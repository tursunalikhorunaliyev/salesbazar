package com.hamkor.salesbazar.repository;

import com.hamkor.salesbazar.entity.HamkorClient;
import com.hamkor.salesbazar.projection.HamkorClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HamkorClientRepository extends JpaRepository<HamkorClient, Long> {
    @Query("SELECT hk FROM HamkorClient hk")
    List<HamkorClientInfo> findAllClient();
}