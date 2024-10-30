package com.kertalu.kertalu.controllers;


import com.kertalu.kertalu.controllers.datatransferobjects.SubscriptionRequest;
import com.kertalu.kertalu.subscription.Subscription;
import com.kertalu.kertalu.subscription.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class Controller {


    private final SubscriptionService subscriptionService;

    public Controller(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    // Subscription registration
            // Post user subscription
    @PostMapping(path = "/v1/subscriptions")
    public ResponseEntity<Subscription> registerClientSubscription(@RequestBody SubscriptionRequest request) {
        Subscription subscription = null;
        try {
            subscription = subscriptionService.subscribeClient(request.getClient(), request.getSubscriptionTierId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
    }



}
