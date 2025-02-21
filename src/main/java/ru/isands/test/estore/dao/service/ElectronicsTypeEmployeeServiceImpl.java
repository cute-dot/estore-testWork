package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployee;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployeeId;
import ru.isands.test.estore.dao.repo.ElectronicsTypeEmployeeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ElectronicsTypeEmployeeServiceImpl implements ElectronicsTypeEmployeeService {

    private final ElectronicsTypeEmployeeRepository electronicsTypeEmployeeRepository;

    @Override
    public List<ElectronicsTypeEmployee> findAllElectronicsTypeEmployee() {
        return electronicsTypeEmployeeRepository.findAll();
    }

    @Override
    public Optional<ElectronicsTypeEmployee> findElectronicsTypeEmployeeById(ElectronicsTypeEmployeeId id) {
        return electronicsTypeEmployeeRepository.findById(id);
    }

    @Override
    public ElectronicsTypeEmployee saveElectronicsTypeEmployee(ElectronicsTypeEmployee electronicsTypeEmployee) {
        return electronicsTypeEmployeeRepository.save(electronicsTypeEmployee) ;
    }

    @Override
    public ElectronicsTypeEmployee updateElectronicsTypeEmployee(ElectronicsTypeEmployeeId id, ElectronicsTypeEmployee electronicsTypeEmployee) {
        electronicsTypeEmployee.setId(id);
        return electronicsTypeEmployeeRepository.save(electronicsTypeEmployee);
    }
}
