package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}