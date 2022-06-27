package computermaster.developercommunity.domain.user

enum class Role {


    GUEST("ROLE_GUEST", "게스트"),
    USER("ROLE_MEMBER", "사용자");

    val key: String
    val title: String

    constructor(key: String, title: String){
        this.key = key
        this.title = title
    }


}