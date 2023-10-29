package com.ejercicio3.Grupo10.service;

import com.ejercicio3.Grupo10.model.Account;

import java.util.List;

public interface AccountService {
    public abstract List<Account> getAllAccounts();

    public abstract Account createAccount(Account account);
    public abstract Account updateAccount(Account account, long id);
    public abstract void deleteAccount(long id);
    public abstract Account getAccountById(long id);
}
