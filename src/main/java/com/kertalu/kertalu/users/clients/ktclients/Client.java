package com.kertalu.kertalu.users.clients.ktclients;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insert_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Instant insert_date;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Instant update_date;

    private String name;
    private String email;
    private String phone;


    public Client(  String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;

    }

    public Client() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", insert_date=" + insert_date +
                ", update_date=" + update_date +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
