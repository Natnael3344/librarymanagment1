package com.example.librarymanagment.service;
import com.example.librarymanagment.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



public interface TransactionsService {
    @Autowired
    public List<Transactions> findAllTransactions();

    Transactions findTransactionsById(Long id);

    public void createTransactions(Transactions transaction);

    Transactions updateTransactions(Transactions transaction);

    void deleteTransactions(Long id);
}
