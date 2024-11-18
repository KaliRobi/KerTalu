package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.kertaluservices.KtService;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "subscription_tier")
public class SubscriptionTier {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long id;

        @Column(name = "insert_date", updatable = false)
        @CreatedDate
        private Instant insert_date;
        @Column(name = "name")
        private String name;
        private String description;

        private boolean isPublic;
        private  ArrayList<KtService> KtServiceList;


    public SubscriptionTier(String name, String description, boolean isPublic, ArrayList<KtService> ktServiceList) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        KtServiceList = ktServiceList;
    }

    public SubscriptionTier() {
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
