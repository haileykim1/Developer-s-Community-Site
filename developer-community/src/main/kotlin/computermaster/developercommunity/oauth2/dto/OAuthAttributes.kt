package computermaster.developercommunity.oauth2.dto

import computermaster.developercommunity.user.Role
import computermaster.developercommunity.user.User
import java.util.concurrent.ConcurrentHashMap

class OAuthAttributes {
    var attributes: ConcurrentHashMap<String, Object>? = null
    var nameAttributeKey: String? = null
    var name: String? = null
    var description: String? = null
    var email: String? = null

    constructor(attributes: ConcurrentHashMap<String, Object>?,
        nameAttributeKey: String?,
        name: String?,
        description: String?,
        email: String?){
        this.attributes = attributes
        this.nameAttributeKey = nameAttributeKey
        this.name = name
        this.description = description
        this.email = email
    }

    companion object{


        fun of(registrationId: String,
               userNameAttributes: String,
               attributes: ConcurrentHashMap<String, Object>): OAuthAttributes {
            return ofGoogle(userNameAttributes, attributes)
        }

        private fun ofGoogle(userNameAttributeName: String, attributes: ConcurrentHashMap<String, Object>): OAuthAttributes {
            return Builder(attributes!!.get("name")!! as String, attributes!!["email"]!! as String)
                    .setDescription(attributes!!["description"]!! as String)
                    .setAttributes(attributes!!)
                    .setNameAttributeKey(userNameAttributeName!!)
                    .build()
        }

    }

    //게스트 전용
    fun toEntity(): User{
        return User(null, null, null, Role.GUEST)
    }


    //builder 패턴 적용
    class Builder(val name: String, val email: String){
        private var nDescription: String? = null
        private var nAttributes: ConcurrentHashMap<String, Object>? = null
        private var nNameAttributeKey: String? = null
        private var onAccept: (() -> Unit)? = null



        fun setDescription(description: String?): Builder {
            this.nDescription = description
            return this
        }

        fun setAttributes(attributes: ConcurrentHashMap<String, Object>): Builder {
            this.nAttributes = attributes
            return this
        }

        fun setNameAttributeKey(userNameAttributeName: String): Builder {
            this.nNameAttributeKey = nNameAttributeKey
            return this
        }

        fun setOnAccept(onAccept: (() -> Unit)?): Builder {
            this.onAccept = onAccept
            return this
        }


        fun build() = OAuthAttributes(nAttributes, nNameAttributeKey, name, nDescription, email)
    }




}