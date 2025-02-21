package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}