package com.ejercicio3.Grupo10.controller;

import com.ejercicio3.Grupo10.dto.request.TransactionRequest;
import com.ejercicio3.Grupo10.exception.ValidationException;
import com.ejercicio3.Grupo10.model.Transaction;
import com.ejercicio3.Grupo10.repository.TransactionRepository;
import com.ejercicio3.Grupo10.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bank/v1")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public TransactionController(ModelMapper modelMapper, TransactionRepository transactionRepository) {
        this.modelMapper = modelMapper;
        this.transactionRepository = transactionRepository;
    }
    //listado de transacciones by id de cuenta
    //URL: http://localhost:8080/api/bank/v1/accounts/{id}/transactions
    @Transactional(readOnly = true)
    @GetMapping("/accounts/{id}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactionsByAccount(@PathVariable(value = "id") Long accountId) {
        return new ResponseEntity<List<Transaction>>(transactionRepository.findByAccountId(accountId), HttpStatus.OK);
    }

    //Registro de transacción
    //URL: http://localhost:8080/api/bank/v1/accounts/{id}/transactions
    @Transactional
    @PostMapping("/accounts/{id}/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest request, @PathVariable String id) {
        Transaction transaction = modelMapper.map(request, Transaction.class);
        transaction.setCreateDate(LocalDate.now());
        validationPostTransaction(transaction);
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }


    //findByCreateDateBetween
    //Request
    //URL: http://localhost:8080/api/bank/v1/transactions/filterByCreateDateRange?startDate=2022-11-28&endDate=2022-11-29
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/transactions/filterByCreateDateRange")
    public ResponseEntity<List<Transaction>> filterByCreateDateRange(@RequestParam(name = "startDate") LocalDate startDate,
                                                                     @RequestParam(name = "endDate") LocalDate endDate) {
        return new ResponseEntity<List<Transaction>>(transactionRepository.findByCreateDateBetween(startDate, endDate), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/bank/v1/transactions/filterByNameCustomer?nameCustomer=Luis_Nicolas_Ramirez_Pocohuanca
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/transactions/filterByNameCustomer")
    public ResponseEntity<List<Transaction>>filterTransactionByNameCustomer(@RequestParam(name= "nameCustomer") String nameCustomer){
        return new ResponseEntity<List<Transaction>>(transactionRepository.findByNameCustomer(nameCustomer), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/bank/v1/transactions/filterByType?nameCustomer=Luis_Nicolas_Ramirez_Pocohuanca


    void validationPostTransaction(Transaction transaction){
        //registro de transacción bancaria sin enviar tipo (type)
        if(transaction.getType() == null || transaction.getType().isEmpty()){
            throw new ValidationException("El tipo de transacción bancaria debe ser obligatorio");
        }

        //registro de transacción bancaria con valor de amount mayor al balance y tipo de transacción es Retiro
        if(transaction.getBalance()<transaction.getAmount()){
            throw new ValidationException("En una transacción bancaria tipo retiro el monto a retirar no puede ser mayor al saldo");
        }

        //registro de transacción bancaria con valor de 0.0 en el amount
        if(transaction.getAmount()<=0){
            throw new ValidationException("El monto en una transacción bancaria debe ser mayor de S/0.0");
        }
    }

}
