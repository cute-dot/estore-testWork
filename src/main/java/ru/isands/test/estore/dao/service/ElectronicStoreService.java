package ru.isands.test.estore.dao.service;


import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.ElectronicsStoreId;

import java.util.List;
import java.util.Optional;

public interface ElectronicStoreService {

    List<ElectronicsStore> findAllElectronicStores();
    Optional<ElectronicsStore> findElectronicStoreById(ElectronicsStoreId electronicsStoreId);

    ElectronicsStore saveElectronicStore(ElectronicsStore electronicStore);

    ElectronicsStore updateElectronicStore(ElectronicsStoreId electronicsStoreId, ElectronicsStore electronicStore);

}
