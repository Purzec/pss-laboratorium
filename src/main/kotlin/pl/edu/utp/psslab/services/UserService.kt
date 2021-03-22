package pl.edu.utp.psslab.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import pl.edu.utp.psslab.model.User
import pl.edu.utp.psslab.repositories.UserRepository

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun getUser(userId: Long) =
        if(userRepository.existsById(userId)) {
            userRepository.findById(userId).get()
        } else null

    fun setUser(user: User) = userRepository.save(user)

    fun getAllUsers(pageable: Pageable) = userRepository.findAll(pageable)

    fun deleteUser(userId: Long) =
        if(userRepository.existsById(userId)) {
            userRepository.deleteById(userId)
            true
        } else false

    fun getAllUsersByRoleName(roleName: String) = userRepository.findAllUsersByRoleName(roleName)


    fun getAllUsersByRoleName(roleName: String, pageable: Pageable) = userRepository.findAllUsersByRoleName(roleName, pageable)

}