package com.kertalu.kertalu.subscription;


import com.kertalu.kertalu.clients.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


    Subscription findByClient(Client client);
}
