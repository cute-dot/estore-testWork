package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.PurchaseType;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
