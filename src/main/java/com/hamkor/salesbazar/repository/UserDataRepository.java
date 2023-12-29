package com.hamkor.salesbazar.repository;

import com.hamkor.salesbazar.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsername(String username);

    boolean existsByUsername(String username);

}