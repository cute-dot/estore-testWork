package ru.isands.test.estore.dao.repo;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.Position;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @NonNull
    Page<Employee> findAll(@NonNull Pageable pageable);
}