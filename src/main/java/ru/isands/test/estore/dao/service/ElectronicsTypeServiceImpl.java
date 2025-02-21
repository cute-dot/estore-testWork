package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.ElectronicsType;
import ru.isands.test.estore.dao.repo.ElectronicsTypeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ElectronicsTypeServiceImpl implements ElectronicsTypeService {
    private final ElectronicsTypeRepository electronicsTypeRepository;
    @Override
    public List<ElectronicsType> findAllElectronicsTypes() {
        return electronicsTypeRepository.findAll();
    }

    @Override
    public Optional<ElectronicsType> findElectronicsTypeById(Long id) {
        return electronicsTypeRepository.findById(id);
    }

    @Override
    public ElectronicsType saveElectronicsType(ElectronicsType electronicsType) {
        return electronicsTypeRepository.save(electronicsType);
    }

    @Override
    public ElectronicsType updateElectronicsType(Long id, ElectronicsType electronicsType) {
        electronicsType.setId(id);
        return electronicsTypeRepository.save(electronicsType);
    }
}
