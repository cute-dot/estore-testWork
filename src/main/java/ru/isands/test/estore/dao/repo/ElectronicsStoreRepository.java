package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.ElectronicsStoreId;

import java.util.Optional;

public interface ElectronicsStoreRepository extends JpaRepository<ElectronicsStore, ElectronicsStoreId> {

    Optional<ElectronicsStore> findByElectronicsIdAndStoreId(Long electronicsId, Long storeId);
}