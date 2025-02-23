package ru.isands.test.estore.dao.dto;

import lombok.Value;

@Value
public class EmployeeTotalRevenueDTO {
    Long employee_id;
    double totalRevenue;
}
