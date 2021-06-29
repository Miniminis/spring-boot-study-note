package com.example.batch.sample.person;

import com.example.batch.sample.domains.Person;
import com.example.batch.sample.repositories.PersonRepository;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class PersonProcessingConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PersonRepository personRepository;

    public PersonProcessingConfig(JobBuilderFactory jobBuilderFactory,
                                  StepBuilderFactory stepBuilderFactory,
                                  PersonRepository personRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.personRepository = personRepository;
    }

    @Bean
    public Job personProcessingJob() {
        return jobBuilderFactory.get("personJob")
                .incrementer(new RunIdIncrementer())
                .start(this.changeAuthYnStep())
                .build();

    }

    @Bean
    @JobScope
    public Step changeAuthYnStep() {
        return stepBuilderFactory.get("changeAuthYnStep")
                .<Person, Person>chunk(10)
                .reader(changeAuthReader())
                .writer(changeAuthWriter())
                .build();
    }

    private ItemWriter<Person> changeAuthWriter() {
        return items -> {
            for (Person item : items) {
                if (item.getName().equals("손무개")) {
                    item.setName("남무개");
                } else {
                    item.setName("손무개");
                }

                if(item.getAuthYn() == null) {
                    item.setAuthYn("N");
                } else if (item.getAuthYn().equals("Y")) {
                    item.setAuthYn("N");
                } else {
                    item.setAuthYn("Y");
                }

                personRepository.save(item);
            }
        };
    }

    private ItemReader<Person> changeAuthReader() {
        List<Person> personList = personRepository.findAll();

        return new ListItemReader<>(personList);
    }

}
