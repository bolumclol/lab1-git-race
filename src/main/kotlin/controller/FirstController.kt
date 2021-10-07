package es.unizar.webeng.hello.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class FirstController {


    @RequestMapping("first")
    fun firstEndpoint(model: MutableMap<String, Any>): String {
        return "page1"

    }
}