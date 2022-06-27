package computermaster.developercommunity.oauth2.dto

import computermaster.developercommunity.domain.user.User
import java.io.Serializable

//인증된 사용자 정보만 필요.
class SessionUser: Serializable {
    var name: String? = null
    var email: String? = null

    constructor(user: User){
        this.name = user.name
        this.email = user.email
    }

}