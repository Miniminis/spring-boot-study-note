package com.example.async.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RestApiService {

    @Async
    public void requestAsync() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
                System.out.println("Sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void requestNormal() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
                System.out.println("Sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
