package com.example.todoapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private final SurfaceHolder holder;
    private GameThread thread;
    private Paint paint;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        setOnTouchListener(this);
        thread = new GameThread(holder);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // start thread
        //thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // stop thread
//        thread.setRunning(false);
//        while (thread.isAlive()) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //thread.addCircle(new Circle(60, 60, 60, holder.getSurfaceFrame()));
        synchronized (holder) {
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            canvas.drawCircle(event.getX(), event.getY(), 60, paint);
            holder.unlockCanvasAndPost(canvas);
        }
        return true;
    }
}
