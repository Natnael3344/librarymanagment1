package com.example.librarymanagment.repository;

import com.example.librarymanagment.entity.Transactions;
import com.example.librarymanagment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}