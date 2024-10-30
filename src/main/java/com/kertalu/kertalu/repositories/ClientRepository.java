package com.kertalu.kertalu.repositories;

import com.kertalu.kertalu.users.clients.ktclients.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
