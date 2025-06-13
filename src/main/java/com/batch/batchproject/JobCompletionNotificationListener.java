package com.batch.batchproject;

import com.batch.batchproject.dao.TransactionDao;
import com.batch.batchproject.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private final TransactionDao transactionDao;

    @Override
    public void afterJob(JobExecution jobExecution) {
            log.info("Job completed");
            List<Transaction> transactions = transactionDao.selectAllTransactions();
            transactions.forEach(p -> log.info("Found {}", p));
    }
}
