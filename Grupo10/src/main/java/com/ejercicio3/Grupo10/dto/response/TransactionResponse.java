package com.ejercicio3.Grupo10.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionResponse {
    private Long id;
    private String type;
    private LocalDate createDate;
    private Double amount;
    private Double balance;
    private AccountResponse account;
}
