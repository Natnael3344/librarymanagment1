package com.example.librarymanagment.service.services;

import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.entity.Transactions;
import com.example.librarymanagment.entity.Users;
import com.example.librarymanagment.exceptions.NotFoundException;
import com.example.librarymanagment.repository.TransactionsRepository;
import com.example.librarymanagment.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public  class TransactionsServices implements TransactionsService {
    @Autowired
    private  TransactionsRepository transactionsRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Transactions> findAllTransactions() {
        return transactionsRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Transactions findTransactionsById(Long id) {
        return transactionsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Transaction not found with ID %d", id)));
    }

    @Override
    public void createTransactions(Transactions transaction) {
        transactionsRepository.save(transaction);
    }

    @Override
    public Transactions updateTransactions(Transactions transaction) {
        boolean exist = transactionsRepository.existsById(transaction.getId());
        if(exist){
            return transactionsRepository.save(transaction);
        }
        return null;
    }



    @Override
    public void deleteTransactions(Long id) {
        transactionsRepository.deleteById(id);
    }
}
