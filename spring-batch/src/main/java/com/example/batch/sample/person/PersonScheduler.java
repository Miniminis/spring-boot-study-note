package com.example.batch.sample.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@Component
@Slf4j
public class PersonScheduler {

    private final JobLauncher jobLauncher;
    private final PersonProcessingConfig personProcessingConfig;

    public PersonScheduler(JobLauncher jobLauncher,
                           PersonProcessingConfig personProcessingConfig) {
        this.jobLauncher = jobLauncher;
        this.personProcessingConfig = personProcessingConfig;
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void updateAuthStatusJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);

        jobLauncher.run(personProcessingConfig.personProcessingJob(), jobParameters);
//        log.info("10초마다 한번씩 실행");
    }

}
