package ru.isands.test.estore.dao.mapper;


import lombok.Value;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.dto.PurchaseDto;
import ru.isands.test.estore.dao.dto.PurchaseDtoGet;
import ru.isands.test.estore.dao.entity.Purchase;

@Component
public class PurchaseGetMapper {

    public PurchaseDtoGet toDto(Purchase purchase) {
        if (purchase == null) {
            return null;
        }
        return new PurchaseDtoGet(
                purchase.getId(),
                purchase.getElectronics().getName(),
                purchase.getEmployee().getId(),
                purchase.getStore().getName(),
                purchase.getPurchaseDatetime(),
                purchase.getPurchaseType().getName()
        );
    }
}
