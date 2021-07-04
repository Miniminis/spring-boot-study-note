package com.example.batch.sample.person;

import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronExpression;

import java.time.ZoneId;
import java.time.ZonedDateTime;


class PersonSchedulerTest {

    private final ZoneId timeZone = ZoneId.of("UTC");

    @Test
    void cron_시간대_테스트() {
        //매월 1일 0시 0분 0초에 동작
        String cronExp = "0 0 0 1 * ?";
        int MAX_RUNS = 30;

        CronExpression generator = CronExpression.parse(cronExp);
        ZonedDateTime now = ZonedDateTime.now(timeZone);


        for (int i = 0; i < MAX_RUNS; i++) {
            ZonedDateTime nextRun = generator.next(now);
            System.out.println(nextRun);
            now = nextRun;
        }

    }
}