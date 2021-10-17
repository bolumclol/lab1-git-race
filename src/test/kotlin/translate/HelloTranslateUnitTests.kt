package es.unizar.webeng.hello.translate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HelloTranslateUnitTests {
    private lateinit var translate: HelloTranslate


    @BeforeEach
    fun setup() {
        translate = HelloTranslate()
    }

    @Test
    fun translateMessage() {
        // Check if the message is correctly translate from Spanish to English
        var message = translate.translate("Hola mundo, esto es una prueba.")
        assertThat(message).isEqualTo("Hello world, this is a test.")

        // Check if the message is correctly translate from German to English
        message = translate.translate("Hallo Welt, das ist ein Test.")
        assertThat(message).isEqualTo("hello world, that's a test.")
    }
}
