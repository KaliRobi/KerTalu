package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.repositories.SubscriptionTierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionTierService {

    @Autowired
    private final SubscriptionTierRepository subscriptionTierRepository;

    public SubscriptionTierService(SubscriptionTierRepository subscriptionTierRepository) {
        this.subscriptionTierRepository = subscriptionTierRepository;
    }


    public boolean deleteSubscriptionTier(SubscriptionTier subscriptionTier){
        try {
            subscriptionTierRepository.delete(subscriptionTier);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean saveSubscriptionTier(SubscriptionTier subscriptionTier) {
        try {
            subscriptionTierRepository.save(subscriptionTier);
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public List<SubscriptionTier> getSubscriptionList() {
        return subscriptionTierRepository.findAll();
    }
}
