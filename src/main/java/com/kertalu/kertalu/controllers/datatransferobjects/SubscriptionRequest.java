package com.kertalu.kertalu.controllers.datatransferobjects;

import com.kertalu.kertalu.users.clients.ktclients.Client;

public class SubscriptionRequest {

    private Client client;
    private Long subscriptionTierId;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
                "client=" + client +
                ", subscriptionTierId=" + subscriptionTierId +
                '}';
    }
}
