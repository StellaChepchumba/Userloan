package com.example.com.userloan.service.serviceimpl;

import com.example.com.userloan.datalayer.entities.Loans;
import com.example.com.userloan.datalayer.repositories.LoanRepository;
import com.example.com.userloan.models.Response;
import com.example.com.userloan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Response createLoan(Loans loans) {
        Response apiResponse = new Response();
        Optional<Loans> existingLoan = loanRepository.
                findAllByAccountNumberAndLoanType(loans.getAccountNumber(), loans.getLoanType());
        if (existingLoan.isPresent()) {
            apiResponse.setResponseCode("409");
            apiResponse.setMessage("You have already applied for this loan");
        } else {
            loans.setLoanDate(LocalDateTime.now());
            loans.setLoanStatus("Pending");
            loans.setPaymentStatus("NotPaid");
            loans.setLoanBalance(0);
            loanRepository.save(loans);
            apiResponse.setResponseCode("200");
            apiResponse.setMessage("User Loan created successfully");
            apiResponse.setResponseObject(loans);
        }
        return apiResponse;
    }

    @Override
    public Response searchLoan(String accountNumber) {
        Response apiResponse = new Response();
        List<Loans> existingLoan = loanRepository.findAllByAccountNumber(accountNumber);
        if (existingLoan.size() <= 0) {
            apiResponse.setResponseCode("404");
            apiResponse.setMessage("There is no loan under the account " + accountNumber);
        } else {
            apiResponse.setResponseCode("200");
            apiResponse.setMessage("success");
            apiResponse.setResponseObject(existingLoan);
        }
        return apiResponse;
    }

    @Override
    public Response approveLoan(int loanId) {
        Response apiResponse = new Response();
        Optional<Loans> existingLoan = loanRepository.findAllByLoanId(loanId);
        if (!existingLoan.isPresent()) {
            apiResponse.setResponseCode("400");
            apiResponse.setMessage("Error approving the Loan");
        } else {
            loanRepository.approveLoan(loanId);
            apiResponse.setResponseCode("200");
            apiResponse.setMessage("Loan approved successfully");
        }
        return apiResponse;
    }
}
