package com.manhcuong.myapplication;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;

public class LiveDrawingActivity extends Activity {
    private LiveDrawingView mLiveDrawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // remove the title from the user interface. This screen will be completely empty when we run this app

        Display display = getWindowManager().getDefaultDisplay(); // get number of pixels on width and height of the device screen
        Point size = new Point();
        display.getSize(size); // put number of pixels on width and height of device into the @size

        mLiveDrawingView = new LiveDrawingView(this, size.x, size.y);
        setContentView(mLiveDrawingView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLiveDrawingView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLiveDrawingView.pause();
    }
}