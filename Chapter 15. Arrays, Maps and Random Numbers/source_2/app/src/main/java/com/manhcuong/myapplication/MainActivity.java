package com.manhcuong.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random randInt = new Random();
        int questionNumber;
        String[][] countriesAndCities;
        countriesAndCities = new String[5][2];

        countriesAndCities[0][0] = "United Kingdom";
        countriesAndCities[0][1] = "London";

        countriesAndCities[1][0] = "USA";
        countriesAndCities[1][1] = "Washington";

        countriesAndCities[2][0] = "India";
        countriesAndCities[2][1] = "New Delhi";

        countriesAndCities[3][0] = "Brazil";
        countriesAndCities[3][1] = "Brasilia";

        countriesAndCities[4][0] = "Kenya";
        countriesAndCities[4][1] = "Nairobi";

        final int country = 0;
        final int capital = 1;

        for (int i = 0; i < 3; i++) {
            // Now we can get a random question number between 0 and 4
            questionNumber = randInt.nextInt(5);

            // and ask the question and in this case just
            // give the answer for the sake of brevity
            Log.d("info", "The capital of " + countriesAndCities[questionNumber][country]);
            Log.d("info", "is " + countriesAndCities[questionNumber][capital]);

        }
    }
}