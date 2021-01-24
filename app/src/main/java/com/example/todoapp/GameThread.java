package com.example.todoapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class GameThread extends Thread {

    private final SurfaceHolder holder;
    private Paint paint;
    private boolean running;
    private ConcurrentLinkedDeque<Circle> circles;

    public GameThread(SurfaceHolder holder) {
        this.holder = holder;
        circles = new ConcurrentLinkedDeque<>();
        paint = new Paint();
        running = true;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    public void addCircle(Circle circle) {
        circles.add(circle);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            try {
                synchronized (holder) {
                    Canvas canvas = holder.lockCanvas();
                    drawAndUpdate(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception e) {
                Log.d("mike", "interrupt draw thread");
            }
        }
    }

    private boolean needReverse(Circle c1, Circle c2) {
        return Math.sqrt(
                (c1.getCx() - c2.getCx()) * (c1.getCx() - c2.getCx()) +
                        (c1.getCy() - c2.getCy()) * (c1.getCy() - c2.getCy())
        ) <= c2.getRadius() + c1.getRadius();
    }

    private void drawAndUpdate(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        for (Circle circle : circles) {
            for (Circle other : circles) {
                if (!circle.equals(other) && needReverse(circle, other)) {
                    circle.processReverse(other);
                }
            }
            canvas.drawCircle(circle.getCx(), circle.getCy(), circle.getRadius(), paint);
            circle.update();
        }
    }
}
