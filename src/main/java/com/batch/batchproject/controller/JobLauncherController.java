package com.batch.batchproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JobLauncherController {

    private final JobLauncher jobLauncher;
    private final Job job;

    @GetMapping("/launchJob")
    public String launchJob() throws Exception {
        log.info("launching job");
        JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
        log.info("job launched, {}", jobExecution.getJobInstance());
        return "ok, " + jobExecution;
    }
}