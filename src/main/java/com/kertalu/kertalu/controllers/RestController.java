package com.kertalu.kertalu.controllers;


import com.kertalu.kertalu.controllers.datatransferobjects.SubscriptionRequest;
import com.kertalu.kertalu.repositories.ClientRepository;
import com.kertalu.kertalu.subscription.Subscription;
import com.kertalu.kertalu.subscription.SubscriptionService;
import com.kertalu.kertalu.subscription.SubscriptionTier;
import com.kertalu.kertalu.subscription.SubscriptionTierService;
import com.kertalu.kertalu.users.clients.ktclients.Client;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {


    private final SubscriptionService subscriptionService;
//    TODO  replace this with Client service
    private final ClientRepository clientRepository;

    private  final SubscriptionTierService subscriptionTierService;


    public RestController(SubscriptionService subscriptionService, ClientRepository clientRepository, SubscriptionTierService subscriptionTierService) {
        this.subscriptionService = subscriptionService;
        this.clientRepository = clientRepository;
        this.subscriptionTierService = subscriptionTierService;
    }
//    TODO replace the create with create-or-modify-subscription update front end too
    @PostMapping(path = "/v1/subscriptions/create")
    public ResponseEntity<String> registerClientSubscription(@RequestBody SubscriptionRequest request) {
        Subscription subscription = null;
        try {
            subscription = subscriptionService.subscribeClient(request.getClientRegistrationInformation(), request.getSubscriptionTierId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(subscription));
    }

    @PostMapping(path = "/v1/subscriptions/create-or-modify-tier")
    public ResponseEntity<String> manageSubscriptionTier(@RequestBody SubscriptionTier subscriptionTier ){
        try {
            subscriptionTierService.saveSubscriptionTier(subscriptionTier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf("Subscription saved"));
    }

    @PostMapping(path = "/v1/subscriptions/delete-tier")
    public ResponseEntity<String> deleteSubscriptionTier(@RequestBody SubscriptionTier subscriptionTier ){
        try {
            subscriptionTierService.deleteSubscriptionTier(subscriptionTier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf("Subscription deleted"));
    }

    @GetMapping(path = "/v1/subscriptions/list-tier")
    public ResponseEntity<List<SubscriptionTier>> listAvailableSubscriptions(){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTierService.getSubscriptionList());
    }


    @GetMapping(path = "/v1/client")
    public ResponseEntity<List<Client>>  listClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }


    @GetMapping("/v1/csrf-token")
    public ResponseEntity<CsrfToken> getCSRFToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
