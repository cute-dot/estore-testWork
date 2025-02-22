package ru.isands.test.estore.dao.repo;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.entity.ElectronicsType;

public interface ElectronicRepository extends JpaRepository<Electronic, Long> {
    @NonNull
    Page<Electronic> findAll(@NonNull Pageable pageable);
}