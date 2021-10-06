package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.json.JSONObject
import java.net.URL

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
        model["message"] = "hellow"
        return "welcome"
    }

    @GetMapping("/weather")
    fun weather(model : MutableMap<String, Any>): String{
      //EINA coordinates
      val lat = 41.683238
      val lon = -0.888809
      try{
        //search EINA weather in open weather map (json objet)
        val response :String = URL("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=1d6395a3a8b430066d21639d1f35c026").readText(Charsets.UTF_8)
        
       
        //read json fields and fill in model
        val jsonObj = JSONObject(response)
        val main = jsonObj.getJSONObject("main")
        val wind = jsonObj.getJSONObject("wind")
        
        val temp = (main.getDouble("temp").toInt() - 273).toString()
        val feels =(main.getDouble("feels_like").toInt() - 273).toString()
        
        model["temperature"] = "Temperatura: \t\t $temp C"
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
      
        
      }catch(e: Exception){
        model["temperature"] = "Weather not found"
      }

      return "weather"
    }
}
