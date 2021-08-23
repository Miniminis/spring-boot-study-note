package com.company.encoder;

public class Encoder {

    private IEncoder iEncoder;

    public Encoder(IEncoder encoder) {
        this.iEncoder = encoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
