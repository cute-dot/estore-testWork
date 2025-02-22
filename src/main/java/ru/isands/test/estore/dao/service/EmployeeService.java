package ru.isands.test.estore.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.dto.PurchaseDtoGet;
import ru.isands.test.estore.dao.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Page<Employee> findAllByPages(Pageable pageable);
    List<Employee> findAllEmployees();
    Optional<Employee> findEmployeeById(Long id);

    void saveEmployee(Employee employee, Long positionId, Long storeId);

    void updateEmployee(Long id, Employee employee , Long positionId, Long storeId);
}
