package com.company.singleton;

public class MainSingleton {

    public static void run() {
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient socketClientA = aClazz.getSocketClient();
        SocketClient socketClientB = bClazz.getSocketClient();

        System.out.println("두 객체가 동일한가요?");
        System.out.println(socketClientA.equals(socketClientB));
    }
}
