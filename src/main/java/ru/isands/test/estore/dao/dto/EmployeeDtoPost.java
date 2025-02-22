package ru.isands.test.estore.dao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ru.isands.test.estore.dao.entity.Employee}
 */
@Value
public class EmployeeDtoPost implements Serializable {
    Long id;
    @NotNull
    @Size(max = 100)
    String lastName;
    @NotNull
    @Size(max = 100)
    String firstName;
    @Size(max = 100)
    String surname;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    Long positionId;
    Long storeId;
    @NotNull
    Boolean gender;
}