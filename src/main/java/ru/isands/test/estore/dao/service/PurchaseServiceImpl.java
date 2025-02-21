package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dao.repo.PurchaseRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    @Override
    public List<Purchase> findAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Optional<Purchase> findPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase updatePurchase(Long id, Purchase purchase) {
        purchase.setId(id);
        return purchaseRepository.save(purchase);
    }
}
