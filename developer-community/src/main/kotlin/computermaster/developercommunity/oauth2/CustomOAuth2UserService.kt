package computermaster.developercommunity.oauth2

import computermaster.developercommunity.user.User
import computermaster.developercommunity.user.UserRepository
import computermaster.developercommunity.oauth2.dto.OAuthAttributes
import computermaster.developercommunity.oauth2.dto.SessionUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.servlet.http.HttpSession

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class CustomOAuth2UserService: OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    val userRepository: UserRepository
    val httpSession: HttpSession

    @Autowired
    constructor(userRepository: UserRepository, httpSession: HttpSession){
        this.userRepository = userRepository
        this.httpSession = httpSession
    }


    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {

        val delegate: OAuth2UserService<OAuth2UserRequest, OAuth2User> = DefaultOAuth2UserService()
        val oAuth2User: OAuth2User = delegate.loadUser(userRequest)

        //현재 로그인 진행 중인 서비스 구분
        val registrationId = userRequest!!.clientRegistration.registrationId

        println("===========================")
        println("${registrationId}")
        println("============================")

        //OAuth2로그인 진행 시 키가 되는 필드값(primary key같은)
        val userNameAttribute = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        //val attributes = OAuthAttributes.attributes
        val attributes: OAuthAttributes = OAuthAttributes.of(registrationId, userNameAttribute, oAuth2User.attributes as ConcurrentHashMap<String, Object>)

        //세션에 사용자 정보를 저장하기 위한 Dto 클래스
        val user: User = saveOrUpdate(attributes)
        httpSession.setAttribute("user", SessionUser(user))


        return DefaultOAuth2User(
               Collections.singleton(SimpleGrantedAuthority(user.getRoleKey())),
                attributes.attributes as ConcurrentHashMap<String, Any>?,
                attributes.nameAttributeKey
        )

    }

    fun saveOrUpdate(attributes: OAuthAttributes): User{
        val user = userRepository.findByEmail(attributes.email)
                ?.let{ entity ->
                    entity.update(attributes.name, attributes.description)
                }
                ?: attributes.toEntity()

        return userRepository.save(user as User) as User
    }

}