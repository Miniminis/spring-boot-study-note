package com.company;

import com.company.encoder.Encoder;

public class Main {

    public static void main(String[] args) {

        String message = "https://newsstand.naver.com/";

        Encoder encoder = new Encoder();
        String encodeResult = encoder.encode(message);

        System.out.println(encodeResult);       //aHR0cHM6Ly9uZXdzc3RhbmQubmF2ZXIuY29tLw

        /**
         * IEncoder -> encode()
         * Encoder
         * Base64Encoder
         * UrlEncoder
         * */
    }
}
