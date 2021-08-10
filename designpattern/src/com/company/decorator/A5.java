package com.company.decorator;

public class A5 extends AudiDecorator {

    public A5(ICar basicAudi, String modelName) {
        super(basicAudi, modelName, 3000);
    }
}
