package com.example.batch.sample.part1hellojob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HelloConfig {

    private final JobBuilderFactory jobBuilderFactory;          //스프링에 의해 이미 빈으로 설정되어있음
    private final StepBuilderFactory stepBuilderFactory;

    public HelloConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")            //이름설정
                .incrementer(new RunIdIncrementer())        //매번 새로운 job 으로 등록할 수 있도록 seq +
                .start(this.helloStep())
                .build();
    }

    @Bean
    public Step helloStep() {
        return stepBuilderFactory.get("helloStep")          //이름설정
                .tasklet((contribution, chunkContext) -> {      //task 기반(실행단위) / chunk 기반
                    log.info("hello spring batch");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
