package com.manhcuong.myapplication;

import android.util.Log;

public class AlienShip {
    private static int numShips;
    private int shieldStrength;
    public String shipName;

    public AlienShip() {
        numShips++;
        this.setShieldStrength(100);
    }

    public static int getNumShips() {
        return numShips;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public int getShieldStrength() {
        return this.shieldStrength;
    }

    public void hitDetected() {
        shieldStrength -= 25;
        Log.i("Incomiming: ", "BAM!!!");
        if (shieldStrength == 0) destroyShips();
    }

    private void destroyShips() {
        --numShips;
        Log.i("Explosion: ", "" + this.shipName + " destroyed!!!");
    }
}
