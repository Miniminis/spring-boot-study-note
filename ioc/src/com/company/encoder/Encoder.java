package com.company.encoder;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.nio.charset.StandardCharsets;

public class Encoder {

    public String encode(String message) {
        return Base64.encode(message.getBytes(StandardCharsets.UTF_8));
    }
}
