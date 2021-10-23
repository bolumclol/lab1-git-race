package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.json.JSONObject
import java.net.URL
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
     * element. In this case, it sets the value of the String helloMessage to `"Hola estudiante"` and the String welcomeMessage to "Bienvenido a la universidad".
     */
    @Value("\${app.message}")
    private var message: String = "Hello World"

    @Value("\${app.hello}")
    private var helloMessage: String = "Welcome to the University"

    /**
     * This function acts as a handler of the HelloController.
     *
     * **Note**
     *
     * The view of this handler uses Thymeleaf as language template.
     * The view is `resources/templates/welcome.html`.
     * Thymeleaf templates has the extension `html` by default.
     * Thymeleaf templates requires to add the dependency `org.springframework.boot:spring-boot-starter-thymeleaf`.
     *
     * The annotation `@GetMapping` acts as a shortcut for `@RequestMapping(method =
     * RequestMethod.GET)`. This allows us to handle all the GET petitions to the path `/` using
     * this controller.
     *
     * @param model collection with the data used to update the view (thymeleaf template)
     * @return the template with the updated information
     */
    @GetMapping("/")
    fun welcome(model: MutableMap<String, Any>): String {
        // This is used to associate the variable "message" of the template welcome with a value.
        model["message"] = message
        model["direction"] = "prueba"
        return "welcome"
    }

    @GetMapping("/weather")
    fun weather(model: MutableMap<String, Any>): String {
        //EINA coordinates
        val lat = 41.683238
        val lon = -0.888809
        try {
            //search EINA weather in open weather map (json objet)
            val response: String =
                URL("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=1d6395a3a8b430066d21639d1f35c026").readText(
                    Charsets.UTF_8
                )


            //read json fields and fill in model
            val jsonObj = JSONObject(response)
            val main = jsonObj.getJSONObject("main")
            val wind = jsonObj.getJSONObject("wind")

            val temp = (main.getDouble("temp").toInt() - 273).toString()
            val feels = (main.getDouble("feels_like").toInt() - 273).toString()

            model["temperature"] = "Temperature: \t\t $temp C"
            model["feels_like"] = "Apparent temperature: \t\t $feels C"

            val humidity = main.getDouble("humidity")
            model["humidity"] = " Humedity: \t\t $humidity %"
            val windSpeed = wind.getDouble("speed")
            model["wind"] = "Wind: $windSpeed m/s"
            val pressure = main.getDouble("pressure")
            model["pressure"] = "Presure: $pressure bar"
            val weather = jsonObj.getJSONArray("weather").getJSONObject(0)


            val icon = weather.getString("icon")
            val iconImg = "https://openweathermap.org/img/wn/$icon@2x.png"
            model["icon"] = iconImg


        } catch (e: Exception) {
            model["temperature"] = "Weather not found"
        }

        return "weather"
    }

    /**
     * This function acts as the handler of the HelloController.
     * shows a template saying hello to the parameter pased by url
     *
     * @param name parameter passed by url
     * @param model collection with the data used to update the view (template)
     * @return the template with the updated information
     */
    @GetMapping("/name/{name}")
    fun new(@PathVariable name: String, model: MutableMap<String, Any>): String {
        model["message"] = "Hello $name"
        return "new"
    }

    /**
     * This function acts as the handler of the HelloController.
     */
    @GetMapping("/rest")
    fun restAPIPage(): String {
        return "restAPIPage"
    }

    /**
     * This function acts as the handler of the HelloController.
     */
    @GetMapping("/react")
    fun restAPIReact(): String {
        return "restAPIReact"
    }

    /**
     * **Note**
     *
     * The view of this handler uses Mustache as language template.
     * The view is `resources/templates/hello.mustache`.
     * Mustache templates has the extension `mustache` by default.
     * Mustache templates requires to add the dependency `org.springframework.boot:spring-boot-starter-mustache`.
     *
     */
    @GetMapping("/hello")
    fun hi(model: MutableMap<String, Any>): String {
        // This is used to associate the variable "message" of the template hello with a value.
        model["message"] = helloMessage
        return "hello"
    }


    /**
     * This function acts as the handler of the event "onClick" of the button "change language".
     *

     *
     * @param model collection with the data used to update the view (template)
     * @return the template with the updated information
     */
    @GetMapping("/changeLanguage")
    fun handleButtonLanguage(model: MutableMap<String, Any>): String {
        message = when (message) {
            "Hola estudiante" -> "Hello student"
            "Hello student" -> "Salut etudiant"
            else -> "Hola estudiante"
        }
        model["message"] = message
        return "welcome"
    }

    /**
     * This function acts as the handler of the HelloController.
     * shows a template saying if your nia (passed by url) is even or odd.
     *
     * @param nia parameter passed by url
     * @param model collection with the data used to update the view (template)
     * @return the template with the updated information
     */
    @GetMapping("/isOdd/{nia}")
    fun nia(@PathVariable nia: String, model: MutableMap<String, Any>): String {
        if (nia.toInt() % 2 == 0) model["message"] = "Your nia: $nia is even"
        else model["message"] = "Your nia: $nia is odd"
        return "new"
    }
}
