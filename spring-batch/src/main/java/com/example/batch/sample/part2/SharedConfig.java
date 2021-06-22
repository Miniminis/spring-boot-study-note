package com.example.batch.sample.part2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SharedConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    public SharedConfig(JobBuilderFactory jobBuilderFactory,
                        StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job sharedJob() {
        return jobBuilderFactory.get("sharedJob")
                .incrementer(new RunIdIncrementer())
                .start(this.sharedStep())
                .next(this.sharedStep2())
                .build();
    }

    @Bean
    public Step sharedStep() {
        return stepBuilderFactory.get("sharedStep")
                .tasklet((contribution, chunkContext) -> {

                    StepExecution stepExecution = contribution.getStepExecution();
                    ExecutionContext stepExecutionContext = stepExecution.getExecutionContext();
                    stepExecutionContext.put("stepKey", "step execution context");

                    JobExecution jobExecution = stepExecution.getJobExecution();
                    ExecutionContext jobExecutionExecutionContext = jobExecution.getExecutionContext();
                    jobExecutionExecutionContext.put("jobKey", "job execution context");

                    JobInstance jobInstance = jobExecution.getJobInstance();
                    JobParameters jobParameters = jobExecution.getJobParameters();

                    log.info("jobName: {}, stepName: {}, parameter:{}",
                            jobInstance.getJobName(),
                            stepExecution.getStepName(),
                            jobParameters.getLong("run.id")
                            );

                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step sharedStep2() {
        return stepBuilderFactory.get("sharedStep02")
                .tasklet((contribution, chunkContext) -> {
                    StepExecution stepExecution = contribution.getStepExecution();
                    ExecutionContext stepExecutionExecutionContext = stepExecution.getExecutionContext();

                    JobExecution jobExecution = stepExecution.getJobExecution();
                    ExecutionContext jobExecutionExecutionContext = jobExecution.getExecutionContext();

                    log.info("jobKey: {}, stepKey: {}",
                            jobExecutionExecutionContext.getString("jobKey", "noJobKey"),
                            stepExecutionExecutionContext.getString("stepKey", "noStepKey")
                            );

                    return RepeatStatus.FINISHED;
                })
                .build();

    }
}
