package es.unizar.webeng.hello.controller

import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@WebMvcTest(DateController::class)
class DateControllerMVCTests {
    /**
     * Mocks the Spring controller
     */
    @Autowired
    private lateinit var mockMvc: MockMvc

    /**
     * With the controller [DateController] mocked, test performs a GET request to server-side
     * endpoint "/date" and:
     *
     * - print the response
     * - expect to receive an OK status (code 200)
     * - expect the atributte "currentDate" of the model to be the current date
     *   with format "yyyy-MM-dd"
     */
    @Test
    fun testDate() {
        mockMvc.perform(get("/date"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                model().attribute(
                    "currentDate",
                    equalTo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                )
            )
    }
}
