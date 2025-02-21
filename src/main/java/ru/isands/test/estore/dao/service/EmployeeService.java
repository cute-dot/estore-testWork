package ru.isands.test.estore.dao.service;

import ru.isands.test.estore.dao.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAllEmployees();
    Optional<Employee> findEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);
}
