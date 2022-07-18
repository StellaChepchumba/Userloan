package com.example.com.userloan.datalayer.repositories;

import com.example.com.userloan.datalayer.entities.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Integer> {
    Optional<Loans> findAllByAccountNumberAndLoanType(String accountNumber, String loanType);
    List<Loans> findAllByAccountNumber(String accountNumber);

    @Transactional
    @Modifying
    @Query(value = "update loans set loan_status='Approved',loan_balance=loan_amount where loan_id=:loanId",nativeQuery = true)
    void approveLoan(int loanId);
    Optional<Loans> findAllByLoanId(int loanId);

    @Query(value = "select * from loans where loan_status='Approved' and loan_id=:loanId",nativeQuery = true)
    Loans searchLoanForPayment(int loanId);

    @Transactional
    @Modifying
    @Query(value = "update loans set loan_balance=loan_balance -:paymentAmount where loan_id=:loanId",nativeQuery = true)
    void updateLoanBalance(int loanId, int paymentAmount);

    @Transactional
    @Modifying
    @Query(value = "update loans set payment_status='Paid' where loan_id=:loanId",nativeQuery = true)
    void updateFullPaymentStatus(int loanId);

    @Transactional
    @Modifying
    @Query(value = "update loans set payment_status='PartiallyPaid' where loan_id=:loanId",nativeQuery = true)
    void updatePartialPaymentStatus(int loanId);
}
