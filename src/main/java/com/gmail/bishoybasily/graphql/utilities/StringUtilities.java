package com.gmail.bishoybasily.graphql.utilities;

import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Random;
import java.util.StringJoiner;

public class StringUtilities {

    public static final String EMPTY = "";
    public static final String UNDERSCORE = "_";

    public static String collapse(String delimiter, String... tokens) {
        StringJoiner stringJoiner = new StringJoiner(delimiter);
        for (String token : tokens)
            stringJoiner.add(token);
        return stringJoiner.toString();
    }

    public static String collapse(String delimiter, Collection<String> tokens) {
        StringJoiner stringJoiner = new StringJoiner(delimiter);
        for (String token : tokens)
            stringJoiner.add(token);
        return stringJoiner.toString();
    }

    public static String[] expand(String delimiter, String line) {

        String[] characters = {"\\", "!", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "]", "^", "_", "`", "{", "|", "}"};
        for (String character : characters)
            if (delimiter.contains(character))
                delimiter = delimiter.replace(character, "\\" + character);

        return line.split(delimiter);
    }

    public static String[] expand(String delimiter, String line, boolean first) {
        int index = first ? line.indexOf(delimiter) : line.lastIndexOf(delimiter);
        return new String[]{line.substring(0, index), line.substring(index + 1)};
    }

    public static String randomNumber() {
        int min = 111111;
        int max = 999999;
        return String.valueOf(new Random().nextInt(max - min + 1) + min);
    }

    public static Mono<String> randomNumberAsync() {
        int min = 111111;
        int max = 999999;
        return Mono.just(String.valueOf(new Random().nextInt(max - min + 1) + min));
    }

    public static String cleanLower(String value) {
        return value.replaceAll(" ", "").trim().toLowerCase();
    }

    public static Mono<String> cleanLowerAsync(String value) {
        return Mono.just(value.replaceAll(" ", "").trim().toLowerCase());
    }

}
