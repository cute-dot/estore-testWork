package ru.isands.test.estore.dao.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
