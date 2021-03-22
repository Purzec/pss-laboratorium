package pl.edu.utp.psslab

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import pl.edu.utp.psslab.model.User
import pl.edu.utp.psslab.services.UserService


@AutoConfigureMockMvc
@SpringBootTest
@SpringBootConfiguration
class PssLabApplicationTests {

    /*@Test
    @Throws(Exception::class)
    fun getProjekty() {
        val projekt = User(1, "Nazwa1", "Opis1", LocalDate.now(), LocalDate.of(2020, 6, 7))
        val page: Page<Projekt> = PageImpl(listOf<Any>(projekt))
        Mockito.`when`(mockProjektService.getProjekty(ArgumentMatchers.any(Pageable::class.java))).thenReturn(page)
        mockMvc.perform(MockMvcRequestBuilders.get(apiPath).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[*]").exists()) //content[*] - oznacza całą zawartość tablicy content
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].projektId").value(projekt.getProjektId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nazwa").value(projekt.getNazwa()))
        Mockito.verify<Any>(mockProjektService, Mockito.times(1)).getProjekty(ArgumentMatchers.any(Pageable::class.java))
        verifyNoMoreInteractions(mockProjektService)
    }*

    @Test
        public void createProjekt() throws Exception {
            Projekt projekt = new Projekt(null, "Nazwa3", "Opis3", null, LocalDate.of(2020, 6, 7));
            String jsonProjekt = jacksonTester.write(projekt).getJson();
            projekt.setProjektId(3);
            when(mockProjektService.setProjekt(any(Projekt.class))).thenReturn(projekt);

            mockMvc.perform(post(apiPath).content(jsonProjekt).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(header().string("location", containsString(apiPath + "/" + projekt.getProjektId())));
        }

    /
     */

   /* @Test
    @Throws(Exception::class)
    fun createProjekt() {
        val projekt = Projekt(null, "Nazwa3", "Opis3", null, LocalDate.of(2020, 6, 7))
        val jsonProjekt: String = jacksonTester.write(projekt).getJson()
        projekt.setProjektId(3)
        Mockito.`when`(mockProjektService.setProjekt(ArgumentMatchers.any(Projekt::class.java))).thenReturn(projekt)
        mockMvc.perform(MockMvcRequestBuilders.post(apiPath).content(jsonProjekt).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString(apiPath + "/" + projekt.getProjektId())))
    }
*/

    @MockBean
    lateinit var mockProjektService: UserService


    lateinit var jacksonTester: JacksonTester<User>
    @Test
    @Throws
    fun createUser()
    {
        //given
        val user = User("Company name", "test",
                "Patryk", "Jurzec", "email@wp.pl", "haslo123", "elo")

        //then
val jsonUser : String = jacksonTester.write(user).json
        user.userId=2
Mockito.`when`(mockProjektService.setUser(ArgumentMatchers.any(User::class.java))).thenReturn(user)





    }


    @Test
    fun contextLoads() {
        //given
        var liczba =5

        //when
        liczba++
        //then
        assert(liczba == 6)
    }


}
