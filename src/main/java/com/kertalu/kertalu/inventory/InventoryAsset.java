package com.kertalu.kertalu.inventory;

import com.kertalu.kertalu.users.clients.ktclients.Client;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Table(name = "inventory_asset")
public class InventoryAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Instant insert_date;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Instant update_date;
    private String name;
    private double value;
    private String type;  // will be another entity

    @ManyToOne
    private Client client;


    public InventoryAsset() {
    }

    public InventoryAsset(String name, double value, String type, Client client) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Instant getInsert_date() {
        return insert_date;
    }


    public Instant getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Instant update_date) {
        this.update_date = update_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "InventoryAsset{" +
                "id=" + id +
                ", insert_date=" + insert_date +
                ", update_date=" + update_date +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                ", client=" + client +
                '}';
    }
}

