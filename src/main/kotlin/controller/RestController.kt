package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * **Note**
 *
 * The annotation `@RestController` is a specialized version of the Controller. Every request handling
 * method of the rest controller class automatically serializes return objects into HttpResponse.
 */
@RestController
class RestController {

    /**
     * **Note**
     *
     * The annotation `@vValue` indicates a default value expression for the annotated
     * element. In this case, it sets the value of the String message to `"Hola estudiante"`.
     */
    @Value("\${app.message}") private var message: String = "Hello World"

    /**
     * This function acts as a handler of the RestController.
     *
     * **Note**
     *
     * The annotation `@GetMapping` acts as a shortcut for `@RequestMapping(method =
     * RequestMethod.GET)`. This allows us to handle all the GET petitions to the path `/rest` using
     * this controller.
     *
     * @return a String serialized into the HttpResponse with the message
     */
    @GetMapping("/api/message")
    fun getBaseMessage(): ResponseEntity<String> =
            ResponseEntity.ok(message)
}