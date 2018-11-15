package com.gmail.bishoybasily.graphql.model.repository

import com.gmail.bishoybasily.graphql.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints
import org.springframework.data.repository.query.Param
import java.util.*
import javax.persistence.QueryHint

interface RepositoryUsers : JpaRepository<User, String> {

    @QueryHints(QueryHint(name = "org.hibernate.cacheable", value = "true"))
    @Query("select u from users u  where u.id=:identifier or u.username=:identifier")
    fun findByIdentifier(@Param("identifier") identifier: String): Optional<User>


}
