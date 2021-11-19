package com.manhcuong.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

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

    private ArrayList<ParticleSystem> mParticleSystems = new ArrayList<>();
    private int mNextSystem = 0;
    private final int MAX_SYSTEMS = 1000;
    private int mParticlesPerSystem = 100;

    private RectF mResetButton;
    private RectF mTogglePauseButton;

    public LiveDrawingView(Context context, int x, int y) {
        super(context);

        mScreenX = x;
        mScreenY = y;
        mFontSize = mScreenX / 20;
        mFontMargin = mScreenX / 75;
        mOurHolder = getHolder();
        mPaint = new Paint();

        mResetButton = new RectF(0, 0, 100, 100);
        mTogglePauseButton = new RectF(0, 150, 100, 250);

        for (int i = 0; i < MAX_SYSTEMS; ++i) {
            mParticleSystems.add(new ParticleSystem());
            mParticleSystems.get(i).init(mParticlesPerSystem);
        }
    }

    private void draw() {
        /* Lock the canvas (graphic memory) ready to draw */
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 0, 0, 0)); // fill the screen with a solid color
            mPaint.setColor(Color.argb(255, 255, 255, 255)); // choose a color to paint
            mPaint.setTextSize(mFontSize); // set the font size the paint

            for (int i = 0; i < mNextSystem; ++i) {
                mParticleSystems.get(i).draw(mCanvas, mPaint);
            }

            mCanvas.drawRect(mResetButton, mPaint);
            mCanvas.drawRect(mTogglePauseButton, mPaint);

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
        mCanvas.drawText(String.format("Systems: %d", mNextSystem), 10, mFontMargin + debugStart + debugSize * 2, mPaint);
        mCanvas.drawText(String.format("Particles: %d", mNextSystem * mParticlesPerSystem), 10, mFontMargin + debugStart + debugSize * 3, mPaint);
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
        for (int i = 0; i < mParticleSystems.size(); ++i) {
            if (mParticleSystems.get(i).mIsRunning) {
                mParticleSystems.get(i).update(mFPS);
            }
        }
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

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & motionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE) {
            mParticleSystems.get(mNextSystem).emitParticles(new PointF(motionEvent.getX(), motionEvent.getY()));
            mNextSystem += 1;

            if (mNextSystem == MAX_SYSTEMS) {
                mNextSystem = 0;
            }
        }

        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
            if (mResetButton.contains(motionEvent.getX(), motionEvent.getY())) {
                mPaused = !mPaused;
            }
        }

        return true;
    }
}
