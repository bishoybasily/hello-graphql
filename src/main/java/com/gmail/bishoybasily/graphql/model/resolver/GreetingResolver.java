package com.gmail.bishoybasily.graphql.model.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.gmail.bishoybasily.graphql.model.entity.Greeting;
import org.springframework.stereotype.Component;

@Component
public class GreetingResolver implements GraphQLResolver<Greeting> {

    public String getComplex(Greeting greeting) {
        System.out.println("Trying hardly to get greeting: " + greeting.getId() + " complex value ....");
        return "I'm a complex value";
    }

}
