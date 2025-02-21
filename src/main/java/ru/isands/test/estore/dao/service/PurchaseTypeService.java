package ru.isands.test.estore.dao.service;

import ru.isands.test.estore.dao.entity.PurchaseType;

import java.util.List;
import java.util.Optional;

public interface PurchaseTypeService {
    List<PurchaseType> findAllPurchaseTypes();
    Optional<PurchaseType> findPurchaseTypeById(Long id);

    PurchaseType savePurchaseType(PurchaseType purchaseType);

    PurchaseType updatePurchaseType(Long id, PurchaseType purchaseType);
}
