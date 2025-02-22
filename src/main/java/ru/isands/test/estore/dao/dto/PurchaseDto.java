package ru.isands.test.estore.dao.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link ru.isands.test.estore.dao.entity.Purchase}
 */
@Value
public class PurchaseDto implements Serializable {
    Long id;
    Long electronicsId;
    Long employeeId;
    Long storeId;
    @NotNull
    Instant purchaseDatetime;
    Long purchaseTypeId;
}