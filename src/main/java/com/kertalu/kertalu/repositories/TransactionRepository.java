package com.kertalu.kertalu.repositories;

import com.kertalu.kertalu.finance.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long clientId);
}

