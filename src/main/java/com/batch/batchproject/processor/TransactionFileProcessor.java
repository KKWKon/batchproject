package com.batch.batchproject.processor;

import com.batch.batchproject.model.Transaction;
import org.springframework.batch.item.ItemProcessor;

public class TransactionFileProcessor implements ItemProcessor<Transaction, Transaction> {

    @Override
    public Transaction process(Transaction transaction) {
        return transaction;
    }
}
