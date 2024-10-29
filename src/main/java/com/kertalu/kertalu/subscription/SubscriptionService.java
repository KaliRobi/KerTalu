package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.clients.Client;
import com.kertalu.kertalu.kertaluservices.KtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionTierRepository subscriptionTierRepository;

    public Subscription subscribeClient(Client client, Long tierId) throws Exception {
        SubscriptionTier tier = subscriptionTierRepository.findById(tierId)
                .orElseThrow(() -> new Exception("Tier not found"));

        Subscription subscription = new Subscription(LocalDateTime.now(), true, tier, client);

        return subscriptionRepository.save(subscription);
    }


    public boolean hasFeatureAccess(Client client, KtService ktService) {

        return subscriptionRepository.findByClient(client)
                .getTier()
                .getKtServiceList()
                .contains(ktService);
    }
}
