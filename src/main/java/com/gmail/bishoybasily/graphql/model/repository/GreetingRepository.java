package com.gmail.bishoybasily.graphql.model.repository;

import com.gmail.bishoybasily.graphql.model.entity.Greeting;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class GreetingRepository {

    private Map<String, Greeting> greetings;

    public GreetingRepository() {
        greetings = new HashMap<>();


        Stream.of(

                new Greeting().setId("123").setMessage("hello first"),
                new Greeting().setId("456").setMessage("hello second"),
                new Greeting().setId("789").setMessage("hello third")

        ).forEach(greeting -> greetings.put(greeting.getId(), greeting));

    }

    public Greeting save(Greeting greeting) {
        String id = UUID.randomUUID().toString();

        greetings.put(id, greeting);
        greeting.setId(id);

        return greeting;
    }

    public Greeting find(String id) {
        return greetings.get(id);
    }

    public Collection<Greeting> findAll() {
        return greetings.values();
    }
}
