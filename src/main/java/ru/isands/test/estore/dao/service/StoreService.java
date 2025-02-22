package ru.isands.test.estore.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.dto.PurchaseDtoGet;
import ru.isands.test.estore.dao.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Page<Store> findAllByPages(Pageable pageable);
    List<Store> findAllStores();
    Optional<Store> findStoreById(Long id);

    Store saveStore(Store store);

    Store updateStore(Long id, Store store);
}
