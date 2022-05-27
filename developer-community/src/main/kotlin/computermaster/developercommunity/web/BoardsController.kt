package computermaster.developercommunity.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/boards")
class BoardsController {

    @GetMapping()
    fun boardList(): String{
        return "boards"
    }

    @GetMapping("/new")
    fun addBoard(): String{
        return "addboard"
    }

}