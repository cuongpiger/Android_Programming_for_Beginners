package com.team9.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView myImageView;
    Bitmap myBlankBitmap;
    Canvas myCanvas;
    Paint myPaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int widthInPixels = 800;
        int heightPixels = 600;

        myBlankBitmap = Bitmap.createBitmap(widthInPixels, heightPixels, Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(myBlankBitmap);
        myImageView = new ImageView(this);
        myPaint = new Paint();


        myCanvas.drawColor(Color.argb(255, 0, 0, 255));

        // Re-size the text
        myPaint.setTextSize(100);
        // Change the paint to white
        myPaint.setColor(Color.argb(255, 255, 255, 255));
        // Draw some text
        myCanvas.drawText("Hello World!",100, 100, myPaint);

        // Change the paint to yellow
        myPaint.setColor(Color.argb(255, 212, 207, 62));
        // Draw a circle
        myCanvas.drawCircle(400,250, 100, myPaint);
        // Associate the drawn upon Bitmap with the ImageView
        myImageView.setImageBitmap(myBlankBitmap);
        // Tell Android to set our drawing
        // as the view for this app
        // via the ImageView
        setContentView(myImageView);

    }
}