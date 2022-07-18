package com.example.com.userloan.models;

import com.example.com.userloan.datalayer.entities.Loans;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String responseCode;
    private String message;
    private Object responseObject;
}
