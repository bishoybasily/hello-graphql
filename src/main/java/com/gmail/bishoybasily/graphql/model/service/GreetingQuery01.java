package com.gmail.bishoybasily.graphql.model.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gmail.bishoybasily.graphql.model.entity.Greeting;
import com.gmail.bishoybasily.graphql.model.repository.GreetingRepository;
import org.springframework.stereotype.Service;


@Service
public class GreetingQuery01 implements GraphQLQueryResolver {

    private GreetingRepository greetingRepository;

    public GreetingQuery01(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

//    @Batched
//    @PreAuthorize("hasAnyAuthority('USER')")
    public Greeting greeting(String id) {
        return greetingRepository.find(id);
    }

//    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
//    public Collection<Greeting> greetings() {
//        return greetingRepository.findAll();
//    }

}
