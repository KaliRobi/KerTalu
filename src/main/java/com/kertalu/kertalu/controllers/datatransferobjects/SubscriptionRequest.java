package com.kertalu.kertalu.controllers.datatransferobjects;

import com.kertalu.kertalu.users.userregistration.ClientRegistrationInformation;

public class SubscriptionRequest {

    private ClientRegistrationInformation clientRegistrationInformation;
    private Long subscriptionTierId;

    public ClientRegistrationInformation getClientRegistrationInformation() {
        return clientRegistrationInformation;
    }

    public void setClientRegistrationInformation(ClientRegistrationInformation clientRegistrationInformation) {
        this.clientRegistrationInformation = clientRegistrationInformation;
    }

    public Long getSubscriptionTierId() {
        return subscriptionTierId;
    }

    public void setSubscriptionTierId(Long subscriptionTierId) {
        this.subscriptionTierId = subscriptionTierId;
    }

    @Override
    public String toString() {
        return "SubscriptionRequest{" +
                "client=" + clientRegistrationInformation +
                ", subscriptionTierId=" + subscriptionTierId +
                '}';
    }
}
