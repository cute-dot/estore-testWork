package ru.isands.test.estore.dao.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.isands.test.estore.dao.entity.Employeesdfsdfa;

public interface EmployeeRepositoryывафыва extends JpaRepository<Employeesdfsdfa, Long> {
	
	@Query("select e from Employeesdfsdfa e where e.lastName = ?1 and e.firstName = ?2 and e.patronymic = ?3 and e.birthDate = ?4")
	public Employeesdfsdfa findFullName(String lastName, String firstName, String patronymic, Date birthDate);
	
	@Query("select e from Employeesdfsdfa e where e.positionId = ?1")
	public List<Employeesdfsdfa> findByPosition(Long positionId);
	
}