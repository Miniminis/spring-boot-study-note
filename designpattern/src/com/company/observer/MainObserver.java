package com.company.observer;

public class MainObserver {

    public static void run() {
        Button button = new Button("신청하기");
        button.addButtonListener(new IButtonListener() {
            @Override
            public void clickEvent(String event) {
                System.out.println(event);
            }
        });

        button.click("버튼 클릭! 1");
        button.click("버튼 클릭! 2");
        button.click("버튼 클릭! 3");
        button.click("버튼 클릭! 4");
        button.click("버튼 클릭! 5");
    }
}
