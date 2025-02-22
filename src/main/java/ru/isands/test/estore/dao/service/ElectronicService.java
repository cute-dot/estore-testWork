package ru.isands.test.estore.dao.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.dto.ElectronicDto;
import ru.isands.test.estore.dao.dto.PurchaseDtoGet;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.entity.ElectronicsType;
import ru.isands.test.estore.dao.entity.Store;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ElectronicService {

    Page<Electronic> findAllByPages(Pageable pageable);
    List<Electronic> findAllElectronics();
    Optional<Electronic> findElectronicById(Long id);

    void saveElectronic(Electronic electronic, Long typeId);

    void updateElectronic(Long id, Electronic electronic, Long typeId);

}
