package ru.isands.test.estore.dao.mapper;

import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.dto.ElectronicsStoreDtoPost;
import ru.isands.test.estore.dao.dto.PurchaseDto;
import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.Purchase;


@Component
public class ElectronicsStoreMappper {

    public ElectronicsStore toEntity(ElectronicsStoreDtoPost electronicsStoreDtoPost) {
        if (electronicsStoreDtoPost == null) {
            return null;
        }
        ElectronicsStore electronicsStore = new ElectronicsStore();
        electronicsStore.setQuantity(electronicsStoreDtoPost.getQuantity());
        return electronicsStore;
    }
}
