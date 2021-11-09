package com.manhcuong.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Soldier mySoldier = new Soldier();
        mySoldier.setHealth(100);
        Log.i("Just set health to 100 = ", "" + mySoldier.getHealth());

        Hospital militaryHospital = new Hospital();
        mySoldier.setHealth(10);
        Log.i("Just got wounded to 10 = ", "" + mySoldier.getHealth());

        militaryHospital.healSoldier(mySoldier);
        Log.i("Back from the militaryHospital = ", "" + mySoldier.getHealth());
    }
}