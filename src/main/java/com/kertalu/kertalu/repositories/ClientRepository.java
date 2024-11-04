package com.kertalu.kertalu.repositories;

import com.kertalu.kertalu.users.clients.ktclients.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select id from Client c WHERE c.email = ?1")
    public Optional<Long> findByEmail(String emailaddress);
}



