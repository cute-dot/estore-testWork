package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.dto.ElectronicsTypeEmployeeDto;
import ru.isands.test.estore.dao.entity.ElectronicsType;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployee;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployeeId;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.repo.ElectronicsTypeEmployeeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ElectronicsTypeEmployeeServiceImpl implements ElectronicsTypeEmployeeService {

    private final ElectronicsTypeEmployeeRepository electronicsTypeEmployeeRepository;
    private final EmployeeServiceImpl employeeService;
    private final ElectronicsTypeServiceImpl electronicsTypeService;

    @Override
    public List<ElectronicsTypeEmployee> findAllElectronicsTypeEmployee() {
        return electronicsTypeEmployeeRepository.findAll();
    }

    @Override
    public Optional<ElectronicsTypeEmployee> findElectronicsTypeEmployeeById(ElectronicsTypeEmployeeId id) {
        return electronicsTypeEmployeeRepository.findById(id);
    }

    @Override
    public void saveElectronicsTypeEmployee(ElectronicsTypeEmployeeDto electronicsTypeEmployeeDto) {
        electronicsTypeEmployeeRepository.save(setIds(electronicsTypeEmployeeDto));
    }

    @Override
    public void updateElectronicsTypeEmployee(ElectronicsTypeEmployeeDto electronicsTypeEmployeeDto) {

    }

    private ElectronicsTypeEmployee setIds(ElectronicsTypeEmployeeDto electronicsTypeEmployeeDto){
        ElectronicsTypeEmployee electronicsTypeEmployee = new ElectronicsTypeEmployee();
        ElectronicsTypeEmployeeId electronicsTypeEmployeeId = new ElectronicsTypeEmployeeId();

        Long emplyee_Id = electronicsTypeEmployeeDto.getIdEmployeeId();
        Long electronicType_Id = electronicsTypeEmployeeDto.getIdElectronicsTypeId();

        electronicsTypeEmployeeId.setEmployeeId(emplyee_Id);
        electronicsTypeEmployeeId.setElectronicsTypeId(electronicType_Id);

        electronicsTypeEmployee.setId(electronicsTypeEmployeeId);
        electronicsTypeEmployee.setEmployee(employeeService.findEmployeeById(emplyee_Id)
                .orElseThrow(() -> new RuntimeException("Store with ID " + emplyee_Id + " not found")));
        electronicsTypeEmployee.setElectronicsType(electronicsTypeService.findElectronicsTypeById(electronicType_Id)
                .orElseThrow(() -> new RuntimeException("Store with ID " + electronicType_Id + " not found")));

        return electronicsTypeEmployee;
    }


}
