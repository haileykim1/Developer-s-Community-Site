package computermaster.developercommunity.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @GetMapping("/")
    fun index(model: Model): String{

        //model.addAttribute()
        //val user =

        return "index"
    }


}