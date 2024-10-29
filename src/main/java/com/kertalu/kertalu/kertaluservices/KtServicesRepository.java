package com.kertalu.kertalu.kertaluservices;

import com.kertalu.kertalu.clients.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KtServicesRepository extends JpaRepository<Client, Long> {
}
