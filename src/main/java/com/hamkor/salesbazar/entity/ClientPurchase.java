package com.hamkor.salesbazar.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client_purchase", indexes = {
        @Index(name = "idx_clientpurchase", columnList = "hamkor_client_id")
})
public class ClientPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hamkor_client_id", referencedColumnName = "id", nullable = false)
    private HamkorClient hamkorClient;

    @ManyToMany
    @JoinTable(name = "purchased_products", joinColumns = @JoinColumn(name = "client_purchase_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_for_sale_id", referencedColumnName = "id"))
    private Set<ProductForSale> productsForSale;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private Integer normalPayment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_interval_id", referencedColumnName = "id")
    private PaymentInterval paymentInterval;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "week_day_id", referencedColumnName = "id")
    private WeekDay weekDay;

    @Column(nullable = false)
    private Integer totalAmount;

    @Column(nullable = false)
    private Integer paidAmount;

    @Column(nullable = false)
    private Integer leftAmount;

    @CreationTimestamp
    private Timestamp date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserData user;
}