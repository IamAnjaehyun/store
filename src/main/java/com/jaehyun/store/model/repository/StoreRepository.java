package com.jaehyun.store.model.repository;

import com.jaehyun.store.model.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository <Store, Long> {
    List<Store> findByStoreName(String storeName);
}
