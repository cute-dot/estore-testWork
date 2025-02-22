package ru.isands.test.estore.dao.repo;


import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Purchase;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @NonNull
    Page<Purchase> findAll(@NonNull Pageable pageable);

    List<Purchase> findAllByOrderByPurchaseDatetimeAsc();
}