package com.example.com.userloan.datalayer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId ;
    private int loanId ;
    private String paymentMode ;
    private int paymentAmount ;
    private String description;
    private LocalDateTime paymentDate;
}
