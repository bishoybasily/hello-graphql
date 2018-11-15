package com.gmail.bishoybasily.graphql.model.service

import com.gmail.bishoybasily.graphql.model.entity.User
import com.gmail.bishoybasily.graphql.model.repository.RepositoryUsers
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * Created by bishoy on 6/18/18.
 */
@Service
class ServiceUser(private val repositoryUsers: RepositoryUsers) : UserDetailsService {

    override fun loadUserByUsername(identifier: String) =
            repositoryUsers.findByIdentifier(identifier).orElseThrow { IllegalAccessException() }

    fun findById(identifier: String): Mono<User> {
        return Mono.fromCallable { loadUserByUsername(identifier) }
    }

}