package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.dto.ElectronicsStoreDtoPost;
import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.ElectronicsStoreId;
import ru.isands.test.estore.dao.mapper.ElectronicsStoreMappper;
import ru.isands.test.estore.dao.repo.ElectronicsStoreRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ElectronicStoreServiceImpl implements ElectronicStoreService {
    private final ElectronicsStoreRepository electronicsStoreRepository;
    private final ElectronicsStoreMappper electr;
    private final StoreServiceImpl storeService;
    private final ElectronicServiceImpl electronicService;
    @Override
    public List<ElectronicsStore> findAllElectronicStores() {
        return electronicsStoreRepository.findAll();
    }

    @Override
    public Optional<ElectronicsStore> findElectronicStoreById(ElectronicsStoreId electronicsStoreId) {

        return electronicsStoreRepository.findById(electronicsStoreId);
    }

    @Transactional
    @Override
    public void saveElectronicStore(ElectronicsStoreDtoPost electronicsStoreDtoPost) {
        electronicsStoreRepository.save(setElectrAndStore(electronicsStoreDtoPost));
    }

    @Transactional
    @Override
    public void updateElectronicStore(ElectronicsStoreId electronicsStoreId, ElectronicsStore electronicStore) {
        electronicStore.setId(electronicsStoreId);
        electronicsStoreRepository.save(electronicStore);
    }

    private ElectronicsStore setElectrAndStore(ElectronicsStoreDtoPost electronicsStoreDtoPost){
        ElectronicsStoreId electronicsStoreId = new ElectronicsStoreId();
        ElectronicsStore electronicsStore = electr.toEntity(electronicsStoreDtoPost);

        Long store_id = electronicsStoreDtoPost.getIdStoreId();
        Long elect_id = electronicsStoreDtoPost.getIdElectronicsId();

        electronicsStoreId.setStoreId(store_id);
        electronicsStoreId.setElectronicsId(elect_id);

        electronicsStore.setStore(storeService.findStoreById(store_id)
                .orElseThrow(() -> new RuntimeException("Store with ID " + store_id + " not found")));
        electronicsStore.setElectronics(electronicService.findElectronicById(elect_id)
                .orElseThrow(() -> new RuntimeException("Store with ID " + elect_id + " not found")));
        electronicsStore.setId(electronicsStoreId);
        return electronicsStore;
    }
}
