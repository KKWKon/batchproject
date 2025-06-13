package com.batch.batchproject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class BatchProjectApplication implements ApplicationListener<ApplicationReadyEvent> {

    public static void main(String[] args) {
        SpringApplication.run(BatchProjectApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        log.info("=======================================================");
        log.info("Application started: {}", applicationContext.getId());
        log.info("=======================================================");
    }
}
