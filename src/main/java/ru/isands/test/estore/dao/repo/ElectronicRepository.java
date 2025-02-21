package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Electronic;

public interface ElectronicRepository extends JpaRepository<Electronic, Long> {
}