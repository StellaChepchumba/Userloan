package com.example.com.userloan.service;

import com.example.com.userloan.datalayer.entities.Loans;
import com.example.com.userloan.models.Response;

public interface LoanService {
    public Response createLoan(Loans loans);
    public Response searchLoan(String accountNumber);
    public Response approveLoan(int loanId);
}
