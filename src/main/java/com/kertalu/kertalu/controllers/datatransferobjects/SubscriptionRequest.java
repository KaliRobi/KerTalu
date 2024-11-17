package com.kertalu.kertalu.controllers.datatransferobjects;

import com.kertalu.kertalu.users.userregistration.ClientRegistrationInformation;


public class SubscriptionRequest {

    private ClientRegistrationInformation clientRegistrationInformation;

    private String subscriptionTierName;

    public ClientRegistrationInformation getClientRegistrationInformation() {
        return clientRegistrationInformation;
    }

    public void setClientRegistrationInformation(ClientRegistrationInformation clientRegistrationInformation) {
        this.clientRegistrationInformation = clientRegistrationInformation;
    }

    public String getSubscriptionTierId() {
        return subscriptionTierName;
    }

    public void setSubscriptionTierId(String subscriptionTierId) {
        this.subscriptionTierName= subscriptionTierId;
    }

    @Override
    public String toString() {
        return "SubscriptionRequest{" +
                "client=" + clientRegistrationInformation +
                ", subscriptionTierId=" + subscriptionTierName+
                '}';
    }
}


