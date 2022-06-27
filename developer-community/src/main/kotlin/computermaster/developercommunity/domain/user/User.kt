package computermaster.developercommunity.domain.user

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

    /*@Column
    var description: String? = null*/

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role = Role.GUEST


    constructor(name: String?, email: String?, role: Role = Role.GUEST){
        this.name = name
        this.email = email
        this.role = role
    }

    /*
    var posts: ConcurrentHashMap<String, Post> = ConcurrentHashMap<String, Post>()
*/
    fun update(name: String?): User{
        this.name = name

        return this
    }

    //builder?

    fun getRoleKey(): String{
        return this.role!!.key
    }

}