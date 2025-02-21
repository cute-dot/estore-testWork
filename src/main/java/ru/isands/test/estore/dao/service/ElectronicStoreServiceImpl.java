package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.ElectronicsStoreId;
import ru.isands.test.estore.dao.repo.ElectronicsStoreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ElectronicStoreServiceImpl implements ElectronicStoreService {
    private final ElectronicsStoreRepository electronicsStoreRepository;
    @Override
    public List<ElectronicsStore> findAllElectronicStores() {
        return electronicsStoreRepository.findAll();
    }

    @Override
    public Optional<ElectronicsStore> findElectronicStoreById(ElectronicsStoreId electronicsStoreId) {
        return electronicsStoreRepository.findById(electronicsStoreId);
    }

    @Override
    public ElectronicsStore saveElectronicStore(ElectronicsStore electronicStore) {
        return electronicsStoreRepository.save(electronicStore);
    }

    @Override
    public ElectronicsStore updateElectronicStore(ElectronicsStoreId electronicsStoreId, ElectronicsStore electronicStore) {
        electronicStore.setId(electronicsStoreId);
        return electronicsStoreRepository.save(electronicStore);
    }

}
