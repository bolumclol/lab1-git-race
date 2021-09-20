package es.unizar.webeng.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
/**
 * Annotation that marks the class `Application` as Configuration 
 * class and also enable Spring Boot's auto-configuration and 
 * component scanning.
 * It's equivalent to using @Configuration, @EnableAutoConfiguration and 
 * @ComponentScan.
 */
@SpringBootApplication
class Application
//Funcion that bootstraps the application
fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
