package com.ejercicio3.Grupo10.controller;

import com.ejercicio3.Grupo10.dto.request.AccountRequest;
import com.ejercicio3.Grupo10.exception.ResourceNotFoundException;
import com.ejercicio3.Grupo10.exception.ValidationException;
import com.ejercicio3.Grupo10.model.Account;
import com.ejercicio3.Grupo10.repository.AccountRepository;
import com.ejercicio3.Grupo10.service.AccountService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank/v1")
public class AccountController {
    @Autowired
    private AccountService accountService;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    public AccountController(ModelMapper modelMapper, AccountRepository accountRepository){
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    //Listado de todas las cuentas bancariass
    //URL: http://localhost:8080/api/bank/v1/accounts
    //Method: GET
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAcounts(){
        return new ResponseEntity<List<Account>>(accountRepository.findAll(), HttpStatus.OK);
    }

    //Registro de cuenta bancaria
    //URL: http://localhost:8080/api/bank/v1/accounts
    //Method: POST
    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest request) {
        Account account = modelMapper.map(request, Account.class);
        validateAccount(account);
        existsAccountByNameCustomerAndNumberAccount(account);
        return new ResponseEntity<Account>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    private void validateAccount(Account account){
        if(account.getNameCustomer() == null || account.getNameCustomer().isEmpty()){
            throw new ValidationException("El nombre del cliente debe ser obligatorio");
        }
        if(account.getNameCustomer().length()>30){
            throw new ValidationException("El nombre del cliente no debe exceder los 30 caracteres");
        }
        if(account.getNumberAccount() == null || account.getNumberAccount().isEmpty()){
            throw new ValidationException("El número de cuenta debe ser obligatorio");
        }
        if(account.getNumberAccount().length()>13){
            throw new ValidationException("El número de cuenta debe tener una longitud de 13 caracteres");
        }
    }

    private void existsAccountByNameCustomerAndNumberAccount(Account account){
        if(accountRepository.existsByNameCustomerAndNumberAccount(account.getNameCustomer(), account.getNumberAccount())){
            throw new ValidationException("No se puede registrar la cuenta porque ya existe una con esos datos");
        }
    }

    //get account by id
    //URL: http://localhost:8080/api/bank/v1/accounts/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Long accountId){
        return new ResponseEntity<Account>(accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("No se encuentra la cuenta con id: " + accountId)), HttpStatus.OK);
    }

}
