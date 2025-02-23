package ru.isands.test.estore.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.dto.EmployeeTotalRevenueDTO;
import ru.isands.test.estore.dao.dto.EmployeeTotalSalesDTO;
import ru.isands.test.estore.dao.dto.PurchaseDtoGet;
import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dao.mapper.PurchaseGetMapper;
import ru.isands.test.estore.dao.repo.PurchaseRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final StoreServiceImpl storeService;
    private final EmployeeServiceImpl employeeService;
    private final PurchaseTypeServiceImpl purchaseTypeService;
    private final ElectronicServiceImpl electronicService;
    private final PurchaseGetMapper purchaseGetMapper;


    @Override
    public List<EmployeeTotalSalesDTO> getTopEmployeesBySalesCount() {
        LocalDateTime lastYearStart = LocalDate.now().minusYears(1).atStartOfDay();
        Instant startDate = lastYearStart.atZone(ZoneId.systemDefault()).toInstant();

        List<Object[]> results = purchaseRepository.findTopEmployeesBySalesCount(startDate);
        System.out.println(startDate);
        return results.stream()
                .map(row -> new EmployeeTotalSalesDTO(
                        ((Long) row[0]),
                        ((Number) row[1]).intValue()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeTotalRevenueDTO> getTopEmployeesBySalesAmount() {
        LocalDateTime lastYearStart = LocalDate.now().minusYears(1).atStartOfDay();
        Instant startDate = lastYearStart.atZone(ZoneId.systemDefault()).toInstant();

        List<Object[]> results = purchaseRepository.findTopEmployeesBySalesAmount(startDate);
        return results.stream()
                .map(row -> new EmployeeTotalRevenueDTO(
                        ((Long) row[0]),  // ID сотрудника
                        ((Number) row[1]).doubleValue()  // Сумма продаж
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseDtoGet> findAllPurchasesSorted() {
        List<Purchase> purchases = purchaseRepository.findAllByOrderByPurchaseDatetimeAsc();
        return purchases.stream()
                .map(purchaseGetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PurchaseDtoGet> findAllByPages(Pageable pageable) {
        return purchaseRepository.findAll(pageable)
                .map(purchaseGetMapper::toDto);
    }


    @Override
    public List<PurchaseDtoGet> findAllPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream()
                .map(purchaseGetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Purchase> findPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public void savePurchase(Purchase purchase, Long electronicId, Long employeeId, Long storeId, Long purchaseTypeId) {
        purchaseRepository.save(setIdsForPurchase(purchase, electronicId, employeeId, storeId, purchaseTypeId));
    }

    @Override
    public void updatePurchase(Long id, Purchase purchase, Long electronicId, Long employeeId, Long storeId, Long purchaseTypeId) {
        purchase.setId(id);
        purchaseRepository.save(setIdsForPurchase(purchase, electronicId, employeeId, storeId, purchaseTypeId));
    }


    private Purchase setIdsForPurchase(Purchase purchase, Long electronicId, Long employeeId, Long storeId, Long purchaseTypeId){
        Electronic electronic = electronicService.findElectronicById(electronicId)
                .orElseThrow(() -> new RuntimeException("Position with ID " + electronicId + " not found"));
        Employee employee = employeeService.findEmployeeById(employeeId)
                .orElseThrow(() -> new RuntimeException("Position with ID " + employeeId + " not found"));
        Store store = storeService.findStoreById(storeId)
                .orElseThrow(() -> new RuntimeException("Position with ID " + storeId + " not found"));
        PurchaseType purchaseType = purchaseTypeService.findPurchaseTypeById(purchaseTypeId)
                .orElseThrow(() -> new RuntimeException("Position with ID " + purchaseTypeId + " not found"));

        purchase.setElectronics(electronic);
        purchase.setEmployee(employee);
        purchase.setStore(store);
        purchase.setPurchaseType(purchaseType);
        return purchase;
    }
}
