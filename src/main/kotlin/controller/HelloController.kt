package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * **Note**
 *
 * The annotation `@Controller` serves as a specialization of `@Component` and it allows us to
 * implement a web controller that handles templates.
 */
@Controller
class HelloController {

    /**
     * **Note** 
     * 
     * The annotation `@vValue` indicates a default value expression for the annotated
     * element. In this case, it sets the value of the String message to `"Hola estudiante"`.
     */
    @Value("\${app.message}") private var message: String = "Hello World"

    /**
     * This function acts as the handler of the HelloController.
     *
     * **Note** 
     * 
     * The annotation `@GetMapping` acts as a shortcut for `@RequestMapping(method =
     * RequestMethod.GET)`. This allows us to handle all the GET petitions to the path `/` using
     * this controller.
     *
     * @param model collection with the data used to update the view (template)
     * @return the template with the updated information
     */
    @GetMapping("/")
    fun welcome(model: MutableMap<String, Any>): String {
        // This is used to associate the variable "message" of the template welcome with a value.
        model["message"] = message
        return "welcome"
    }
    
    /**
    * This function acts as the handler of the HelloController.
    * shows a template saying if your nia (pased by url) is even or odd.
    * 
    * **Note**
    * @param nia parameter passed by url
    * @param model collection with the data used to update the view (template)
    * @return the template with the updated information
    */
    @GetMapping("/isOdd/{nia}")
    fun new(@PathVariable nia: String, model: MutableMap<String, Any>): String {
        if (nia.toInt()%2 == 0) model["message"] = "Your nia: " + nia + " is even"
        else  model["message"] = "Your nia: " + nia + " is odd"
        return "new"
    }
}
