package computermaster.developercommunity.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/boards")
class BoardsController {

    @GetMapping
    fun boardList(): String{
        return "boards"
    }

    @GetMapping("/new")
    fun addBoard(): String{
        println("get method")
        return "addboard"
    }

    @PostMapping("/new")
    fun createBoard(): String{
        //새로운 보드 추가하는 로직
        println("post method")
        //PRG 패턴 적용
        return "redirect:/boards"
    }

}