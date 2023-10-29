package com.ejercicio3.Grupo10.dto.request;
import com.ejercicio3.Grupo10.dto.response.AccountResponse;
import com.ejercicio3.Grupo10.model.Account;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequest {
    private String type;
    private Double amount;
    private Double balance;
    private AccountResponse account;
}
