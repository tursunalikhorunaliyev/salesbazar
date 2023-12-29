package com.hamkor.salesbazar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "client_balance")
public class ClientBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "hamkor_client", referencedColumnName = "id", unique = true)
    private HamkorClient hamkorClient;

    @Column(nullable = false)
    private Integer totalAmount;

    @Column(nullable = false)
    private Integer paidAmount;

    @Column(nullable = false)
    private Integer leftAmount;
}