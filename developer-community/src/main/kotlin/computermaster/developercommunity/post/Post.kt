package computermaster.developercommunity.post

import javax.persistence.*

@Entity
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: String? = null

    @Column(length = 100, nullable = false)
    var title: String? = null

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String? = null

    var author: String? = null


}