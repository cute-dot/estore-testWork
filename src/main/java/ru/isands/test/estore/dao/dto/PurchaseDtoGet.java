package ru.isands.test.estore.dao.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link ru.isands.test.estore.dao.entity.Purchase}
 */
@Value
public class PurchaseDtoGet implements Serializable {
    Long id;
    String electronicsName;
    Long employeeId;
    String storeAddress;
    @NotNull
    Instant purchaseDatetime;
    String purchaseTypeName;
}