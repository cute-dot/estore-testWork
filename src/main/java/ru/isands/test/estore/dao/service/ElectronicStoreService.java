package ru.isands.test.estore.dao.service;


import ru.isands.test.estore.dao.dto.ElectronicsStoreDtoPost;
import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.ElectronicsStoreId;

import java.util.List;
import java.util.Optional;

public interface ElectronicStoreService {

    boolean isElectronicsAvailable(Long electronicsId, Long storeId);
    List<ElectronicsStore> findAllElectronicStores();
    Optional<ElectronicsStore> findElectronicStoreById(ElectronicsStoreId electronicsStoreId);

    void saveElectronicStore(ElectronicsStoreDtoPost electronicStore);

    void updateElectronicStore(ElectronicsStoreId electronicsStoreId, ElectronicsStore electronicStore);

}
