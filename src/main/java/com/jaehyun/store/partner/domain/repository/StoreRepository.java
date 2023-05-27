package com.jaehyun.store.partner.domain.repository;

import com.jaehyun.store.partner.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository <Store, Long> {
    List<Store> findByStoreName(String storeName);
    Optional<Store> findByStoreId(Long storeId);
    Long findStoreIdByStoreName(String storeName);


}
