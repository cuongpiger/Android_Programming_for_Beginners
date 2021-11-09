package com.manhcuong.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fighter aFighter = new Fighter();
        Bomber aBomber = new Bomber();

        aBomber.shipName = "Newell Bomber";
        aFighter.shipName = "Meier Fighter";

        Log.i("aFighter Shield:", ""+ aFighter.getShieldStrength());
        Log.i("aBomber Shield:", ""+ aBomber.getShieldStrength());

        aBomber.fireWeapon();
        aFighter.fireWeapon();

        aBomber.hitDetected();
        aBomber.hitDetected();
        aBomber.hitDetected();
        aBomber.hitDetected();
    }
}