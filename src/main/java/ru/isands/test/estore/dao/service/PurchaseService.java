package ru.isands.test.estore.dao.service;

import ru.isands.test.estore.dao.entity.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    List<Purchase> findAllPurchases();
    Optional<Purchase> findPurchaseById(Long id);

    Purchase savePurchase(Purchase purchase);

    Purchase updatePurchase(Long id, Purchase purchase);
}
