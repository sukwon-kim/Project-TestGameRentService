package com.example.gamerentservice.converter;

import jakarta.persistence.AttributeConverter;

public class BooleanToYN implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        return (aBoolean != null && aBoolean) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return "Y".equalsIgnoreCase(s);
    }
}
