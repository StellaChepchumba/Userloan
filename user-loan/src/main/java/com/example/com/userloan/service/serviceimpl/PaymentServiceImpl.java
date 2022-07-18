package com.example.com.userloan.service.serviceimpl;

import com.example.com.userloan.datalayer.entities.Loans;
import com.example.com.userloan.datalayer.entities.Payments;
import com.example.com.userloan.datalayer.repositories.LoanRepository;
import com.example.com.userloan.datalayer.repositories.PaymentsRepository;
import com.example.com.userloan.models.Response;
import com.example.com.userloan.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    public Response payLoan(Payments payments, int loanId) {
        Response apiResponse = new Response();
        Loans existingLoan = loanRepository.searchLoanForPayment(loanId);
        if (existingLoan == null) {
            apiResponse.setResponseCode("404");
            apiResponse.setMessage("Loan does not exist");
        } else {
            Integer balance = existingLoan.getLoanBalance();
            if (balance == 0) {
                apiResponse.setResponseCode("400");
                apiResponse.setMessage("Loan is already cleared");
            } else if (payments.getPaymentAmount() >= balance) {
                payments.setPaymentDate(LocalDateTime.now());
                payments.setLoanId(loanId);
                paymentsRepository.save(payments);
                loanRepository.updateLoanBalance(loanId, payments.getPaymentAmount());
                loanRepository.updateFullPaymentStatus(loanId);
                apiResponse.setResponseCode("200");
                apiResponse.setMessage("Payment made successfully");
                apiResponse.setResponseObject(payments);

            } else {
                payments.setPaymentDate(LocalDateTime.now());
                payments.setLoanId(loanId);
                paymentsRepository.save(payments);
                loanRepository.updateLoanBalance(loanId, payments.getPaymentAmount());
                loanRepository.updatePartialPaymentStatus(loanId);
                apiResponse.setResponseCode("200");
                apiResponse.setMessage("Payment made successfully");
                apiResponse.setResponseObject(payments);
            }


        }
        return apiResponse;
    }

    @Override
    public Response searchPayment(int transactionId) {
        Response apiResponse = new Response();
        Payments existingPayment = paymentsRepository.findAllByTransactionId(transactionId);
        if (existingPayment == null) {
            apiResponse.setResponseCode("404");
            apiResponse.setMessage("Payment details not found");
        } else {
            apiResponse.setResponseCode("200");
            apiResponse.setMessage("success");
            apiResponse.setResponseObject(existingPayment);
        }
        return apiResponse;
    }
}
