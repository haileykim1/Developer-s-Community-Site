package computermaster.developercommunity.web

import computermaster.developercommunity.oauth2.dto.SessionUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpSession

@Controller
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class IndexController {

    val httpSession: HttpSession

    @Autowired
    constructor(httpSession: HttpSession){
        this.httpSession = httpSession
    }

    @GetMapping("/")
    fun index(httpSession: HttpSession, model: Model): String{

        val sessionUser = httpSession.getAttribute("user")
        sessionUser?.let{
            val user = sessionUser as SessionUser
            println("=====================\n${user.name}\n===================")
            model.addAttribute("username", user.name)
        }

        return "index"
    }


}