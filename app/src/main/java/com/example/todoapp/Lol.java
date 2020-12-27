package com.example.todoapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lol {
    public static void main(String[] args) {
        Counter[] counters = new Counter[4];
        for (int i = 0; i < counters.length; i++) {
            counters[i] = new Counter();
            counters[i].start();
        }
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String command = in.next();
            if (command.equals("info")) {
                long sum = 0;
                for (Counter counter : counters) {
                    int inc = counter.getInc();
                    sum += inc;
                    System.out.println(counter.getName() + "'s inc: " + inc);
                }
                System.out.println("Counter: " + Counter.getCounter() + ", sum: " + sum);
            }
            if (command.equals("q")) {
                for (Counter counter : counters) {
                    counter.setRunning(false);
                }
                break;
            }
        }
        System.out.println("Finished!");
    }
}
