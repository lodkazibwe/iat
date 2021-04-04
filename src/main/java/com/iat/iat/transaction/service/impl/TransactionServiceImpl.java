package com.iat.iat.transaction.service.impl;

import com.iat.iat.transaction.converter.TransactionConverter;
import com.iat.iat.transaction.dao.TransactionDao;
import com.iat.iat.transaction.dto.TransactionDto;
import com.iat.iat.transaction.model.Transaction;
import com.iat.iat.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired TransactionConverter transactionConverter;
    @Autowired TransactionDao transactionDao;
    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Override
    @Transactional
    public Transaction addNew(TransactionDto transactionDto) {
        logger.info("converting...");
        Transaction transaction=transactionConverter.dtoToEntity(transactionDto);
        return transactionDao.save(transaction);
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
