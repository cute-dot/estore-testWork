package ru.isands.test.estore.dao.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.entity.ElectronicsType;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dao.entity.Store;
import ru.isands.test.estore.dao.repo.ElectronicRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ElectronicServiceImpl implements ElectronicService {
    private final ElectronicRepository electronicRepository;
    private final ElectronicsTypeServiceImpl electronicsTypeService;

    @Override
    public Page<Electronic> findAllByPages(Pageable pageable) {
        return electronicRepository.findAll(pageable);
    }

    @Override
    public List<Electronic> findAllElectronics() {
        return electronicRepository.findAll();
    }

    @Override
    public Optional<Electronic> findElectronicById(Long id) {
        return electronicRepository.findById(id);
    }

    @Override
    public void saveElectronic(Electronic electronic, Long typeId) {
        electronicRepository.save(setElTypeId(electronic, typeId));
    }

    @Override
    public void updateElectronic(Long id, Electronic electronic, Long typeId) {
        electronic.setId(id);
        electronicRepository.save(setElTypeId(electronic, typeId));
    }

    private Electronic setElTypeId(Electronic electronic, Long typeId){
        ElectronicsType electronicsType = electronicsTypeService.findElectronicsTypeById(typeId)
                .orElseThrow(() -> new RuntimeException("ElectronicType with ID " + typeId + " not found"));
        electronic.setType(electronicsType);
        return electronic;
    }
}
