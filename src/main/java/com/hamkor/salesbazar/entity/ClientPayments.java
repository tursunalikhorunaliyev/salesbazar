package com.hamkor.salesbazar.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "client_payments")
public class ClientPayments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hamkor_client_id", referencedColumnName = "id", nullable = false)
    private HamkorClient hamkorClient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_purchase_id", referencedColumnName = "id", nullable = false)
    private ClientPurchase clientPurchase;

    private Integer amount;

    @CreationTimestamp
    private Timestamp date;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserData user;


}