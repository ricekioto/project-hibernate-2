package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "hibernate_project2", name = "store")
@FieldDefaults(level = PRIVATE)
public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Byte id;

    @ToString.Exclude
    @OneToOne()
    @JoinColumn(name = "manager_staff_id")
    Staff staff;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    Address address;

    @Column(name = "last_update")
    @UpdateTimestamp
    LocalDateTime lastUpdate;

    @ToString.Exclude
    @OneToMany(mappedBy = "store")
    List<Inventory> inventory;

    @ToString.Exclude
    @OneToMany(mappedBy = "store")
    List<Customer> customers;
}
