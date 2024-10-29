package com.kertalu.kertalu.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTierRepository extends JpaRepository<SubscriptionTier, Long> {
}
