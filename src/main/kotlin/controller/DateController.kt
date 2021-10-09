package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * **Note**
 *
 * The annotation `@Controller` serves as a specialization of `@Component` and it allows us to
 * implement a web controller that handles templates.
 */
@Controller
class DateController {
    /**
     * This function act as a handler of the [DateController].
     *
     * **Note**
     *
     * The annotation [GetMapping] allows us to handle all the GET petitions to the path `/date`
     * using this controller.
     *
     * @param model collection with the data used to update the view (template)
     * @return the template with the updated information
     */
    @GetMapping("/date")
    fun date(model: MutableMap<String, Any>): String {
        //Get the current date with format "yyyy-MM-dd"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = LocalDateTime.now().format(formatter)
        // This is used to associate the variable "currentDate" of the template date
        // with the current date.
        model["currentDate"] = currentDate
        return "date"
    }
}
