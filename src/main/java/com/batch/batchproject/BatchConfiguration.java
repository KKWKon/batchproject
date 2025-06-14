package com.batch.batchproject;

import com.batch.batchproject.model.Transaction;
import com.batch.batchproject.processor.TransactionFileProcessor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class BatchConfiguration {

//    @Autowired
//    private JobRepository jobRepository;

    @Bean
    public FlatFileItemReader<Transaction> reader() {
        return new FlatFileItemReaderBuilder<Transaction>()
                .name("transactionItemReader")
                .resource(new ClassPathResource("dataSource.txt"))
                .recordSeparatorPolicy(new CustomRecordSeparatorPolicy())
                .delimited()
                .delimiter("|")
                .names("accountNumber",
                        "trxAmount",
                        "description",
                        "trxDate",
                        "trxTime",
                        "customerId")
                .linesToSkip(1)
                .targetType(Transaction.class)
                .build();
    }

    @Bean
    public TransactionFileProcessor processor() {
        return new TransactionFileProcessor();
    }

    @Bean
    public MyBatisBatchItemWriter<Transaction> writer(SqlSessionFactory sqlSessionFactory) {
        return new MyBatisBatchItemWriterBuilder<Transaction>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("com.batch.batchproject.mapper.TransactionMapper.insertTransaction")
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<Transaction> reader, TransactionFileProcessor processor, MyBatisBatchItemWriter<Transaction> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Transaction, Transaction>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .faultTolerant()
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

//    @Bean
//    public JobLauncher createJobLauncher() throws Exception {
//        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
//        jobLauncher.setJobRepository(jobRepository);
//        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        jobLauncher.afterPropertiesSet();
//        return jobLauncher;
//    }
}
