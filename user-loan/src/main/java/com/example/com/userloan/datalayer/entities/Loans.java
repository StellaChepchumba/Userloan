package com.example.com.userloan.datalayer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loans")
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    private String accountNumber;
    private String loanType;
    private int loanAmount;
    private int loanBalance;
    private LocalDateTime loanDate;
    private String loanStatus;
    private String paymentStatus;
}
