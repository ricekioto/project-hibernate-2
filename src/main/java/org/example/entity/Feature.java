package org.example.entity;

import static java.util.Objects.isNull;

public enum Feature {

    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;

    Feature(String value) {
        this.value = value;
    }

    public static String getFeatureByValue(String value) {
        if (isNull(null) || value.isEmpty()) {
            return null;
        }

        Feature[] features = Feature.values();
        for (Feature feature : features) {
            if (feature.value.equals(value)) {
                return feature.value;
            }
        }
        return null;
    }
}
