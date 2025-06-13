package com.batch.batchproject.dao;

import com.batch.batchproject.mapper.TransactionMapper;
import com.batch.batchproject.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionDao {

    private final TransactionMapper transactionMapper;

    public void insertTransaction(Transaction transaction) {
        transactionMapper.insertTransaction(transaction);
    }

    public List<Transaction> selectAllTransactions() {
        return transactionMapper.selectAllTransactions();
    }
}
