package com.ejercicio3.Grupo10.repository;

import com.ejercicio3.Grupo10.model.Account;
import com.ejercicio3.Grupo10.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCreateDateBetween(LocalDate startDate, LocalDate endDate);
    List<Transaction> findByAccountId(Long id);

    @Query("SELECT t FROM Transaction t WHERE t.account.nameCustomer = :nameCustomer")
    List<Transaction> findByNameCustomer(String nameCustomer);

}

