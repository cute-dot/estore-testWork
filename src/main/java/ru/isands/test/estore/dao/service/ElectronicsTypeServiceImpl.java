package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ElectronicsType> findAllByPages(Pageable pageable) {
        return electronicsTypeRepository.findAll(pageable);
    }

    @Override
    public List<ElectronicsType> findAllElectronicsTypes() {
        return electronicsTypeRepository.findAll();
    }

    @Override
    public Optional<ElectronicsType> findElectronicsTypeById(Long id) {
        return electronicsTypeRepository.findById(id);
    }

    @Override
    public void saveElectronicsType(ElectronicsType electronicsType) {
        electronicsTypeRepository.save(electronicsType);
    }

    @Override
    public void updateElectronicsType(Long id, ElectronicsType electronicsType) {
        electronicsType.setId(id);
        electronicsTypeRepository.save(electronicsType);
    }
}
