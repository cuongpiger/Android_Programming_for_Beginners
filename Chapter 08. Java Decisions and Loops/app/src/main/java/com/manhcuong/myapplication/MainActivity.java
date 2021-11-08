package com.manhcuong.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void countUp(View v) {
        Log.i("message:", "In countUp method");

        int x = 0;
        while (true) {
            x++;
            Log.i("x = ", "" + x);

            if (x == 3) break;
        }
    }

    public void countDown(View v) {
        Log.i("message:", "In countDown method");

        int x = 4;
        while (true) {
            x--;
            Log.i("x = ", "" + x);

            if (x == 1) break;
        }
    }

    public void nested(View v) {
        Log.i("message:", "In nested method");
        for (int i = 0; i < 3; ++i) {
            for (int j = 3; j > 0; --j) {
                Log.i("i = " + i, "j = " + j);
            }
        }
    }
}