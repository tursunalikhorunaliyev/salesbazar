package com.hamkor.salesbazar.repository;

import com.hamkor.salesbazar.entity.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekDayRepository extends JpaRepository<WeekDay, Long> {
}