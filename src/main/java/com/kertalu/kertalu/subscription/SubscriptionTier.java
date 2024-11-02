package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.kertaluservices.KtService;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class SubscriptionTier {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private final Long id;

        @Column(name = "insert_date", nullable = false, updatable = false)
        @Temporal(TemporalType.TIMESTAMP)
        @CreatedDate
        private final Instant insert_date;
        private final String name;
        private String description;

        private boolean isPublic;
        private final ArrayList<KtService> KtServiceList;


    public SubscriptionTier(Long id, Instant insert_date, String name, String description, boolean isPublic, ArrayList<KtService> ktServiceList) {
        this.id = id;
        this.insert_date = insert_date;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        KtServiceList = ktServiceList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<KtService> getKtServiceList() {
        return KtServiceList;
    }

    public Instant getInsert_date() {
        return insert_date;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "SubscriptionTier{" +
                "id=" + id +
                ", insert_date=" + insert_date +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", isPublic=" + isPublic +
                ", KtServiceList=" + KtServiceList +
                '}';
    }
}
