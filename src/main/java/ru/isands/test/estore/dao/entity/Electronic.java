package ru.isands.test.estore.dao.entity;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "electronics")
public class Electronic{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ElectronicsType electronicsType;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    @Column(name = "description")
    private String description;
}