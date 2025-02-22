package ru.isands.test.estore.dao.mapper;

import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.dto.EmployeeDtoPost;
import ru.isands.test.estore.dao.entity.Employee;

@Component
public class EmployeeMapper {

    public EmployeeDtoPost toDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        return new EmployeeDtoPost(
                employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getSurname(),
                employee.getBirthDate(),
                employee.getPosition().getId(),
                employee.getStore().getId(),
                employee.getGender()
        );
    }

    public Employee toEntity(EmployeeDtoPost employeeDtoPost) {
        if (employeeDtoPost == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(employeeDtoPost.getId());
        employee.setLastName(employeeDtoPost.getLastName());
        employee.setFirstName(employeeDtoPost.getFirstName());
        employee.setSurname(employeeDtoPost.getSurname());
        employee.setBirthDate(employeeDtoPost.getBirthDate());
        employee.setGender(employeeDtoPost.getGender());

        return employee;
    }
}
