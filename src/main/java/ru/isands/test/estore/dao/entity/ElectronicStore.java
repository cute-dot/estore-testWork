package ru.isands.test.estore.dao.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "electronics_store")
public class ElectronicStore {

    @EmbeddedId
    private ElectronicsStoreId id;

    @ManyToOne
    @MapsId("electronicsId")
    @JoinColumn(name = "electronics_id", nullable = false)
    private Electronic electronics;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}