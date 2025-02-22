package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.Position;
import ru.isands.test.estore.dao.entity.Store;
import ru.isands.test.estore.dao.repo.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionServiceImpl positionService;
    private final StoreServiceImpl storeService;

    @Override
    public Page<Employee> findAllByPages(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    @Override
    public void saveEmployee(Employee employee, Long positionId, Long storeId) {
        employeeRepository.save(setPositionAndStoreIds(employee, positionId, storeId));
    }

    @Transactional
    @Override
    public void updateEmployee(Long id, Employee employee, Long positionId, Long storeId) {
        employee.setId(id);
        employeeRepository.save(setPositionAndStoreIds(employee, positionId, storeId));

    }

    private Employee setPositionAndStoreIds(Employee employee, Long positionId, Long storeId){
        Position position = positionService.findPositionById(positionId)
                .orElseThrow(() -> new RuntimeException("Position with ID " + positionId + " not found"));
        Store store = storeService.findStoreById(storeId)
                .orElseThrow(() -> new RuntimeException("Store with ID " + storeId + " not found"));
        employee.setPosition(position);
        employee.setStore(store);
        return employee;
    }
}
