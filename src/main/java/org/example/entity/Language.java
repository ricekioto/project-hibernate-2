package org.example.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "hibernate_project2", name = "language")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Language {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Byte id;

    @Column(length = 20, columnDefinition = "char")
    String name;

    @Column(name = "last_update")
    @UpdateTimestamp
    LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "languageId")
    List<Film> filmsLanguageId;

    @OneToMany(mappedBy = "originalLanguageId")
    List<Film> filmsOriginalLanguageId;

}
