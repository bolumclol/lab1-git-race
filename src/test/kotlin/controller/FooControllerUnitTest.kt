package controller

import es.unizar.webeng.hello.controller.FooController
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class FooControllerUnitTest{
    private lateinit var controller: FooController
    @BeforeEach
    fun setup() {
        controller = FooController()
    }
    @Test
    fun testMessage() {
        val map = mutableMapOf<String,Any>()
        val view = controller.welcome2(map)

        Assertions.assertThat(view).isEqualTo("prueba")
        Assertions.assertThat(map.containsKey("message")).isTrue
        Assertions.assertThat(map["message"]).isEqualTo("Hello and welcome")
    }
}