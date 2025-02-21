package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.PurchaseType;
import ru.isands.test.estore.dao.repo.PurchaseTypeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PurchaseTypeServiceImpl implements PurchaseTypeService {
    private final PurchaseTypeRepository purchaseTypeRepository;
    @Override
    public List<PurchaseType> findAllPurchaseTypes() {
        return purchaseTypeRepository.findAll();
    }

    @Override
    public Optional<PurchaseType> findPurchaseTypeById(Long id) {
        return purchaseTypeRepository.findById(id);
    }

    @Override
    public PurchaseType savePurchaseType(PurchaseType purchaseType) {
       return purchaseTypeRepository.save(purchaseType);
    }

    @Override
    public PurchaseType updatePurchaseType(Long id, PurchaseType purchaseType) {
        purchaseType.setId(id);
        return purchaseTypeRepository.save(purchaseType);
    }
}
