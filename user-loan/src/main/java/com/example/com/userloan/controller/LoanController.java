package com.example.com.userloan.controller;

import com.example.com.userloan.datalayer.entities.Loans;
import com.example.com.userloan.models.Response;
import com.example.com.userloan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/v1")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("/loans")
    private Response createLoan(@RequestBody Loans loans) {
        return loanService.createLoan(loans);
    }

    @GetMapping("/loans/search")
    private Response searchLoan(@PathParam("accountNumber") String accountNumber) {
        return loanService.searchLoan(accountNumber);
    }

    @PostMapping("/loans/approve/{loanId}")
    private Response approveLoan(@PathVariable("loanId") int loanId) {
        return loanService.approveLoan(loanId);
    }
}
