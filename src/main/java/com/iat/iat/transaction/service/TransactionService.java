package com.iat.iat.transaction.service;

import com.iat.iat.transaction.dto.TransactionDto;
import com.iat.iat.transaction.model.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    Transaction addNew(TransactionDto transactionDto);
    Transaction getById(int id);
    List<Transaction> userTransactions(int userId);
    List<Transaction> getPaidFor(String contact);
    List<Transaction> dateTransactions(Date date);
    List<Transaction> ispTransactions(int ispId);

    Transaction updateTransaction(TransactionDto transactionDto);
    void deleteTransaction(int id);

}
