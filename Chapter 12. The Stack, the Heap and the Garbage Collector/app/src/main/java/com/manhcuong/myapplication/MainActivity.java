package com.manhcuong.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int value = 0;
    private Button btnAdd, btnTake, btnGrow, btnShrink, btnReset, btnHide;
    private TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnTake = findViewById(R.id.btnTake);
        btnShrink = findViewById(R.id.btnShrink);
        btnReset = findViewById(R.id.btnReset);
        btnGrow = findViewById(R.id.btnGrow);
        btnHide = findViewById(R.id.btnHide);
        txtValue = findViewById(R.id.txtValue);

        btnAdd.setOnClickListener(this);
        btnTake.setOnClickListener(this);
        btnShrink.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnGrow.setOnClickListener(this);
        btnHide.setOnClickListener(this);
        txtValue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        float size;
        switch (view.getId()) {
            case R.id.btnAdd:
                txtValue.setText("" + ++value);
                break;

            case R.id.btnTake:
                txtValue.setText("" + --value);
                break;

            case R.id.btnReset:
                value = 0;
                txtValue.setText("" + value);
                break;

            case R.id.btnGrow:
                size = txtValue.getTextScaleX();
                txtValue.setTextScaleX(size + 1);
                break;

            case R.id.btnShrink:
                size = txtValue.getTextScaleX();
                txtValue.setTextScaleX(size - 1);
                break;

            case R.id.btnHide:
                if (txtValue.getVisibility() == View.VISIBLE) {
                    txtValue.setVisibility(View.INVISIBLE);
                    btnHide.setText("SHOW");
                } else {
                    txtValue.setVisibility(View.VISIBLE);
                    btnHide.setText("HIDE");
                }

                break;
        }
    }
}