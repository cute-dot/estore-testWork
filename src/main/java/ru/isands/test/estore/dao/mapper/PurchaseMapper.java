package ru.isands.test.estore.dao.mapper;

import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.dto.PurchaseDto;
import ru.isands.test.estore.dao.entity.Purchase;

@Component
public class PurchaseMapper {

    public PurchaseDto toDto(Purchase purchase) {
        if (purchase == null) {
            return null;
        }
        return new PurchaseDto(
                purchase.getId(),
                purchase.getElectronics().getId(),
                purchase.getEmployee().getId(),
                purchase.getStore().getId(),
                purchase.getPurchaseDatetime(),
                purchase.getPurchaseType().getId()
        );
    }

    public Purchase toEntity(PurchaseDto purchaseDto) {
        if (purchaseDto == null) {
            return null;
        }
        Purchase purchase = new Purchase();
        purchase.setId(purchaseDto.getId());
        purchase.setPurchaseDatetime(purchaseDto.getPurchaseDatetime());
        return purchase;
    }
}