package pl.edu.utp.psslab.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.edu.utp.psslab.model.Delegation
import pl.edu.utp.psslab.model.DelegationDTO
import pl.edu.utp.psslab.model.User
import pl.edu.utp.psslab.model.UserDTO
import pl.edu.utp.psslab.services.DelegationService
import pl.edu.utp.psslab.services.UserService
import springfox.documentation.swagger2.annotations.EnableSwagger2

@RestController
@EnableSwagger2
class MainController
@Autowired
constructor(
    private val userService: UserService,
    private val delegationService: DelegationService
) {

    @PostMapping("/user/create")
    fun registerUser(
        @ModelAttribute userDTO: UserDTO
    ) = userService.setUser(User(userDTO))



    @GetMapping("/users")
    fun getAllUsers(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "0") size: Int,
        pageable: Pageable
    ) = userService.getAllUsers(pageable)
    // = userService.getAllUsers(PageRequest.of(pageRequest.pageNumber, pageRequest.pageSize, pageRequest.sort))



    @ResponseStatus
    @PutMapping("/user/changepassword")
    fun changePassword(
        @RequestParam("userId") userId: Long,
        @RequestParam("newPassword") newPassword: String,
    ): HttpStatus {
        val user = userService.getUser(userId)
        if (user != null) {
            user.password = newPassword
            userService.setUser(user)
            return HttpStatus.OK
        } else
            return HttpStatus.NOT_FOUND
    }

    @DeleteMapping("/user/delete")
    fun deleteUserById(
        @RequestParam("userId") userId: Long
    ) = if (userService.deleteUser(userId)) HttpStatus.OK else HttpStatus.NOT_FOUND

    @PutMapping("/delegation/add")
    fun addDelegation(
        @RequestParam("userId") userId: Long,
        @ModelAttribute delegationDTO: DelegationDTO
    ): Delegation? {
        val user = userService.getUser(userId)
        if (user != null) {
            val delegation = Delegation(delegationDTO)
            delegation.user = user
            delegationService.setDelegation(delegation)
            return delegation
        } else
            return null
    }

    @DeleteMapping("/delegation/delete")
    fun removeDelegation(
        @RequestParam("delegationId") delegationId: Long,
        @ModelAttribute("userId") userId: Long
    ): HttpStatus {
        val delegation = delegationService.getDelegation(delegationId)
        if (delegation != null) {
            if (userId == delegation.user!!.userId) {
                delegationService.deleteDelegation(delegationId)
                return HttpStatus.OK
            }
            return HttpStatus.FORBIDDEN
        }
        return HttpStatus.NOT_FOUND
    }

    @PutMapping("/delegation/change")
    fun changeDelegation(
        @RequestParam("delegationId") delegationId: Long,
        @RequestParam("delegation") delegationDTO: DelegationDTO
    ): HttpStatus {
        val delegation = delegationService.getDelegation(delegationId)
        if (delegation != null) {
            delegation.updateValues(delegationDTO)
            return HttpStatus.OK
        }
        return HttpStatus.NOT_FOUND
    }

    @GetMapping("/delegations")
    fun getAllDelegations(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "0") size: Int,
        pageable: Pageable
    ) = delegationService.getAllDelegations(pageable)

    @GetMapping("/delegations/datestartdesc")
    fun getAllDelegationsOrderByDateStartDesc(pageable: Pageable) =
        delegationService.getAllDelegationsOrderByDateStartDesc(pageable)

    @GetMapping("/delegations/userdatestartdesc")
    fun getAllDelByUserOrderByDateStartDesc(
        @RequestParam("userId") userId: Long,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "0") size: Int,
        pageable: Pageable
    ) =
        delegationService.getAllDelByUserOrderByDateStartDesc(userId, pageable)

    @GetMapping("/users/role")
    fun getAllUsersByRoleName(
        @RequestParam("roleName") roleName: String,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "0") size: Int,
        pageable: Pageable
    ) = userService.getAllUsersByRoleName(roleName, pageable)


}