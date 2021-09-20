package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * The annotation `@Controller` serves as a specialization of `@Component` and it allows us to
 * implement a web controller that handles templates.
 * This component it's used to handle the template associated with "/prueba", it's just a simple test in order to
 * show to the user that we are now in a different template regarding the one given.
 */
@Controller
class FooController {

    /**
     *
     * The annotation `@Value` indicates a default value expression for the annotated
     * element. In this case, it sets the value of the String message to `"Your first steps to Web Engineering",
     * whose value it's defined on the application.properties`.
     */
    @Value("\${app.message2}") private var messageSaludo: String = "Hello and welcome"

    /**
     * The annotation `@GetMapping` acts as a shortcut for `@RequestMapping(method =
     * RequestMethod.GET)`. This allows us to handle all the GET petitions to the path `/prueba` using
     * this controller.
     *
     * @param model collection with the data used to update the view
     * @return the template with the updated information
     */
    @GetMapping("/prueba")
    fun welcome2(model: MutableMap<String, Any>): String {
        model["message"] = messageSaludo
        return "prueba"
    }

}