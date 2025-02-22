package ru.isands.test.estore.dao.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ru.isands.test.estore.dao.entity.Electronic}
 */
@Value
public class ElectronicDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 150)
    String name;
    Long typeId;
    @NotNull
    BigDecimal price;
    @NotNull
    Integer quantity;
    @NotNull
    Boolean archived;
    String description;
}