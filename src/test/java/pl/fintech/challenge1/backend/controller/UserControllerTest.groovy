package pl.fintech.challenge1.backend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import pl.fintech.challenge1.backend.controller.dto.RegistrationDTO
import pl.fintech.challenge1.backend.controller.dto.UserDTO
import pl.fintech.challenge1.backend.controller.mapper.UserMapper
import pl.fintech.challenge1.backend.domain.investment.Investment
import pl.fintech.challenge1.backend.domain.user.User
import pl.fintech.challenge1.backend.domain.user.UserService
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.hamcrest.Matchers.is
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ActiveProfiles("test")
@WebMvcTest(controllers = [UserController.class])
@AutoConfigureJsonTesters
class UserControllerTest extends Specification {

    @Autowired
    MockMvc mvc

    @MockBean
    UserService userService

    @MockBean
    UserMapper userMapper

    @Autowired
    private JacksonTester<RegistrationDTO> json;

    def "CreateNewUser"() {
        given:
        def user = new User(1, "email@email.com", "Password1!", new HashSet<Investment>())
        when(userService.save(user)).thenReturn(user)
        when(userMapper.mapDTOToUser(getRegistrationDTO())).thenReturn(user)
        when(userMapper.mapUserToDTO(user)).thenReturn(UserDTO.builder().email("email@email.com").build())
        expect:
        mvc.perform(post(new URI("/api/users/register"))
                        .content(json.write(getRegistrationDTO()).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("email", is("email@email.com")))

    }

    RegistrationDTO getRegistrationDTO() {
        def register = new RegistrationDTO()
        register.setEmail("email@email.com")
        register.setPassword("Password1!")
        register.setConfirmPassword("Password1!")
        return register
    }

    @TestConfiguration
    static class StubConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        UserService userService() {
            return detachedMockFactory.Stub(UserService)
        }

        @Bean
        UserMapper userMapper() {
            return detachedMockFactory.Stub(UserMapper)
        }

    }
}
