package com.example.ioc;

import com.example.ioc.encoders.Base64Encoder;
import com.example.ioc.encoders.Encoder;
import com.example.ioc.encoders.UrlEncoder;
import com.example.ioc.providers.ApplicationContextProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args);
        ApplicationContext context = ApplicationContextProvider.getContext();

        String message = "https://newsstand.naver.com/";

        Encoder encoder = context.getBean(Encoder.class);
        String encodedMessage = encoder.encode(message);
        System.out.println(encodedMessage);     //aHR0cHM6Ly9uZXdzc3RhbmQubmF2ZXIuY29tLw==
    }

}
