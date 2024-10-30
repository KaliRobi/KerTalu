package com.kertalu.kertalu.repositories;


import com.kertalu.kertalu.users.clients.ktclients.Client;
import com.kertalu.kertalu.subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


    Subscription findByClient(Client client);
}
