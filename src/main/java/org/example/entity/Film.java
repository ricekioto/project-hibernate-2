package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "hibernate_project2", name = "film")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    Short id;

    @Column(name = "title", length = 128)
    String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    String description;

    @Column(name = "release_year", columnDefinition = "year")
    Integer year;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    Language languageId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    Language originalLanguageId;

    @Column(name = "rental_duration")
    Byte rentalDuration;

    @Column(name = "rental_rate")
    BigDecimal rentalRate;

    @Column(name = "length")
    Short length;

    @Column(name = "replacement_cost")
    BigDecimal replacementCost;

    @ToString.Exclude
    @Convert(converter = RentingConventer.class)
    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    Rating rating;

    @ToString.Exclude
    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    String specialFeatures;

    @Column(name = "last_update")
    @UpdateTimestamp
    LocalDateTime lastUpdate;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    Set<Actor> actors;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    Set<Category> categories;

    @ToString.Exclude
    @OneToOne(mappedBy = "film")
    FilmText filmText;

    @ToString.Exclude
    @OneToMany(mappedBy = "film")
    List<Inventory> inventories;

    public Set<Feature> getSpecialFeatures() {
        if (isNull(specialFeatures) || specialFeatures.isEmpty()) {
            return null;
        }

        Set<Feature> result = new HashSet<>();
        String[] features = specialFeatures.split(",");
        for (String feature : features) {
            result.add(Feature.getFeatureByValue(feature));
        }
        result.remove(null);
        return result;
    }

    public void setSpecialFeatures(Set<Feature> features) {
        if (isNull(features)) {
            features = null;
        } else {
            features.stream()
                    .map(Feature::getValue)
                    .collect(Collectors.joining(","));


        }
    }
}
