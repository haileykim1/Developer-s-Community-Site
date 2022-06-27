package computermaster.developercommunity.oauth2.dto

import computermaster.developercommunity.domain.user.Role
import computermaster.developercommunity.domain.user.User

class OAuthAttributes {
    val attributes: Map<String, Object>
    var nameAttributeKey: String? = null
    var name: String? = "김"
    var email: String? = "default@default.com"
    var role = Role.GUEST

    constructor(attributes: Map<String, Object>,
        nameAttributeKey: String?,
        name: String?,
        email: String?,
        role: Role = Role.GUEST){
        this.attributes = attributes
        this.nameAttributeKey = nameAttributeKey
        this.name = name
        this.email = email
        this.role = role
    }

    companion object{


        fun of(registrationId: String,
               userNameAttributeName: String,
               attributes: Map<String, Object>, role: Role = Role.GUEST): OAuthAttributes {

            if("naver".equals(registrationId)){
                return ofNaver("id", attributes, role)
            }

            return ofGoogle(userNameAttributeName, attributes, role)
        }

        private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Object>, role: Role = Role.GUEST): OAuthAttributes {
            return Builder(attributes!!.get("name")!! as String, attributes!!["email"]!! as String)
                    .setAttributes(attributes!!)
                    .setNameAttributeKey(userNameAttributeName!!)
                    .setRole(role)
                    .build()
        }

        fun ofNaver(userNameAttributeName: String, attributes: Map<String, Object>, role: Role = Role.GUEST): OAuthAttributes{
            val response = attributes.get("response")!! as Map<String, Object>

            //name, email
            return Builder(response.get("name").toString(), response.get("email").toString())
                    .setAttributes(response as Map<String, Object>)
                    .setNameAttributeKey(userNameAttributeName)
                    .setRole(role)
                    .build()
        }



    }

    fun toEntity(): User {
        return User(name, email, role)
    }


    //builder 패턴 적용
    class Builder(val name: String?, val email: String?){
        private lateinit var nAttributes: Map<String, Object>
        private var nNameAttributeKey: String = ""
        private var onAccept: (() -> Unit)? = null
        private var role: Role = Role.GUEST


        fun setAttributes(attributes: Map<String, Object>): Builder {
            this.nAttributes = attributes
            return this
        }

        fun setNameAttributeKey(userNameAttributeName: String): Builder {
            this.nNameAttributeKey = nNameAttributeKey
            return this
        }

        fun setRole(role: Role): Builder{
            this.role = role
            return this
        }

        fun setOnAccept(onAccept: (() -> Unit)?): Builder {
            this.onAccept = onAccept
            return this
        }


        fun build() = OAuthAttributes(nAttributes, nNameAttributeKey, name, email, role)
    }




}