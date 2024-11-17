package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.users.clients.ktclients.Client;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insert_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Instant insert_date;


    private Instant startDate;
//    private Date endDate; // this needs to be added later. also calculated
    private boolean isActive;

    @ManyToOne
    private SubscriptionTier tier;

    @ManyToOne
    private Client client;

    public Subscription(Instant startDate,  boolean isActive, SubscriptionTier tier, Client client) {
        this.startDate = startDate;
        this.isActive = isActive;
        this.tier = tier;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Instant getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(Instant insert_date) {
        this.insert_date = insert_date;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
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
