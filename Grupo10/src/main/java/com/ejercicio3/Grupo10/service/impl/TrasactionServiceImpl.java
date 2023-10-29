package com.ejercicio3.Grupo10.service.impl;

import com.ejercicio3.Grupo10.model.Transaction;
import com.ejercicio3.Grupo10.repository.TransactionRepository;
import com.ejercicio3.Grupo10.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrasactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
