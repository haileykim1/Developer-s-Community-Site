package computermaster.member

import computermaster.post.Post
import java.util.concurrent.ConcurrentHashMap

class Member {

    var id: String? = null
    var nickName: String? = null
    var description: String? = null
    var posts: ConcurrentHashMap<String, Post> = ConcurrentHashMap<String, Post>()


}