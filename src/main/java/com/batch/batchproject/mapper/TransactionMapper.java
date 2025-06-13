package com.batch.batchproject.mapper;

import com.batch.batchproject.model.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Insert("""
        INSERT INTO transaction (account_number, trx_amount, description, trx_date, trx_time, customer_id)
        VALUES (#{accountNumber}, #{trxAmount}, #{description}, #{trxDate}, #{trxTime}, #{customerId})
    """)
    void insertTransaction(Transaction transaction);

    @Select("""
        SELECT id, account_number AS accountNumber, trx_amount AS trxAmount, description, trx_date AS trxDate, trx_time AS trxTime, customer_id AS customerId FROM transaction;
    """)
    List<Transaction> selectAllTransactions();
}
