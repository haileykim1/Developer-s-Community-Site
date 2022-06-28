package computermaster.developercommunity.web

import computermaster.developercommunity.oauth2.dto.SessionUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/login")
class LoginController {

    val httpSession: HttpSession

    @Autowired
    constructor(httpSession: HttpSession){
        this.httpSession = httpSession
    }

    //login 화면
    @GetMapping
    fun login(httpSession: HttpSession, model: Model): String{

        val sessionUser = httpSession.getAttribute("user")
        sessionUser?.let{
            val user = sessionUser as SessionUser
            model.addAttribute("username", user.name)
            return "redirect:/"
        }


        return "login"
    }

    //로그인 성공 URI

}