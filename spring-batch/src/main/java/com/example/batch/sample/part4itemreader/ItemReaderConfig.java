package com.example.batch.sample.part4itemreader;

import com.example.batch.sample.domains.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ItemReaderConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    public ItemReaderConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job itemReaderJob() {
        return this.jobBuilderFactory.get("itemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(this.itemReaderStep())
                .build();
    }

    @Bean
    @JobScope
    public Step itemReaderStep() {
        return this.stepBuilderFactory.get("itemReaderStep")
                .<Person, Person>chunk(10)
                .reader(new CustomItemReader<>(getItems()))
                .writer(itemWriter())
                .build();
    }

    private ItemWriter<? super Person> itemWriter() {
        return items -> {
            String personNames = items.stream()
                    .map(Person::getName)
                    .collect(Collectors.joining(", "));

            log.info(personNames);
        };
    }

    private List<Person> getItems() {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Person person = Person.builder()
                    .name("person" + i)
                    .age(i)
                    .address("영등포구 사가정로 501 - " +  i)
                    .authYn("N")
                    .createDate(LocalDateTime.now())
                    .build();

            persons.add(person);
        }

        return persons;
    }
}
