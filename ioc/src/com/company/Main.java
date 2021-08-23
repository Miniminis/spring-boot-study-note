package com.company;

import com.company.encoder.Base64Encoder;
import com.company.encoder.Encoder;
import com.company.encoder.UrlEncoder;

public class Main {

    public static void main(String[] args) {

        String message = "https://newsstand.naver.com/";

        Encoder base64Encoder = new Encoder(new Base64Encoder());  //내가 생성자에서 필요한 Encoder 타입을 생성하여 의존성을 주입해주면 코드 내에서 처리함
        String base64EncodeMsg = base64Encoder.encode(message);

        Encoder urlEncoder = new Encoder(new UrlEncoder());  //내가 생성자에서 필요한 Encoder 타입을 생성하여 의존성을 주입해주면 코드 내에서 처리함
        String urlEncodeMsg = urlEncoder.encode(message);

        System.out.println(base64EncodeMsg);
        System.out.println(urlEncodeMsg);

        //aHR0cHM6Ly9uZXdzc3RhbmQubmF2ZXIuY29tLw==
        //https%3A%2F%2Fnewsstand.naver.com%2F

        /**
         * IEncoder -> encode()
         * Encoder
         * Base64Encoder
         * UrlEncoder
         * */

    }
}
