package com.kertalu.kertalu.subscription;

import com.kertalu.kertalu.repositories.SubscriptionTierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionTierService {

    @Autowired
    private final SubscriptionTierRepository subscriptionTierRepository;

    public SubscriptionTierService(SubscriptionTierRepository subscriptionTierRepository) {
        this.subscriptionTierRepository = subscriptionTierRepository;
    }

    public Optional<SubscriptionTier> findSubscriptionTier(String tierName){
        return subscriptionTierRepository.findTierByName(tierName);
    }

    @Transactional
    public boolean deleteSubscriptionTier(SubscriptionTier subscriptionTier){
        try {
            subscriptionTierRepository.delete(subscriptionTier);
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    //TODO with optional
    @Transactional
    public boolean saveSubscriptionTier(SubscriptionTier subscriptionTier) {
        try {
            subscriptionTierRepository.save(subscriptionTier);
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }


    public List<SubscriptionTier> getSubscriptionList() {
        return subscriptionTierRepository.findAll();
    }
}
