package com.kertalu.kertalu.kertaluservices;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Date;

@Entity
public class KtService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "insert_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private final Instant insert_date;
    private boolean isActive;
    private final String code;
    private final String description;

    public KtService(Long id, Instant insert_date, boolean isActive, String code, String description) {
        this.id = id;
        this.insert_date = insert_date;
        this.isActive = isActive;
        this.code = code;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Instant getInsert_date() {
        return insert_date;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "KtService{" +
                "id=" + id +
                ", insert_date=" + insert_date +
                ", isActive=" + isActive +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


