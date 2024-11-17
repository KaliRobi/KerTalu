package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.users.clients.ktclients.Client;
import com.kertalu.kertalu.kertaluservices.KtService;
import com.kertalu.kertalu.repositories.SubscriptionRepository;
import com.kertalu.kertalu.users.clients.ktclients.ClientService;
import com.kertalu.kertalu.users.userregistration.ClientRegistrationInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;



@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;


    private SubscriptionTierService subscriptionTierService;


    public Subscription subscribeClient(ClientRegistrationInformation clientInfo, String tierName) throws Exception {

                SubscriptionTier tier = subscriptionTierService.findSubscriptionTier(tierName)
                .orElseThrow(() -> new Exception("Tier not found"));

                Client client = new Client(clientInfo.getName(), clientInfo.getEmail(), clientInfo.getEmail());

                ClientService clientService = new ClientService();
                clientService.safeSaveClient(client);

                Subscription subscription = new Subscription(Instant.now(), true, tier, client);

        return subscriptionRepository.save(subscription);
    }

    public Subscription getClientSubscription(Client client){
        return subscriptionRepository.findByClient(client);
    }

    public boolean hasFeatureAccess(Client client, KtService ktService) {

        return subscriptionRepository.findByClient(client)
                .getTier()
                .getKtServiceList()
                .contains(ktService);
    }
}
