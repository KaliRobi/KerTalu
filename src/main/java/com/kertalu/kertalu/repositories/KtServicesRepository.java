package com.kertalu.kertalu.repositories;

import com.kertalu.kertalu.users.clients.ktclients.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KtServicesRepository extends JpaRepository<Client, Long> {
}
