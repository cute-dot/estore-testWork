package ru.isands.test.estore.dao.repo;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dao.entity.PurchaseType;

public interface PurchaseTypeRepository extends JpaRepository<PurchaseType, Long> {
    @NonNull
    Page<PurchaseType> findAll(@NonNull Pageable pageable);
}