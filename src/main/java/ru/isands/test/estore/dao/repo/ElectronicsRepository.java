package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Electronic;

public interface ElectronicsRepository extends JpaRepository<Electronic, Long> {
}