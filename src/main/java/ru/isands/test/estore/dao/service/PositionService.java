package ru.isands.test.estore.dao.service;

import ru.isands.test.estore.dao.entity.Position;

import java.util.List;
import java.util.Optional;

public interface PositionService {
    List<Position> findAllPositions();
    Optional<Position> findPositionById(Long id);

    Position savePosition(Position position);

    Position updatePosition(Long id, Position position);
}
