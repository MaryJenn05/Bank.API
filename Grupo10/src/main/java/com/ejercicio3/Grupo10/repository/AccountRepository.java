package com.ejercicio3.Grupo10.repository;

import com.ejercicio3.Grupo10.model.Account;
import com.ejercicio3.Grupo10.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Boolean existsByNameCustomerAndNumberAccount(String nameCustomer, String numberAccount);


}
