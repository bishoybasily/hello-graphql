package com.gmail.bishoybasily.graphql.utilities;

import java.util.Collection;
import java.util.Map;

/**
 * Created by bishoy on 6/27/17.
 */
public class ObjectUtilities {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().equals("");
    }


    public static boolean isEmpty(Integer value) {
        return value == null;
    }

    public static boolean isEmpty(Enum value) {
        return value == null;
    }

    public static boolean areEquals(Enum val1, Enum val2) {
        if (!isEmpty(val1) && !isEmpty(val2)) return val1.equals(val2);
        else return isEmpty(val1) && isEmpty(val2);
    }

    public static boolean isEmpty(String[] values) {
        return values == null || values.length == 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    public static boolean isEmpty(Object object) {
        return object == null;
    }

}
