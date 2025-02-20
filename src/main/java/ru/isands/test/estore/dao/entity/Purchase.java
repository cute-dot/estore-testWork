package ru.isands.test.estore.dao.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "electronics_id", nullable = false)
    private Electronic electronics;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "purchase_datetime", nullable = false)
    private LocalDateTime purchaseDatetime;

    @ManyToOne
    @JoinColumn(name = "purchase_type_id", nullable = false)
    private PurchaseType purchaseType;
}