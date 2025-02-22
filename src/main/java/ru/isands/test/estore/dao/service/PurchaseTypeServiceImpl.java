package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<PurchaseType> findAllByPages(Pageable pageable) {
        return purchaseTypeRepository.findAll(pageable);
    }

    @Override
    public List<PurchaseType> findAllPurchaseTypes() {
        return purchaseTypeRepository.findAll();
    }

    @Override
    public Optional<PurchaseType> findPurchaseTypeById(Long id) {
        return purchaseTypeRepository.findById(id);
    }

    @Override
    public void savePurchaseType(PurchaseType purchaseType) {
       purchaseTypeRepository.save(purchaseType);
    }

    @Override
    public void updatePurchaseType(Long id, PurchaseType purchaseType) {
        purchaseType.setId(id);
        purchaseTypeRepository.save(purchaseType);
    }
}
