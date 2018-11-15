package com.gmail.bishoybasily.graphql.utilities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Set;

public class SetUtilities {

    private static Gson GSON = new Gson();

    public static <E> String toJson(Set<E> set) {
        return GSON.toJson(set);
    }

    public static <E> Set<E> fromJson(String json) {
        return GSON.fromJson(json, new TypeToken<Set<E>>() {
        }.getType());
    }

}
