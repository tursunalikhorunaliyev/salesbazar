package com.hamkor.salesbazar.repository;

import com.hamkor.salesbazar.entity.ProductForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductForSaleRepository extends JpaRepository<ProductForSale, Long> {
    @Query("SELECT t FROM ProductForSale t WHERE t.id IN (:ids)")
    Set<ProductForSale> getByInIds(@Param("ids") List<Long> ids);
}