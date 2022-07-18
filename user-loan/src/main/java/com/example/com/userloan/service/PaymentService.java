package com.example.com.userloan.service;

import com.example.com.userloan.datalayer.entities.Payments;
import com.example.com.userloan.models.Response;

public interface PaymentService {
    public Response payLoan(Payments payments,int loanId);
    public Response searchPayment(int transactionId);
}
