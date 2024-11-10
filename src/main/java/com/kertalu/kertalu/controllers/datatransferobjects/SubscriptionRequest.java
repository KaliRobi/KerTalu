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

//    Could not resolve parameter [0] in public org.springframework.http.ResponseEntity<com.kertalu.kertalu.subscription.Subscription> com.kertalu.kertalu.controllers.RestController.registerClientSubscription(com.kertalu.kertalu.controllers.datatransferobjects.SubscriptionRequest): JSON parse error: Cannot deserialize value of type `java.lang.Long` from String "Advanced": not a valid `java.lang.Long` value
//        2024-11-10T18:19:56.211+02:00  WARN 33708 --- [kertalu] [io-8080-exec-10] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `java.lang.Long` from String "Advanced": not a valid `java.lang.Long` value]