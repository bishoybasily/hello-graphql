package com.gmail.bishoybasily.graphql.model.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gmail.bishoybasily.graphql.model.entity.Greeting;
import com.gmail.bishoybasily.graphql.model.repository.GreetingRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class GreetingQuery02 implements GraphQLQueryResolver {

    private GreetingRepository greetingRepository;

    public GreetingQuery02(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

//    @PreAuthorize("hasAnyAuthority('USER')")
//    public Greeting greeting(String id) {
//        return greetingRepository.find(id);
//    }

    //    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public Publisher<Iterable<Greeting>> greetings() {
        return Mono.fromCallable(() -> greetingRepository.findAll());
    }

}
