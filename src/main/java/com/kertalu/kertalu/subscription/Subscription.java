package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.clients.Client;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insert_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date insert_date;


    private Date startDate;
//    private Date endDate; // this needs to be added later. also calculated
    private boolean isActive;

    @ManyToOne
    private SubscriptionTier tier;

    @ManyToOne
    private Client client;

    public Subscription(Date startDate,  boolean isActive, SubscriptionTier tier, Client client) {
        this.startDate = startDate;
        this.isActive = isActive;
        this.tier = tier;
        this.client = client;
    }




}
