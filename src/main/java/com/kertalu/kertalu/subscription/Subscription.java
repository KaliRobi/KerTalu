package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.clients.Client;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insert_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime insert_date;


    private LocalDateTime startDate;
//    private Date endDate; // this needs to be added later. also calculated
    private boolean isActive;

    @ManyToOne
    private SubscriptionTier tier;

    @ManyToOne
    private Client client;

    public Subscription(LocalDateTime startDate,  boolean isActive, SubscriptionTier tier, Client client) {
        this.startDate = startDate;
        this.isActive = isActive;
        this.tier = tier;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(LocalDateTime insert_date) {
        this.insert_date = insert_date;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public SubscriptionTier getTier() {
        return tier;
    }

    public void setTier(SubscriptionTier tier) {
        this.tier = tier;
    }

    public Client getClient() {
        return client;
    }



    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", insert_date=" + insert_date +
                ", startDate=" + startDate +
                ", isActive=" + isActive +
                ", tier=" + tier +
                ", client=" + client +
                '}';
    }
}
