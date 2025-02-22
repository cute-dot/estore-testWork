package ru.isands.test.estore.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotNull
    @Column(name = "address", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String address;

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private Set<ElectronicsStore> electronicsStores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private Set<Employee> employees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private Set<Purchase> purchases = new LinkedHashSet<>();

}