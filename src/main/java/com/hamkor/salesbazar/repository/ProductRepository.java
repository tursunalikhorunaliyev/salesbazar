package com.hamkor.salesbazar.repository;

import com.hamkor.salesbazar.entity.Product;
import com.hamkor.salesbazar.projection.ProductInfo;
import com.hamkor.salesbazar.projection.ProductTypeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p")
    List<ProductInfo> findAllProduct();

}