package com.gmail.bishoybasily.graphql.model.service

import com.gmail.bishoybasily.graphql.model.entity.Client
import com.gmail.bishoybasily.graphql.model.repository.RepositoryClients
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * Created by bishoy on 5/9/17.
 */
@Service
class ServiceClient(private val repositoryClients: RepositoryClients) : ClientDetailsService {

    override fun loadClientByClientId(identifier: String) =
            repositoryClients.findByIdentifier(identifier).orElseThrow { IllegalAccessException() }

    fun findById(identifier: String): Mono<Client> {
        return Mono.fromCallable { loadClientByClientId(identifier) }
    }

}
