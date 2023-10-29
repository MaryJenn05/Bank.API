package com.ejercicio3.Grupo10.service.impl;

import com.ejercicio3.Grupo10.model.Account;
import com.ejercicio3.Grupo10.repository.AccountRepository;
import com.ejercicio3.Grupo10.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return null;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account, long id) {
        return null;
    }

    @Override
    public void deleteAccount(long id) {
    }

    @Override
    public Account getAccountById(long id) {
        return null;
    }

}
