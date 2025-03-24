package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "hibernate_project2", name = "staff")
@FieldDefaults(level = PRIVATE)
public class Staff {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Byte id;

    @Column(name = "first_name", length = 45)
    String firstName;

    @Column(name = "last_name", length = 45)
    String lastName;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "address_id")
    Address address;

    @Lob
    @Column(columnDefinition = "BLOB")
    byte[] picture;

    @Column(length = 50)
    String email;

    @ToString.Exclude
    @OneToOne()
    @JoinColumn(name = "store_id")
    Store store;

    @Column(name = "active", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    Boolean isActive;

    @Column(length = 16)
    String username;

    @Column(length = 40)
    String password;

    @Column(name = "last_update")
    @UpdateTimestamp
    LocalDateTime lastUpdate;

    @ToString.Exclude
    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY)
    List<Rental> rentals;

    @ToString.Exclude
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    List<Payment> payments;
}
