package ru.isands.test.estore.dao.service;

import ru.isands.test.estore.dao.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    List<Store> findAllStores();
    Optional<Store> findStoreById(Long id);

    Store saveStore(Store store);

    Store updateStore(Long id, Store store);
}
