package ru.isands.test.estore.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.Position;
import ru.isands.test.estore.dao.entity.Store;

import java.util.List;
import java.util.Optional;

public interface PositionService {
    Page<Position> findAllByPages(Pageable pageable);
    List<Position> findAllPositions();
    Optional<Position> findPositionById(Long id);

    Position savePosition(Position position);

    Position updatePosition(Long id, Position position);
}
