package com.example.todoapp;

import android.graphics.Rect;

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
        dy = (float) Math.sqrt(100 - dx * dx);
    }

    public void reverce() {
        dx *= -1;
        dy *= -1;
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
