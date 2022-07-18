package com.example.com.userloan.controller;

import com.example.com.userloan.datalayer.entities.Payments;
import com.example.com.userloan.models.Response;
import com.example.com.userloan.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/v1")
public class PaymentsController {
    @Autowired
    private PaymentService paymentService;

    @PutMapping("/pay-loan/{loanId}")
    public Response payLoan(@RequestBody Payments payments, @PathVariable Integer loanId) {
        return paymentService.payLoan(payments, loanId);

    }

    @GetMapping("/payments/search")
    private Response searchLoan(@PathParam("transactionId") int transactionId) {
        return paymentService.searchPayment(transactionId);
    }
}
