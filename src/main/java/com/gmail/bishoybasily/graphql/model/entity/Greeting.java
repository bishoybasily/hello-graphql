package com.gmail.bishoybasily.graphql.model.entity;

public class Greeting {

    private String id;
    private String message;

    public Greeting() {
    }

    public Greeting(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public Greeting setId(String id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Greeting setMessage(String message) {
        this.message = message;
        return this;
    }
}
