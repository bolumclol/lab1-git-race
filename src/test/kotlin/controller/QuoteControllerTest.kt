package es.unizar.webeng.hello.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import es.unizar.webeng.hello.QuoteRepository
import org.springframework.beans.factory.annotation.Autowired
import es.unizar.webeng.hello.Quote
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

/**
 *    This class contains a test that checks if the 'quotes' method
 *    for class QuoteController in file 'QuoteController.kt' is
 *    executed successfully.
 */
@DataJpaTest
class QuoteControllerUnitTests @Autowired constructor (val quoteRepository: QuoteRepository) {

    /**
     *   Variable 'controller' that will allocate an instance of the
     *   QuoteController class. 'lateinit' implies it must be
     *   initiallized later.
     */
    private lateinit var controller: QuoteController 

    /**
     *   This function is used to initiallise the controller variable
     *   with its constructor. Contains @BeforeEach tag since it
     *   must be executed before the test.
     * 
     *   It deletes all elements in the database to start all tests 
     *   with an empty one.
     */
    @BeforeEach
    fun setup() {
        quoteRepository.deleteAll()
        controller = QuoteController(quoteRepository)
    }

    /**
     *   Test that checks if the 'quotes' method is executed
     *   successfully when there are no quotes saved.
     */
    @Test
    fun testEmpty() {
        // Map used for 'quotes' controller method
        val map = mutableMapOf<String, Any>()

        // Executes 'quotes' controller method without extra information
        // and saves returned value in variable 'view'
        val view = controller.quotes("", "", map)

        // Check if the return value of the quotes method is equal to "quotes"
        assertThat(view).isEqualTo("quotes")

        // Check if the map inside the controller contains the key "quote"
        assertThat(map.containsKey("quote")).isTrue

        // Check if value for key "quote" in map is "It seems there aren't any quotes yet."
        assertThat(map["quote"]).isEqualTo("It seems there aren't any quotes yet.")

        // Check if the map inside the controller contains the key "saidBy"
        assertThat(map.containsKey("saidBy")).isTrue

        // Check if value for key "saidBy" in map is "Unknown"
        assertThat(map["saidBy"]).isEqualTo("Unknown")
    }

    /**
     * Test that checks if the 'quotes' method is executed successfully when
     * there is a new quote to add and there are no quotes saved yet.
     */
    @Test
    fun testAdd() {
        // Map used for 'quotes' controller method
        val map = mutableMapOf<String, Any>()

        val quote = Quote(
            "If debugging is the process of removing software bugs, then programming must be the process of putting them in.",
            "Edsger W. Dijkstra")

        // Executes 'quotes' controller method with a valid quote
        // and saves returned value in variable 'view'
        val view = controller.quotes(quote.quote, quote.saidBy, map)

        // Check if the return value of the quotes method is equal to "quotes"
        assertThat(view).isEqualTo("quotes")

        // Check if the map inside the controller contains the key "quote"
        assertThat(map.containsKey("quote")).isTrue

        // Check if value for key "quote" in map is the new added quote
        assertThat(map["quote"]).isEqualTo(quote.quote)

        // Check if the map inside the controller contains the key "saidBy"
        assertThat(map.containsKey("saidBy")).isTrue

        // Check if value for key "saidBy" in map is the same as that of the new added quote
        assertThat(map["saidBy"]).isEqualTo(quote.saidBy)
    }
}
