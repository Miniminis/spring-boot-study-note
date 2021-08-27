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

        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

        Encoder encoder = new Encoder(base64Encoder);
        String encodedMessage = encoder.encode(message);
        System.out.println(encodedMessage);     //aHR0cHM6Ly9uZXdzc3RhbmQubmF2ZXIuY29tLw==

        encoder.setiEncoder(urlEncoder);
        encodedMessage = encoder.encode(message);
        System.out.println(encodedMessage);     //https%3A%2F%2Fnewsstand.naver.com%2F


        /**
         * 개발자가 관리하던 객체를 -> 스프링이 관리하도록 권한을 위임하다.
         * -> 제어의 역전이 발생한 것.
         * */
    }

}
