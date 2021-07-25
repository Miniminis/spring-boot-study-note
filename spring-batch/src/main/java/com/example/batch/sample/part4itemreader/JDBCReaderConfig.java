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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class JDBCReaderConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;


    public JDBCReaderConfig(JobBuilderFactory jobBuilderFactory,
                            StepBuilderFactory stepBuilderFactory,
                            DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public Job jdbcReaderJob() throws Exception {
        return this.jobBuilderFactory.get("jdbcReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(this.jdbcReaderStep())
                .build();
    }

    @Bean
    @JobScope
    public Step jdbcReaderStep() throws Exception {
        return this.stepBuilderFactory.get("jdbcReaderStep")
                .<Person, Person>chunk(10)
                .reader(jdbcCursorItemReader())
                .writer(itemWriter())
                .build();
    }

    private ItemReader<? extends Person> jdbcCursorItemReader() throws Exception {
        JdbcCursorItemReader<Person> jdbcCursorItemReader = new JdbcCursorItemReaderBuilder<Person>()
                .name("jdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("select * from person")
                .rowMapper((rs, rowNum) -> new Person(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)))
                .build();

        jdbcCursorItemReader.afterPropertiesSet();
        return jdbcCursorItemReader;
    }

    private ItemWriter<? super Person> itemWriter() {
        return items -> {
            String personNames = items.stream()
                    .map(Person::getName)
                    .collect(Collectors.joining(", "));

            log.info(personNames);
        };
    }

}
