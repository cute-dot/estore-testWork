package ru.isands.test.estore.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.dto.PurchaseDtoGet;
import ru.isands.test.estore.dao.entity.PurchaseType;

import java.util.List;
import java.util.Optional;

public interface PurchaseTypeService {
    Page<PurchaseType> findAllByPages(Pageable pageable);
    List<PurchaseType> findAllPurchaseTypes();
    Optional<PurchaseType> findPurchaseTypeById(Long id);

    void savePurchaseType(PurchaseType purchaseType);

    void updatePurchaseType(Long id, PurchaseType purchaseType);
}
