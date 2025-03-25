package org.example.entity;

import jakarta.persistence.*;
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
@Table(schema = "hibernate_project2", name = "inventory")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Inventory {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "film_id")
    Film film;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "store_id")
    Store store;

    @Column(name = "last_update")
    @UpdateTimestamp
    LocalDateTime lastUpdate;

    @ToString.Exclude
    @OneToMany(mappedBy = "inventory",fetch = FetchType.LAZY)
    List<Rental> rentals;
}
