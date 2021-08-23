package com.company.encoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Encoder implements IEncoder{

    @Override
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
    }
}
