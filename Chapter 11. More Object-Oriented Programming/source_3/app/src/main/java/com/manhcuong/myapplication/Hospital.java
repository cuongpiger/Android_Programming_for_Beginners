package com.manhcuong.myapplication;

import android.util.Log;

public class Hospital {
    protected void healSoldier(Soldier soldierToHeal) {
        Log.i("Just arrived at healSoldier method = ", "" + soldierToHeal.getHealth());
        int health = soldierToHeal.getHealth();
        health += 10;

        soldierToHeal.setHealth(health);
        Log.i("Just finishing heal soldier method = ", "" + soldierToHeal.getHealth());
    }
}
