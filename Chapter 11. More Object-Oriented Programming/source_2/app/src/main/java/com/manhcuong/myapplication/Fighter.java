package com.manhcuong.myapplication;

import android.util.Log;

public class Fighter extends AlienShip {
    public Fighter() {
        super(400);
        Log.i ("Location: ", "Fighter constructor");
    }

    public void fireWeapon() {
        Log.i("Firing weapon: ", "lasers firing");
    }
}
