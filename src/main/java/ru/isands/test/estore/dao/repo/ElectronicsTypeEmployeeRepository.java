package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployee;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployeeId;

public interface ElectronicsTypeEmployeeRepository extends JpaRepository<ElectronicsTypeEmployee, ElectronicsTypeEmployeeId> {
}