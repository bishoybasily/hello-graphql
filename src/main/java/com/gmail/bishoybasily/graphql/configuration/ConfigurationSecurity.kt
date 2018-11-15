package com.gmail.bishoybasily.graphql.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.gmail.bishoybasily.graphql.model.service.ServiceUser
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AccountStatusUserDetailsChecker
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore


@Configuration
class ConfigurationSecurity {

    @Bean
    @Primary
    fun tokenStore(): TokenStore {
        return InMemoryTokenStore()
    }

    @Bean
    @Primary
    fun tokenEnhancer(objectMapper: ObjectMapper, serviceUser: ServiceUser): TokenEnhancer {
        return TokenEnhancer { accessToken, authentication ->

            val additionalInfo = HashMap<String, Any>()

            additionalInfo.put("user", serviceUser.loadUserByUsername(authentication.name))

            (accessToken as DefaultOAuth2AccessToken).additionalInformation = additionalInfo

            return@TokenEnhancer accessToken
        }
    }

    @Bean
    fun authenticationManager(passwordEncoder: PasswordEncoder,
                              serviceUser: ServiceUser) = AuthenticationManager {

        val name = it.name.toString()
        val credentials = it.credentials.toString()

        val user = serviceUser.loadUserByUsername(name)
        if (passwordEncoder.matches(credentials, user.password))
            return@AuthenticationManager UsernamePasswordAuthenticationToken(name, credentials, user.authorities)


        throw BadCredentialsException("Credentials are incorrect")

    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Primary
    fun tokenServices(tokenStore: TokenStore): DefaultTokenServices {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore)
        defaultTokenServices.setSupportRefreshToken(true)
        return defaultTokenServices
    }

    @Bean
    fun oAuth2AccessDeniedHandler(): OAuth2AccessDeniedHandler {
        return OAuth2AccessDeniedHandler()
    }

    @Bean
    fun detailsChecker(): AccountStatusUserDetailsChecker {
        return AccountStatusUserDetailsChecker()
    }

    @Bean
    fun expressionHandler(applicationContext: ApplicationContext): OAuth2MethodSecurityExpressionHandler {
        val expressionHandler = OAuth2MethodSecurityExpressionHandler()
        expressionHandler.setApplicationContext(applicationContext)
        return expressionHandler
    }

    @Bean
    fun oauth2StatelessSecurity(): Boolean {
        return true
    }

}
