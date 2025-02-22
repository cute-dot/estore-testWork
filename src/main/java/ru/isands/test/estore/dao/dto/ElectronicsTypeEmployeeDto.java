package ru.isands.test.estore.dao.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ru.isands.test.estore.dao.entity.ElectronicsTypeEmployee}
 */
@Value
public class ElectronicsTypeEmployeeDto implements Serializable {
    Long idElectronicsTypeId;
    Long idEmployeeId;
}