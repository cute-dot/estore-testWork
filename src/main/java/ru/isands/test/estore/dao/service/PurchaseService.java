package ru.isands.test.estore.dao.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.dto.EmployeeTotalRevenueDTO;
import ru.isands.test.estore.dao.dto.EmployeeTotalSalesDTO;
import ru.isands.test.estore.dao.dto.PurchaseDtoGet;
import ru.isands.test.estore.dao.entity.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {

    List<EmployeeTotalSalesDTO> getTopEmployeesBySalesCount();
    List<EmployeeTotalRevenueDTO> getTopEmployeesBySalesAmount();

    List<PurchaseDtoGet> findAllPurchasesSorted();

    Page<PurchaseDtoGet> findAllByPages(Pageable pageable);

    List<PurchaseDtoGet> findAllPurchases();
    Optional<Purchase> findPurchaseById(Long id);

    void savePurchase(Purchase purchase, Long electronicId, Long employeeId, Long storeId, Long purchaseTypeId);

    void updatePurchase(Long id, Purchase purchase, Long electronicId, Long employeeId, Long storeId, Long purchaseTypeId);
}
