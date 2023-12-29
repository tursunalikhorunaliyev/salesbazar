package com.hamkor.salesbazar.repository;

import com.hamkor.salesbazar.entity.ProductType;
import com.hamkor.salesbazar.projection.HamkorClientInfo;
import com.hamkor.salesbazar.projection.ProductTypeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    @Query("SELECT pt FROM ProductType pt")
    List<ProductTypeInfo> findAllProductType();
}