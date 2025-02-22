package ru.isands.test.estore.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.dto.PurchaseDtoGet;
import ru.isands.test.estore.dao.entity.ElectronicsType;


import java.util.List;
import java.util.Optional;

public interface ElectronicsTypeService {
    Page<ElectronicsType> findAllByPages(Pageable pageable);
    List<ElectronicsType> findAllElectronicsTypes();
    Optional<ElectronicsType> findElectronicsTypeById(Long id);

    void saveElectronicsType(ElectronicsType electronicsType);

    void updateElectronicsType(Long id, ElectronicsType electronicsType);
}
