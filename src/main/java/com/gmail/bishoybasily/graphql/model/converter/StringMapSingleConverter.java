package com.gmail.bishoybasily.graphql.model.converter;

import com.gmail.bishoybasily.graphql.utilities.MapUtilities;
import com.gmail.bishoybasily.graphql.utilities.ObjectUtilities;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

public class StringMapSingleConverter implements AttributeConverter<Map<String, String>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, String> value) {
        if (!ObjectUtilities.isEmpty(value))
            return MapUtilities.toJson(value);
        return null;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String value) {
        if (!ObjectUtilities.isEmpty(value))
            return MapUtilities.fromJson(value);
        return new HashMap<>();
    }

}
