package org.example.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RentingConventer implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String string) {
        Rating[] ratings = Rating.values();
        for (Rating rating : ratings) {
            if (rating.getValue().equals(string)) {
                return rating;
            }
        }
        return null;
    }
}
