package com.gmail.bishoybasily.graphql.model.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.gmail.bishoybasily.graphql.model.entity.Greeting;
import com.gmail.bishoybasily.graphql.model.repository.GreetingRepository;
import org.springframework.stereotype.Service;

@Service
public class GreetingMutation implements GraphQLMutationResolver {

    private GreetingRepository greetingRepository;

    public GreetingMutation(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Greeting greeting(String message) {
        return greetingRepository.save(new Greeting().setMessage(message));
    }

}
