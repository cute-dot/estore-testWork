package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Store;
import ru.isands.test.estore.dao.repo.StoreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    @Override
    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> findStoreById(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Long id, Store store) {
        store.setId(id);
        return storeRepository.save(store);
    }
}
