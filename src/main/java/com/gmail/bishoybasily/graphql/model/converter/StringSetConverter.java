package com.gmail.bishoybasily.graphql.model.converter;

import com.gmail.bishoybasily.graphql.utilities.ObjectUtilities;
import com.gmail.bishoybasily.graphql.utilities.SetUtilities;

import javax.persistence.AttributeConverter;
import java.util.HashSet;
import java.util.Set;

public class StringSetConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> value) {
        if (!ObjectUtilities.isEmpty(value))
            return SetUtilities.toJson(value);
        return null;
    }

    @Override
    public Set<String> convertToEntityAttribute(String value) {
        if (!ObjectUtilities.isEmpty(value))
            return SetUtilities.fromJson(value);
        return new HashSet<>();
    }

}
