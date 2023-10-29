package com.ejercicio3.Grupo10.service;

import com.ejercicio3.Grupo10.model.Transaction;

import java.util.List;

public interface TransactionService {
    public abstract List<Transaction> getAllTransactions();

    public abstract Transaction createTransaction(Transaction transaction);
}
