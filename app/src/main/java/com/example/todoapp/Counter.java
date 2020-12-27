package com.example.todoapp;

import java.util.concurrent.TimeUnit;

public class Counter extends Thread {

    private static int counter;

    private int inc;
    private boolean running;

    @Override
    public void run() {
        running = true;
        System.out.println(getName() + " started!");
        while (running) {
            try {
                inc++;
                synchronized (Counter.class) {
                    counter++;
                }
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static int getCounter() {
        return counter;
    }

    public int getInc() {
        return inc;
    }

    public boolean isRunning() {
        return running;
    }
}
