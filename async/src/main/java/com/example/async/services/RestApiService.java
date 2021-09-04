package com.example.async.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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

    public String requestNormal() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
                System.out.println("Sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "hello, world!";
    }

    @Async("async-thread")
    public CompletableFuture requestCompletedFuture() {
        return new AsyncResult(requestNormal()).completable();
    }

}
