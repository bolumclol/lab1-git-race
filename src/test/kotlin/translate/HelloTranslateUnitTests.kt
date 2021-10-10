package es.unizar.webeng.hello.translate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/*
 *    This class contains a test that checks if the 'welcome' method
 *    for class HelloController in file 'HelloController.kt' is
 *    executed successfully.
 */
class HelloTranslateUnitTests {
    /*
     *   Variable 'controller' that will allocate an instance of the
     *   HelloController class. 'lateinit' implies it must be
     *   initiallized later.
     */
    private lateinit var translate: HelloTranslate


    /*
     *   This function is used to initiallize the controller variable
     *   with its constructor. Cointains @BeforeEach tag since it
     *   must be executed before the test.
     */
    @BeforeEach
    fun setup() {
        translate = HelloTranslate()
    }

    /*
     *   Test that checks if the 'welcome' method is executed
     *   successfully.
     */
    @Test
    fun translateMessage() {

        // Check if the message is correctly translate from Spanish to English"
        var message=translate.translate("Hola mundo, esto es una prueba.")
        assertThat(message).isEqualTo("Hello world, this is a test.")

        // Check if the message is correctly translate from German to English"
        message=translate.translate("Hallo Welt, das ist ein Test.")

        assertThat(message).isEqualTo("hello world, that's a test.")

    }
}
