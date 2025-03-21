package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "hibernate_project2", name = "payment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Short id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    Staff staff;

    @ToString.Exclude
    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    Rental rental;

    BigDecimal amount;

    @Column(name = "payment_date")
    @CreationTimestamp
    LocalDateTime paymentDate;


    @Column(name = "last_update")
    @UpdateTimestamp
    LocalDateTime lastUpdate;
}
