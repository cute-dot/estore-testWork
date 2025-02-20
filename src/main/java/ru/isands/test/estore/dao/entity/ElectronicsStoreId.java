package ru.isands.test.estore.dao.entity;


import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ElectronicsStoreId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "electronics_id")
    private Long electronicsId;

    @Column(name = "store_id")
    private Long storeId;

    public ElectronicsStoreId() {}

    public ElectronicsStoreId(Long electronicsId, Long storeId) {
        this.electronicsId = electronicsId;
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectronicsStoreId)) return false;
        ElectronicsStoreId that = (ElectronicsStoreId) o;
        return Objects.equals(electronicsId, that.electronicsId) &&
                Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(electronicsId, storeId);
    }
}