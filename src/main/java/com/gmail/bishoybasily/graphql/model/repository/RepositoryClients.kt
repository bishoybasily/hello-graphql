package com.gmail.bishoybasily.graphql.model.repository

import com.gmail.bishoybasily.graphql.model.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints
import org.springframework.data.repository.query.Param
import java.util.*
import javax.persistence.QueryHint


/**
 * Created by bishoy on 10/13/16.
 */
interface RepositoryClients : JpaRepository<Client, String> {

    @Query("select c from clients c where c.id=:identifier or c.clientId=:identifier")
    @QueryHints(QueryHint(name = "org.hibernate.cacheable", value = "true"))
    fun findByIdentifier(@Param("identifier") identifier: String): Optional<Client>


}
