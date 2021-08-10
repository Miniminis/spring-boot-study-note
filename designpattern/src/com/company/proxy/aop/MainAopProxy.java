package com.company.proxy.aop;

import com.company.proxy.cache.IBrowser;

import java.util.concurrent.atomic.AtomicLong;

public class MainAopProxy {

    public static void run() {

        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                () -> {
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                () -> {
                    System.out.println("after");
                    long now = System.currentTimeMillis();
                    end.set(now - start.get());
                }
                );

        aopBrowser.show();
        System.out.println("loading time : " + end.get());

        aopBrowser.show();
        System.out.println("loading time : " + end.get());

        aopBrowser.show();
        System.out.println("loading time : " + end.get());
    }
}
