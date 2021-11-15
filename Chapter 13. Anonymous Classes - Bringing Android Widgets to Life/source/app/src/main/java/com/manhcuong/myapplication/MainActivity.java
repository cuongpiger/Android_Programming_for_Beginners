package com.manhcuong.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Get a reference to all widgets */
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Switch switch1 = findViewById(R.id.switch1);
        WebView webView = findViewById(R.id.webView);

        final EditText editText = findViewById(R.id.editText);
        final Button button = findViewById(R.id.button);
        final TextClock tClock = findViewById(R.id.textClock);
        final CheckBox cbTransparency = findViewById(R.id.checkBoxTransparency);
        final CheckBox cbTint = findViewById(R.id.checkBoxTint);
        final CheckBox cbResize = findViewById(R.id.checkBoxReSize);
        final ImageView imageView = findViewById(R.id.imageView);
        final TextView textView = findViewById(R.id.textView);

        textView.setVisibility(View.INVISIBLE);
        radioGroup.clearCheck();

        cbTransparency.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbTransparency.isChecked()) {
                    imageView.setAlpha(.1f);
                } else {
                    imageView.setAlpha(1f);
                }
            }
        });

        cbTint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbTint.isChecked()) {
                    imageView.setColorFilter(Color.argb(150, 255, 0, 0));
                } else {
                    imageView.setColorFilter(Color.argb(0, 0, 0, 0));
                }
            }
        });

        cbResize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbResize.isChecked()) {
                    imageView.setScaleX(2);
                    imageView.setScaleY(2);
                } else {
                    imageView.setScaleX(1);
                    imageView.setScaleY(1);
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                switch (rb.getId()) {
                    case R.id.radioButtonLondon:
                        tClock.setTimeZone("Europe/London"); break;

                    case R.id.radioButtonBeijing:
                        tClock.setTimeZone("Etc/GMT-8"); break;

                    case R.id.radioButtonNewYork:
                        tClock.setTimeZone("America/New_York"); break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText());
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        });

        webView.loadUrl("https://github.com/cuongpiger");
    }
}