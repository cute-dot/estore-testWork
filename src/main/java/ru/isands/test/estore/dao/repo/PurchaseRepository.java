package ru.isands.test.estore.dao.repo;


import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.isands.test.estore.dao.entity.Purchase;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT s.employee.id, COUNT(s.id) AS totalSales FROM Purchase s " +
//            "WHERE s.purchaseDatetime >= :startDate " +
            "GROUP BY s.employee.id " +
            "ORDER BY totalSales DESC")

    List<Object[]> findTopEmployeesBySalesCount(@Param("startDate") Instant startDate);


    @Query("SELECT p.employee.id, SUM(e.price) AS totalRevenue " +
            "FROM Purchase p " +
            "JOIN p.electronics e " +
//            "WHERE p.purchaseDatetime >= :startDate " +
            "GROUP BY p.employee.id " +
            "ORDER BY totalRevenue DESC")
    List<Object[]> findTopEmployeesBySalesAmount(@Param("startDate") Instant startDate);
    @NonNull
    Page<Purchase> findAll(@NonNull Pageable pageable);

    List<Purchase> findAllByOrderByPurchaseDatetimeAsc();
}