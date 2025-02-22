package ru.isands.test.estore.dao.repo;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.ElectronicsType;
import ru.isands.test.estore.dao.entity.Employee;

public interface ElectronicsTypeRepository extends JpaRepository<ElectronicsType, Long> {
    @NonNull
    Page<ElectronicsType> findAll(@NonNull Pageable pageable);
}