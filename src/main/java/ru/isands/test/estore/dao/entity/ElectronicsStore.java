package ru.isands.test.estore.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "electronics_store")
public class ElectronicsStore {
    @EmbeddedId
    private ElectronicsStoreId id;

    @MapsId("electronicsId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "electronics_id", nullable = false)
    @JsonBackReference
    private Electronic electronics;

    @MapsId("storeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    @JsonBackReference
    private Store store;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}