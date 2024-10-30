package com.kertalu.kertalu.repositories;

import com.kertalu.kertalu.subscription.SubscriptionTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTierRepository extends JpaRepository<SubscriptionTier, Long> {
}
