package pl.edu.utp.psslab

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import pl.edu.utp.psslab.controllers.MainController
import pl.edu.utp.psslab.model.*

import java.time.LocalDateTime


@SpringBootTest
class PssLabApplicationTests {

    @Autowired
    lateinit var mainController: MainController


    @Test
    fun registerUser() {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        //when
        mainController.registerUser(user)
        val result = mainController.getUserByID(1L)
        //then
        if (result != null) {
            assert(result.userId == 1L)
        }
    }

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

    @Test
    fun changePassword() {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        //when
        println(user.password)
        mainController.registerUser(user)
        mainController.changePassword(1, "nowehaslo")
        val result = mainController.getUserByID(1)
        //then
        if (result != null) {
            println(result.password)
        }
        if (result != null) {
            assert(result.password == "nowehaslo")
        }

    }


    @Test
    fun deleteByUserID() {
        //given
        val user = UserDTO("Company name", "adres",
                "nip", "Andrzej", "Nowak", "email@wp.pl", "haslo")
        //when
        mainController.registerUser(user)
        val res = mainController.getUserByID(1)
        if (res != null) {
            assert(res.userId == 1L)
        }
        mainController.deleteUserById(1)
        val result = mainController.getUserByID(1)
        //then
        assert(result == null)
    }


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

        val result = mainController.getDelegation(1L)

        //then
        if (result != null) {
            assert(result.delegationId == 1L)
        }
    }

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

    /*@Test
    fun changeDelegation() {
        //when
        val delegation = DelegationDTO("wyjazd integracyjny", LocalDateTime.now(), LocalDateTime.now(), 50,
                20, 10, 10, TransportType.Auto,
                null, AutoCapacity.goe900m3, 500, 20, null, null, null)
        //then

        mainController.changeDelegation(1, delegation.description = "Nowy opis")

        //given

    }*/

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






