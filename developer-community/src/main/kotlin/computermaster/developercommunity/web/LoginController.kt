package computermaster.developercommunity.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/login")
class LoginController {

    //login 화면
    @GetMapping
    fun login(): String{

        return "login"
    }

    //로그인 성공 URI

}