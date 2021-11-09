package com.manhcuong.myapplication;

import android.util.Log;

public class Bomber extends AlienShip {
    public Bomber() {
        super(100);
        Log.i("Location: ", "Bomber constructor!!!");
    }

    public void fireWeapon() {
        Log.i("Firing weapon: ", "bombs away");
    }
}
