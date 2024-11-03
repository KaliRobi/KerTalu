package com.kertalu.kertalu.controllers;


import com.kertalu.kertalu.controllers.datatransferobjects.SubscriptionRequest;
import com.kertalu.kertalu.repositories.ClientRepository;
import com.kertalu.kertalu.subscription.Subscription;
import com.kertalu.kertalu.subscription.SubscriptionService;
import com.kertalu.kertalu.users.clients.ktclients.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {


    private final SubscriptionService subscriptionService;
    private final ClientRepository clientRepository;


    public RestController(SubscriptionService subscriptionService, ClientRepository clientRepository) {
        this.subscriptionService = subscriptionService;
        this.clientRepository = clientRepository;

    }



    @PostMapping(path = "/v1/subscriptions/create")
    public ResponseEntity<Subscription> registerClientSubscription(@RequestBody SubscriptionRequest request) {
        Subscription subscription = null;
        try {
            subscription = subscriptionService.subscribeClient(request.getClientRegistrationInformation(), request.getSubscriptionTierId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
    }

//    @PostMapping(path = "/v1/subscriptions/modify")
//    public ResponseEntity<Subscription> registerClientSussbscription(@RequestBody SubscriptionRequest request) {
//        try {
//            if(subscriptionService.getClientSubscription(request.getClientRegistrationInformation()).equals(null)
//            ){
//                System.out.println("hello");
////                subscription.setTier(tier);
//            }
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(null);
//    }


    @GetMapping(path = "/v1/client")
    public ResponseEntity<List<Client>>  listClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }

}
