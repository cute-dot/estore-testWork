package ru.isands.test.estore.dao.repo;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Position;
import ru.isands.test.estore.dao.entity.Store;

public interface PositionRepository extends JpaRepository<Position, Long> {
    @NonNull
    Page<Position> findAll(@NonNull Pageable pageable);
}