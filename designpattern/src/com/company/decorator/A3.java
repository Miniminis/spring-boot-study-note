package com.company.decorator;

public class A3 extends AudiDecorator {

    public A3(ICar basicAudi, String modelName) {
        super(basicAudi, modelName, 1000);
    }
}
