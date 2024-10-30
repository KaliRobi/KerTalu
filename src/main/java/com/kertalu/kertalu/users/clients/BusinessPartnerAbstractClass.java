package com.kertalu.kertalu.users.clients;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
@MappedSuperclass
public abstract class BusinessPartnerAbstractClass {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insert_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date insert_date;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date update_date;

    private String name;
    private String email;     // could be a communication_chanel table so one client can have more than one
    private String phone;
    private String address;  // address detail table
    private String details;  // client detail table


}
