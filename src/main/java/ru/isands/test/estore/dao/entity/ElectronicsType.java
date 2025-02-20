package ru.isands.test.estore.dao.entity;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "electronics_types")
public class ElectronicsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    // Отображение обратной стороны связи many-to-many с Employee
    @ManyToMany(mappedBy = "electronicsTypes")
    private Set<Employee> employees;
}