package com.company.decorator;

public class A4 extends AudiDecorator {

    public A4(ICar basicAudi, String modelName) {
        super(basicAudi, modelName, 2000);
    }
}
