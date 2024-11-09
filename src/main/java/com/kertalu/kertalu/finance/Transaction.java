package com.kertalu.kertalu.finance;



import com.kertalu.kertalu.users.clients.ktclients.Client;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Instant insert_date;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Instant update_date;
    private String type;  // "income" or "expense"
    private double amount;
    private Instant date;
    private String category; // Seed, Crop, Equipment, Labor, again, now only sting

    private  String description;

    @ManyToOne
    private Client client;


    public Transaction() {}

    public Transaction(String type, double amount, String category, Client client, String description) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.client = client;
        this.description = description;
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

    public Instant getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Instant update_date) {
        this.update_date = update_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", insert_date=" + insert_date +
                ", update_date=" + update_date +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", client=" + client +
                '}';
    }
}
