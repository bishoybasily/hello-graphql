package com.gmail.bishoybasily.graphql.configuration

import com.gmail.bishoybasily.graphql.model.service.ServiceClient
import com.gmail.bishoybasily.graphql.model.service.ServiceUser
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore

/**
 * Created by bishoy on 11/26/16.
 */
@Configuration
@EnableWebSecurity
class ConfigurationOAuth(private val serviceUser: ServiceUser) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(serviceUser)
    }

    @Configuration
    @EnableAuthorizationServer
    class ConfigurationAuthorization(private val authenticationManager: AuthenticationManager,
                                     private val serviceClient: ServiceClient,
                                     private val tokenStore: TokenStore,
                                     private val passwordEncoder: PasswordEncoder,
                                     private val serviceUser: ServiceUser) : AuthorizationServerConfigurerAdapter() {

        override fun configure(security: AuthorizationServerSecurityConfigurer) {
            security
                    .passwordEncoder(passwordEncoder)
                    .allowFormAuthenticationForClients()
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("permitAll()")
        }

        @Throws(Exception::class)
        override fun configure(clients: ClientDetailsServiceConfigurer) {
            clients.withClientDetails(serviceClient)
        }

        override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
            endpoints
                    .tokenStore(tokenStore)
                    .authenticationManager(authenticationManager)
                    .userDetailsService(serviceUser)
        }

    }

    @Configuration
    @EnableResourceServer
    class ConfigurationResource(private val accessDeniedHandler: OAuth2AccessDeniedHandler,
                                private val oauth2StatelessSecurity: Boolean,
                                private val tokenServices: DefaultTokenServices) : ResourceServerConfigurerAdapter() {

        override fun configure(resources: ResourceServerSecurityConfigurer) {
            resources.stateless(oauth2StatelessSecurity).tokenServices(tokenServices)
        }

        @Throws(Exception::class)
        override fun configure(http: HttpSecurity) {
            http
                    .anonymous()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and().csrf().disable()
                    .and().authorizeRequests().anyRequest().permitAll()
                    .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
        }
    }

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true)
    class ConfigurationMethod : GlobalMethodSecurityConfiguration() {

        override fun createExpressionHandler(): MethodSecurityExpressionHandler {
            return OAuth2MethodSecurityExpressionHandler()
        }

    }

}