package com.company.decorator;

public class AudiDecorator implements ICar{

    private ICar basicAudi;
    private String modelName;
    private int modelPrice;

    public AudiDecorator(ICar basicAudi, String modelName, int modelPrice) {
        this.basicAudi = basicAudi;
        this.modelName = modelName;
        this.modelPrice = modelPrice;
    }

    @Override
    public int getPrice() {
        return basicAudi.getPrice() + modelPrice;
    }

    @Override
    public void showPrice() {
        System.out.println("the price of " + modelName + " is " + getPrice());
    }

}
