package com.example.batch.sample.part4itemreader;

import com.example.batch.sample.domains.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.stream.Collectors;

@Slf4j
@Configuration
public class FlatFileReaderConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    public FlatFileReaderConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job flatFileReaderJob() throws Exception {
        return this.jobBuilderFactory.get("flatFileReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(this.flatFileReaderStep())
                .build();
    }

    @Bean
    @JobScope
    public Step flatFileReaderStep() throws Exception {
        return this.stepBuilderFactory.get("flatFileReaderStep")
                .<Person, Person>chunk(10)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    private ItemWriter<? super Person> itemWriter() {
        return items -> {
            String itemStrs = items.stream()
                    .map(Person::getName)
                    .collect(Collectors.joining(", "));
            log.info(itemStrs);
        };
    }

    private FlatFileItemReader<? extends Person> itemReader() throws Exception {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();       //행
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();        //열

        tokenizer.setNames("id", "name", "age", "address");

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet -> {
            String name = fieldSet.readString("name");
            Integer age = fieldSet.readInt("age");
            String address = fieldSet.readString("address");

            return Person.builder()
                    .name(name)
                    .age(age)
                    .address(address)
                    .build();
        });

        FlatFileItemReader<Person> itemReader = new FlatFileItemReaderBuilder<Person>()
                .name("csvFileItemReader")
                .encoding("UTF-8")
                .resource(new ClassPathResource("personInfo.csv"))
                .linesToSkip(1)
                .lineMapper(lineMapper)
                .build();
        itemReader.afterPropertiesSet();

        return itemReader;
    }
}
