package com.gmail.bishoybasily.graphql.runner;

import com.gmail.bishoybasily.graphql.model.entity.Client;
import com.gmail.bishoybasily.graphql.model.entity.User;
import com.gmail.bishoybasily.graphql.model.repository.RepositoryClients;
import com.gmail.bishoybasily.graphql.model.repository.RepositoryUsers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitializationRunner implements CommandLineRunner {

    private RepositoryUsers repositoryUsers;
    private RepositoryClients repositoryClients;
    private PasswordEncoder passwordEncoder;

    public InitializationRunner(RepositoryUsers repositoryUsers, RepositoryClients repositoryClients, PasswordEncoder passwordEncoder) {
        this.repositoryUsers = repositoryUsers;
        this.repositoryClients = repositoryClients;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {

        User userRoot = new User()
                .setId("unique_user_id")
                .setName("Super-duper")
                .setUsername("root")
                .setPassword(passwordEncoder.encode("toor"))
                .setEnabled(true)
                .setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .setPhotoName("generic.png")
                .addAuthorities("USER", "ADMINISTRATOR");
        repositoryUsers.save(userRoot);

        Client clientFirstParty = new Client()
                .setId("unique_client_id")
                .setClientId("some_id")
                .setClientSecret(passwordEncoder.encode("some_secret"))
                .addGrantTypes("password", "refresh_token", "authorization_code", "implicit")
                .addScopes("openid")
                .setPhotoName("fidelyo.png")
                .setAccessTokenValiditySeconds(1 * 24 * 60 * 60)
                .setRefreshTokenValiditySeconds(10 * 24 * 60 * 60);
        repositoryClients.save(clientFirstParty);

    }

}
