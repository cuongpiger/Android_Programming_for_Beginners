package com.manhcuong.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

public class ParticleSystem {
    private float mDuration;
    private ArrayList<Particle> mParticles;
    private Random random = new Random();
    boolean mIsRunning = false;

    void init(int numParticles) {
        mParticles = new ArrayList<>();
        for (int i = 0; i < numParticles; ++i) {
            float angle = (random.nextInt(360));
            angle  = angle * 3.14f / 180.f;
            float speed = (random.nextInt(10) + 1);
            PointF direction = new PointF((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);
            mParticles.add(new Particle(direction));
        }
    }

    void update(long fps) {
        mDuration -= (1f/fps);
        for (Particle p : mParticles) {
            p.update(fps);
        }

        if (mDuration < 0) {
            mIsRunning = false;
        }
    }

    void emitParticles(PointF startPosition) {
        mIsRunning = true;
        mDuration = 3f;
        for (Particle p : mParticles) {
            p.setPosition(startPosition);
        }
    }

    void draw(Canvas canvas, Paint paint) {
        for (Particle p : mParticles) {
            paint.setARGB(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            float sizeX = 10;
            float sizeY = 10;

            canvas.drawCircle(p.getPosition().x, p.getPosition().y, sizeX, paint);
        }
    }
}
