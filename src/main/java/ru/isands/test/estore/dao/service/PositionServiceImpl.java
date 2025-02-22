package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Position;
import ru.isands.test.estore.dao.repo.PositionRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    @Override
    public Page<Position> findAllByPages(Pageable pageable) {
        return positionRepository.findAll(pageable);
    }

    @Override
    public List<Position> findAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Position> findPositionById(Long id) {
        return positionRepository.findById(id);
    }

    @Override
    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Position updatePosition(Long id, Position position) {
        position.setId(id);
        return positionRepository.save(position);
    }
}
