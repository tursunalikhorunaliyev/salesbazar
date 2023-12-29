package com.hamkor.salesbazar.repository;

import com.hamkor.salesbazar.entity.ClientBalance;
import com.hamkor.salesbazar.entity.HamkorClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientBalanceRepository extends JpaRepository<ClientBalance, Long> {
    ClientBalance findByHamkorClient(HamkorClient hamkorClient);
}