package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@ToString@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "hibernate_project2", name = "country")
@FieldDefaults(level = PRIVATE)
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Short id;

    @Column(length = 50)
    String country;

    @Column(name = "last_update")
    @UpdateTimestamp
    LocalDateTime lastUpdate;

    @ToString.Exclude
    @OneToMany(mappedBy = "country")
    List<City> cities;
}
