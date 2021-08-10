package com.company.adapter;

public class SocketAdapter implements Electron110v {

    private Electron220v electron220v;

    public SocketAdapter(Electron220v electron220v) {
        this.electron220v = electron220v;
    }

    @Override
    public void powerOn() {
        electron220v.powerOn();;
    }
}
