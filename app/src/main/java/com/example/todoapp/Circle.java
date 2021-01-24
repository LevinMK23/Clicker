package com.example.todoapp;

import android.graphics.Rect;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public class Circle {

    private float cx;
    private float cy;
    private float radius;
    private float dx;
    private float dy;
    private float speed;
    private Rect window;

    public Circle(float cx, float cy, float radius, Rect window) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.window = window;
        speed = 10;
        dx = (float) (Math.random() * 10);
        dy = (float) sqrt(100 - dx * dx);
    }

    public void processReverse(Circle other) {
        float vx = cx - other.cx;
        float vy = cy - other.cy;
        float diff = (float) (10 / sqrt(vx * vx + vy * vy));
        dx = vx * diff;
        dy = vy * diff;
        other.dx = vx * diff * -1;
        other.dy = vy * diff * -1;
    }

    public void update() {
        cx += dx;
        cy += dy;
        if (cx - radius < 0 || cx + radius > window.right) {
            dx *= -1;
        }
        if (cy - radius < 0 || cy + radius > window.bottom) {
            dy *= -1;
        }
    }

    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }

    public float getRadius() {
        return radius;
    }
}
