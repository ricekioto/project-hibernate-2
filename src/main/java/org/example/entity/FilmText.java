package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "hibernate_project2", name = "film_text")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmText {
    @Id
    @Column(name = "film_id")
    Short id;

    String title;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    String description;

    @ToString.Exclude
    @OneToOne()
    @JoinColumn(name = "film_id")
    Film film;
}
