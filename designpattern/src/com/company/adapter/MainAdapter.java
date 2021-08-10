package com.company.adapter;

public class MainAdapter {

    public static void run() {
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        AirConditioner airConditioner = new AirConditioner();
        SocketAdapter adapter = new SocketAdapter(airConditioner);
        connect(adapter);       //adapter 없을 경우 오류발생!
    }

    public static void connect(Electron110v electron110v) {
        electron110v.powerOn();
    }
}
