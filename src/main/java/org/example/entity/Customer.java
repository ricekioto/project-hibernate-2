package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "hibernate_project2", name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Short id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    Store store;

    @Column(name = "first_name", length = 45)
    String firstName;

    @Column(name = "last_name", length = 45)
    String lastName;

    @Column(length = 50)
    String email;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "address_id")
    Address address;

    @Column(name = "active", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    Boolean isActive;

    @Column(name = "create_date")
    @CreationTimestamp
    LocalDateTime createDate;

    @Column(name = "last_update")
    @UpdateTimestamp
    LocalDateTime lastUpdate;

    @ToString.Exclude
    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY)
    List<Rental> rentals;

    @ToString.Exclude
    @OneToMany(mappedBy = "costumer", fetch = FetchType.LAZY)
    List<Payment> payments;
}
