package ru.isands.test.estore.dao.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.entity.Store;
import ru.isands.test.estore.dao.repo.ElectronicRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ElectronicServiceImpl implements ElectronicService {
    private final ElectronicRepository electronicRepository;

    @Override
    public List<Electronic> findAllElectronics() {
        return electronicRepository.findAll();
    }

    @Override
    public Optional<Electronic> findElectronicById(Long id) {
        return electronicRepository.findById(id);
    }

    @Override
    public Electronic saveElectronic(Electronic electronic) {
        return electronicRepository.save(electronic);
    }

    @Override
    public Electronic updateElectronic(Long id, Electronic electronic) {
        electronic.setId(id);
        return electronicRepository.save(electronic);
    }
}
