package com.gmail.bishoybasily.graphql.model.converter;

import com.gmail.bishoybasily.graphql.utilities.MapUtilities;
import com.gmail.bishoybasily.graphql.utilities.ObjectUtilities;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StringMapMultipleConverter implements AttributeConverter<Map<String, Set<String>>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Set<String>> value) {
        if (!ObjectUtilities.isEmpty(value))
            return MapUtilities.toJson(value);
        return null;
    }

    @Override
    public Map<String, Set<String>> convertToEntityAttribute(String value) {
        if (!ObjectUtilities.isEmpty(value))
            return MapUtilities.fromJson(value);
        return new HashMap<>();
    }

}
