package ru.isands.test.estore.dao.service;

import ru.isands.test.estore.dao.dto.ElectronicsTypeEmployeeDto;
import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployee;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployeeId;

import java.util.List;
import java.util.Optional;

public interface ElectronicsTypeEmployeeService {
    List<ElectronicsTypeEmployee> findAllElectronicsTypeEmployee();
    Optional<ElectronicsTypeEmployee> findElectronicsTypeEmployeeById(ElectronicsTypeEmployeeId id);

    void saveElectronicsTypeEmployee(ElectronicsTypeEmployeeDto electronicsTypeEmployeeDto);

    void updateElectronicsTypeEmployee(ElectronicsTypeEmployeeDto electronicsTypeEmployeeDto);
}
