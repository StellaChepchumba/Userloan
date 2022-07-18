package com.example.com.userloan.datalayer.repositories;

import com.example.com.userloan.datalayer.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
    Payments findAllByTransactionId(int transactionId);
}
