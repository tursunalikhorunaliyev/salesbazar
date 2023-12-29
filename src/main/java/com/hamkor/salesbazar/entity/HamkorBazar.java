package com.hamkor.salesbazar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "hamkor_bazar")
public class HamkorBazar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer currentAmount;

    @Column(nullable = false)
    private Integer incomeAmount;

    @Column(nullable = false)
    private Integer leftAmount;


}