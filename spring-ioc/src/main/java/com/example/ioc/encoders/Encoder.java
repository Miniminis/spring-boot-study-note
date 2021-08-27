package com.example.ioc.encoders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Encoder {

    private IEncoder iEncoder;

    public Encoder(@Qualifier("base74Encoder") IEncoder encoder) {
        /***
         * IEncoder 이 2개 이상이기 때문에 autowire 에러가 발생한다.
         * -> qualifier 를 설정해주어 어떤 bean 을 주입해줄 것인지 해결한다.
         * */


        this.iEncoder = encoder;
    }

    public void setiEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
