package ru.isands.test.estore.dao.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.entity.Store;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ElectronicService {

    List<Electronic> findAllElectronics();
    Optional<Electronic> findElectronicById(Long id);

    Electronic saveElectronic(Electronic electronic);

    Electronic updateElectronic(Long id, Electronic electronic);

}
