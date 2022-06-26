package computermaster.developercommunity.oauth2

import computermaster.developercommunity.user.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@ComponentScan
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter {

    val customOAuth2UserService: CustomOAuth2UserService

    @Autowired
    constructor(customOAuth2UserService: CustomOAuth2UserService){
        this.customOAuth2UserService = customOAuth2UserService
    }

    override fun configure(http: HttpSecurity) {

        //url별 권한 관리
        http
                .csrf{
                    it.disable()
                }
                .authorizeRequests{
                    it.antMatchers("/", "/login", "/css/**", "/images/**").permitAll()
                    it.antMatchers("/api/v1/**").hasRole(Role.USER.name)
                    it.anyRequest().authenticated()
                }
                .logout{
                    it.logoutSuccessUrl("/")
                }
                .oauth2Login{
                    it.loginPage("/login")
                    it.userInfoEndpoint().userService(customOAuth2UserService)
                }

    }



}