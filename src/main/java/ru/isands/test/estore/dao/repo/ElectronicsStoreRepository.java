package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.ElectronicsStoreId;

public interface ElectronicsStoreRepository extends JpaRepository<ElectronicsStore, ElectronicsStoreId> {
}