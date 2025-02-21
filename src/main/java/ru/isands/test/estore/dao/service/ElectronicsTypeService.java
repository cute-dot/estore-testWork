package ru.isands.test.estore.dao.service;

import ru.isands.test.estore.dao.entity.ElectronicsType;


import java.util.List;
import java.util.Optional;

public interface ElectronicsTypeService {
    List<ElectronicsType> findAllElectronicsTypes();
    Optional<ElectronicsType> findElectronicsTypeById(Long id);

    ElectronicsType saveElectronicsType(ElectronicsType electronicsType);

    ElectronicsType updateElectronicsType(Long id, ElectronicsType electronicsType);
}
