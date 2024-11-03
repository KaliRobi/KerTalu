package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.repositories.SubscriptionTierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionTierService {

    @Autowired
    private final SubscriptionTierRepository subscriptionTierRepository;

    public SubscriptionTierService(SubscriptionTierRepository subscriptionTierRepository) {
        this.subscriptionTierRepository = subscriptionTierRepository;
    }




}
