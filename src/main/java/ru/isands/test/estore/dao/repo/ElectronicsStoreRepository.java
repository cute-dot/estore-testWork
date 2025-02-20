package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.ElectronicStore;
import ru.isands.test.estore.dao.entity.ElectronicsStoreId;

public interface ElectronicsStoreRepository extends JpaRepository<ElectronicStore, ElectronicsStoreId> {
}
