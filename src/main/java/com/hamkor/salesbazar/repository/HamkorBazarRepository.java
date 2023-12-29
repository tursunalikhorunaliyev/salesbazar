package com.hamkor.salesbazar.repository;

import com.hamkor.salesbazar.entity.HamkorBazar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HamkorBazarRepository extends JpaRepository<HamkorBazar, Long> {
    @Query("select count(h) from HamkorBazar h")
    long countFirstBy();
}