package com.gmail.bishoybasily.graphql.model.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.gmail.bishoybasily.graphql.model.entity.Greeting;
import com.gmail.bishoybasily.graphql.model.repository.GreetingRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GreetingMutation implements GraphQLMutationResolver {

    private GreetingRepository greetingRepository;

    public GreetingMutation(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    //    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Publisher<Greeting> greeting(String message) {
        return Mono.fromCallable(() -> greetingRepository.save(new Greeting().setMessage(message)));
    }

}
