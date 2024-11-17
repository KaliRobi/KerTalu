package com.kertalu.kertalu.repositories;

import com.kertalu.kertalu.subscription.SubscriptionTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SubscriptionTierRepository extends JpaRepository<SubscriptionTier, Long> {
    @Query("select id from subscription_tier t WHERE t.name = ?1")
    Optional<SubscriptionTier> findByName(String tierName);
}
