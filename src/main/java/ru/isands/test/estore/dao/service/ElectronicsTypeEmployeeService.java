package ru.isands.test.estore.dao.service;

import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployee;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployeeId;

import java.util.List;
import java.util.Optional;

public interface ElectronicsTypeEmployeeService {
    List<ElectronicsTypeEmployee> findAllElectronicsTypeEmployee();
    Optional<ElectronicsTypeEmployee> findElectronicsTypeEmployeeById(ElectronicsTypeEmployeeId id);

    ElectronicsTypeEmployee saveElectronicsTypeEmployee(ElectronicsTypeEmployee electronicsTypeEmployee);

    ElectronicsTypeEmployee updateElectronicsTypeEmployee(ElectronicsTypeEmployeeId id, ElectronicsTypeEmployee electronicsTypeEmployee);
}
