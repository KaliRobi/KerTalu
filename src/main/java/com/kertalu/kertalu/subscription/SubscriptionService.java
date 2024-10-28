package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.clients.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubscriptionService {

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    @Autowired
    private SubscriptionTierRepository subscriptionTierRepository;

    public Subscription subscribeUser(Client client, Long tierId) {
        SubscriptionTier tier = subscriptionTierRepository.findById(tierId)
                .orElseThrow(() -> new Exception("Tier not found"));

        Subscription subscription = new Subscription(LocalDateTime.now(), true, user, tier);

        return userSubscriptionRepository.save(subscription);
    }

    // Check if user's tier allows access to a specific feature
    public boolean hasFeatureAccess(Client client, String feature) {

        return userSubscriptionRepository.findByUser(client)
                .getTier()
                .getFeatures()
                .contains(feature);
    }
}
