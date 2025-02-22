package ru.isands.test.estore.dao.repo;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.PurchaseType;
import ru.isands.test.estore.dao.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @NonNull
    Page<Store> findAll(@NonNull Pageable pageable);
}