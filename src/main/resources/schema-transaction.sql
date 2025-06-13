CREATE TABLE transaction  (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    account_number VARCHAR(20),
    trx_amount VARCHAR(20),
    description VARCHAR(50),
    trx_date VARCHAR(20),
    trx_time VARCHAR(20),
    customer_id VARCHAR(20)
);