package com.jaehyun.store.model.repository;

import com.jaehyun.store.model.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository <Store, Long> {
}
