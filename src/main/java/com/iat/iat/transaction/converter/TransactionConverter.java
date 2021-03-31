package com.iat.iat.transaction.converter;

import com.iat.iat.isp.converter.ISPConverter;
import com.iat.iat.transaction.dto.TransactionDto;
import com.iat.iat.transaction.model.Transaction;
import com.iat.iat.transaction.model.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {
    @Autowired ISPConverter ispConverter;
    public TransactionDto entityToDto(Transaction transaction){
        TransactionDto transactionDto =new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setIspDto(ispConverter.entityToDto(transaction.getIsp()));
        transactionDto.setPaidBy(transaction.getPaidBy());
        transactionDto.setPaidFor(transaction.getPaidFor());
        transactionDto.setUserId(transaction.getUserId());
        transactionDto.setStatus(transaction.getStatus());
        transactionDto.setTransactionDate(transaction.getTransactionDate());
        transactionDto.setCreationDateTime(transaction.getCreationDateTime());
        return  transactionDto;
    }

    public Transaction dtoToEntity(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setIsp(ispConverter.dtoToEntity(transactionDto.getIspDto()));
        transaction.setPaidBy(transactionDto.getPaidBy());
        transaction.setUserId(transactionDto.getUserId());
        transaction.setPaidFor(transactionDto.getPaidFor());
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setCreationDateTime(new Date());
        transaction.setTransactionDate(new Date());
        return transaction;
    }

    public List<TransactionDto> entityToDto(List<Transaction> transactions){
        return  transactions.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Transaction> dtoToEntity(List<TransactionDto> transactionDtos){
        return transactionDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());

    }
}
