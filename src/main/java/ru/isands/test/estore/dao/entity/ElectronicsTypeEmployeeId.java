package ru.isands.test.estore.dao.entity;

import lombok.Getter;
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
public class ElectronicsTypeEmployeeId implements Serializable {
    private static final long serialVersionUID = 1980189144368042463L;
    @NotNull
    @Column(name = "electronics_type_id", nullable = false)
    private Long electronicsTypeId;

    @NotNull
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ElectronicsTypeEmployeeId entity = (ElectronicsTypeEmployeeId) o;
        return Objects.equals(this.electronicsTypeId, entity.electronicsTypeId) &&
                Objects.equals(this.employeeId, entity.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(electronicsTypeId, employeeId);
    }

}