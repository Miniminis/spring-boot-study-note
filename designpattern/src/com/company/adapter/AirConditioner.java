package com.company.adapter;

public class AirConditioner implements Electron220v {
    @Override
    public void powerOn() {
        System.out.println("AirConditioner 220v power on");
    }
}
