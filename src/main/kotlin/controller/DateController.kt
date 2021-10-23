package es.unizar.webeng.hello.controller

import io.swagger.annotations.*
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
 * If no name is specified, the class name will be used by default.
 *
 * [Api] Swagger annotation that indicates the following class implements an API
 * and we can set a custom description and name for the API in the swagger interface.
 */
@Controller
@Api(value = "example", description = "Date Rest API used as example", tags = ["Date API (Name edited by user)"])
class DateController {
    /**
     * This function act as a handler of the [DateController].
     *
     * **Note**
     *
     * The annotation [GetMapping] allows us to handle all the GET petitions to the path `/date`
     * using this controller.
     *
     * [ApiOperation] Swagger annotation indicates this Mapped method is an Operation of the
     * Api implemented in this class. In this case, we see a custom description and the type of the answer.
     * If no name is specified, the function name will be used as the default description.
     *
     * [ApiResponse] Swagger annotation allows setting custom messages for the different
     * http response codes that the operation returns.
     *
     * @param model collection with the data used to update the view (template)
     * @return the template with the updated information
     */
    @GetMapping("/date")
    @ApiOperation(value = "Display a page with current date to user", response = String::class)
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "You are not authorized access the resource because i don't like you"),
            ApiResponse(code = 404, message = "The page was not found")]
    )
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
