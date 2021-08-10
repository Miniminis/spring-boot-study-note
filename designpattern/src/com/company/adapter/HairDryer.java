package com.company.adapter;

public class HairDryer implements Electron110v {
    @Override
    public void powerOn() {
        System.out.println("Hair Dryer 110v power on");
    }
}
