package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.kertaluservices.KtService;
import com.kertalu.kertalu.repositories.SubscriptionTierRepository;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.ArrayList;

@Entity
@Table(name = "subscription_tier")
public class SubscriptionTier {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long id;

        @Column(name = "insert_date", nullable = false, updatable = false)
        @Temporal(TemporalType.TIMESTAMP)
        @CreatedDate
        private  Instant insert_date;
        private final String name;
        private String description;

        private boolean isPublic;
        private final ArrayList<KtService> KtServiceList;

        private SubscriptionTierRepository subscriptionTierRepository;


    public SubscriptionTier(String name, String description, boolean isPublic, ArrayList<KtService> ktServiceList) {
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
