package pl.edu.utp.psslab

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import pl.edu.utp.psslab.controllers.MainController
import pl.edu.utp.psslab.model.*
import pl.edu.utp.psslab.services.DelegationService
import pl.edu.utp.psslab.services.UserService

import java.time.LocalDateTime


@SpringBootTest
class PssLabApplicationTests {

    @Autowired
    lateinit var mainController: MainController
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var delegationService: DelegationService

// podpunkt A
    @Test
    fun registerUser() {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        //when
        mainController.registerUser(user)
        val result = userService.getUser(1L)
        //then
        if (result != null) {
            assert(result.userId == 1L)
            assert(result.companyName == "Company name")
            assert(result.companyAddress == "adres")
            assert(result.companyNip == "nip")
            assert(result.name == "Andrzej")
            assert(result.lastName == "Nowak")
            assert(result.email == "email@wp.pl")
            assert(result.password == "haslo")
        }
    }


    //podpunkt B
    @Test
    fun getAllUsers() {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        val user2 = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        //when
        mainController.registerUser(user)
        mainController.registerUser(user2)

        val result = mainController.getAllUsers(1, 1, Pageable.unpaged())
        //then
        println(result.content.size)
        assert(result.content.size != null)
    }

    //podpunkt C
    @Test
    fun changePassword() {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        //when
        println(user.password)
        mainController.registerUser(user)
        mainController.changePassword(1, "nowehaslo")
        val result = userService.getUser(1L)
        //then
        if (result != null) {
            println(result.password)
        }
        if (result != null) {
            assert(result.password == "nowehaslo")
        }

    }

//podpunkt D
    @Test
    fun deleteByUserID() {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        //when
        mainController.registerUser(user)
        val res = userService.getUser(1L)
        if (res != null) {
            assert(res.userId == 1L)
        }
        mainController.deleteUserById(1)
        val result = userService.getUser(1L)
        //then
        assert(result == null)
    }

//podpunkt E
    @Test
    fun addDelegations() {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        val delegation = DelegationDTO("wyjazd integracyjny", LocalDateTime.now(), LocalDateTime.now(), 50,
                20, 10, 10, TransportType.Auto,
                null, AutoCapacity.goe900m3, 500, 20, null, null, null)

//when
        mainController.registerUser(user)
        mainController.addDelegation(1, delegation)

        val result = delegationService.getDelegation(1L)

        //then
        if (result != null) {
            assert(result.delegationId == 1L)
            assert(result.description == "wyjazd integracyjny")
            assert(result.travelDietAmount == 50)
            assert(result.breakfastNumber == 20)
            assert(result.dinnerNumber == 10)
            assert(result.supperNumber == 10)
            assert(result.ticketPrice == null)
            assert(result.km == 500)
            assert(result.accomodationPrice == 20)
            assert(result.otherOutlayPrice == null)
        }
    }

    //podpunkt F
    @Test
    fun removeDelegation() {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        val delegation = DelegationDTO("wyjazd integracyjny", LocalDateTime.now(), LocalDateTime.now(), 50,
                20, 10, 10, TransportType.Auto,
                null, AutoCapacity.goe900m3, 500, 20, null, null, null)
        mainController.registerUser(user)
        mainController.addDelegation(1, delegation)
//when
        val result = mainController.removeDelegation(1L, 1L)

        //then

        assert(result == HttpStatus.OK)
    }
    @Test
    fun changeDelegation() {
        //when
        val delegation = DelegationDTO("wyjazd integracyjny", LocalDateTime.now(), LocalDateTime.now(), 50,
                20, 10, 10, TransportType.Auto,
                null, AutoCapacity.goe900m3, 500, 20, null, null, null)
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")

        //then
        mainController.registerUser(user)
        mainController.addDelegation(1, delegation)
        delegation.description="nowe wytyczne"
        delegation.travelDietAmount=100
        println(delegation.description)
        mainController.changeDelegation(1,delegation)
        var result = delegationService.getDelegation(1L)
        //given

        if (result != null) {
            println(result.delegationId==1L)
            println(result.description)
            println(result.travelDietAmount)
            assert(result.delegationId==1L)
            assert(result.description=="nowe wytyczne")
            assert(result.travelDietAmount==100)
        }



    }

    @Test
    fun getAllDelegations()
    {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")


        val delegation = DelegationDTO("wyjazd integracyjny", LocalDateTime.now(), LocalDateTime.now(), 50,
                20, 10, 10, TransportType.Auto,
                null, AutoCapacity.goe900m3, 500, 20, null, null, null)

        val delegation2 = DelegationDTO("wyjazd integracyjny2", LocalDateTime.now(), LocalDateTime.now(), 50,
                20, 10, 10, TransportType.Auto,
                null, AutoCapacity.goe900m3, 500, 20, null, null, null)

        //when
        mainController.registerUser(user)
        mainController.addDelegation(1,delegation)
        mainController.addDelegation(1,delegation2)

        val result = mainController.getAllDelegations(1,1, Pageable.unpaged())

        //then
        assert(!result.isEmpty)

    }

    }






