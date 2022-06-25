package computermaster.developercommunity.user

import javax.persistence.*

@Entity
class User {

    //기본 키 생성 DB에게 위임
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var email: String? = null

    @Column
    var description: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role? = null

    constructor(name: String?, email: String?, description: String?, role: Role?){
        this.name = name
        this.email = email
        this.description = description
        this.role = role
    }

    /*
    var posts: ConcurrentHashMap<String, Post> = ConcurrentHashMap<String, Post>()
*/
    fun update(name: String?, description: String?){
        this.name = name
        this.description = description
    }

    //builder?

    fun getRoleKey(): String{
        return this.role!!.key
    }

}