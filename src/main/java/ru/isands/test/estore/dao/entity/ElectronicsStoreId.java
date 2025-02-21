package ru.isands.test.estore.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ElectronicsStoreId implements Serializable {
    private static final long serialVersionUID = 7240274076429675993L;
    @NotNull
    @Column(name = "electronics_id", nullable = false)
    private Long electronicsId;

    @NotNull
    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ElectronicsStoreId entity = (ElectronicsStoreId) o;
        return Objects.equals(this.storeId, entity.storeId) &&
                Objects.equals(this.electronicsId, entity.electronicsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, electronicsId);
    }

}