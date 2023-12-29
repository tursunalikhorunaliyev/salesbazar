package com.hamkor.salesbazar.repository;

import com.hamkor.salesbazar.entity.PaymentInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface PaymentIntervalRepository extends JpaRepository<PaymentInterval, Long> {

}