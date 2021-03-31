package com.iat.iat.transaction.service.impl;

import com.iat.iat.transaction.dto.TransactionDto;
import com.iat.iat.transaction.model.Transaction;
import com.iat.iat.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public Transaction addNew(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public Transaction getById(int id) {
        return null;
    }

    @Override
    public List<Transaction> userTransactions(int userId) {
        return null;
    }

    @Override
    public List<Transaction> getPaidFor(String contact) {
        return null;
    }

    @Override
    public List<Transaction> dateTransactions(Date date) {
        return null;
    }

    @Override
    public List<Transaction> ispTransactions(int ispId) {
        return null;
    }

    @Override
    public Transaction updateTransaction(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public void deleteTransaction(int id) {

    }
}
