package com.manhcuong.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LiveDrawingView extends SurfaceView implements Runnable {
    private final boolean DEBUGGING = true;
    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    private Paint mPaint;

    private long mFPS;  // number frames per second
    private final int MILLIS_IN_SECOND = 1000;

    private int mScreenX; // the resolution of the screen
    private int mScreenY; // ''

    private int mFontSize; // text configuration
    private int mFontMargin;

    private Thread mThread = null;
    private volatile boolean mDrawing;
    private boolean mPaused = true;

    public LiveDrawingView(Context context, int x, int y) {
        super(context);

        mScreenX = x;
        mScreenY = y;
        mFontSize = mScreenX / 20;
        mFontMargin = mScreenX / 75;
        mOurHolder = getHolder();
        mPaint = new Paint();
    }

    private void draw() {
        /* Lock the canvas (graphic memory) ready to draw */
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 0, 0, 0)); // fill the screen with a solid color
            mPaint.setColor(Color.argb(255, 255, 255, 255)); // choose a color to paint
            mPaint.setTextSize(mFontSize); // set the font size the paint

            if (DEBUGGING) {
                printDebuggingText();
            }

            /* Display the drawing on screen */
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    private void printDebuggingText() {
        int debugSize = mFontSize / 2;
        int debugStart = 150;
        mPaint.setTextSize(debugSize);
        mCanvas.drawText(String.format("FPS: %d", mFPS), 10, debugStart + debugSize, mPaint);
    }

    public void pause() {
        mDrawing = false;
        try {
            mThread.join(); // stop the thread
        } catch (InterruptedException e) {
            Log.e("ERROR", "Joining thread");
        }
    }

    public void resume() {
        mDrawing = true;
        mThread = new Thread(this);
        mThread.start();
    }

    private void update() {

    }

    @Override
    public void run() {
        while (mDrawing) {
            long frameStartTime = System.currentTimeMillis();
            if (!mPaused) {
                update();
            }

            draw();
            long timeTThisFrame = System.currentTimeMillis() - frameStartTime;

            if (timeTThisFrame > 0) {
                mFPS = MILLIS_IN_SECOND / timeTThisFrame;
            }
        }
    }
}
