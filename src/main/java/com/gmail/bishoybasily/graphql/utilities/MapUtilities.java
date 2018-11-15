package com.gmail.bishoybasily.graphql.utilities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapUtilities {

    private static Gson GSON = new Gson();

    public static Map<String, Set<String>> strings(String key, Set<String> values) {
        Map<String, Set<String>> map = new HashMap<>();
        map.put(key, values);
        return map;
    }

    public static Map<String, Set<Boolean>> booleans(String key, Set<Boolean> values) {
        Map<String, Set<Boolean>> map = new HashMap<>();
        map.put(key, values);
        return map;
    }

    public static <K, V> String toJson(Map<K, V> map) {
        return GSON.toJson(map);
    }

    public static <K, V> Map<K, V> fromJson(String json) {
        return GSON.fromJson(json, new TypeToken<Map<K, V>>() {
        }.getType());
    }

    public static <K, V> Map<K, V> simple(K k, V v) {
        Map<K, V> map = new HashMap<>();
        map.put(k, v);
        return map;
    }
}
