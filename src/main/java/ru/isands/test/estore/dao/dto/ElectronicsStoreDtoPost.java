package ru.isands.test.estore.dao.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link ru.isands.test.estore.dao.entity.ElectronicsStore}
 */
@Value
public class ElectronicsStoreDtoPost implements Serializable {
    Long idElectronicsId;
    Long idStoreId;
    @NotNull
    Integer quantity;
}