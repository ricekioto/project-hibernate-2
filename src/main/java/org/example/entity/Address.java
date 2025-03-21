package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "hibernate_project2", name = "address")
@FieldDefaults(level = PRIVATE)
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Short id;

    @Column(name = "address", length = 50)
    String address;

    @Column(name = "address2", length = 50)
    String address2;

    @Column(length = 20)
    String district;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    City city;

    @Column(name = "postal_code", length = 10)
    String postalCode;

    @Column(length = 20)
    String phone;

    @Column(name = "last_update")
    @UpdateTimestamp
    LocalDateTime lastUpdate;

    @OneToOne(mappedBy = "address")
    Staff staff;

    @ToString.Exclude
    @OneToMany(mappedBy = "address")
    List<Store> store;

    @ToString.Exclude
    @OneToMany(mappedBy = "address")
    List<Customer> customers;
}
