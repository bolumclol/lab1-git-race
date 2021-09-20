package es.unizar.webeng.hello.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
/*
 *    This class contains a test that checks if the 'date' method
 *    for class DateController in file 'DateController.kt' is
 *    executed successfully.
 */
class DateControllerUnitTests {
    /*
     *   Variable 'controller' that will allocate an instance of the
     *   DateController class. 'lateinit' implies it must be
     *   initiallized later.
     */
    private lateinit var controller: DateController

    /*
     *   This function is used to initiallize the controller variable
     *   with its constructor. Cointains @BeforeEach tag since it
     *   must be executed before the test.
     */
    @BeforeEach
    fun setup() {
        controller = DateController()
    }

    /*
     *   Test that checks if the 'date' method is executed
     *   successfully.
     */
    @Test
    fun testDate() {
        // Map used for 'date' controller method
        val map = mutableMapOf<String, Any>()

        // Executes 'date' controller method and saves returned
        // value in variable 'view'
        val view = controller.date(map)

        // Check if the return value of the 'date' method is equal to "date"
        assertThat(view).isEqualTo("date")

        // Check if the map inside the controller contains the key "currentDate"
        assertThat(map.containsKey("currentDate")).isTrue

        // Check if value for key "currentDate" in map is the current date 
		// with format "yyyy-MM-dd"
        assertThat(map["currentDate"])
				.isEqualTo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
    }
}
