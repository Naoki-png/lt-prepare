package com.techyourchance.multithreading.demonstrations.atomicity;

import com.techyourchance.multithreading.demonstrations.visibility.VisibilityDemonstration;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicityDemonstration {

    private static final AtomicityDemonstration instatnce = new AtomicityDemonstration();
    private static final int COUNT_UP_TO = 1000;
    private static final int NUM_OF_COUNTER_THREADS = 100;
    private volatile AtomicInteger mCount = new AtomicInteger(0);
    public static void main(String[] args) {
        instatnce.startCount();
    }

    private void startCount() {
        mCount.set(0);

        for (int i = 0; i < NUM_OF_COUNTER_THREADS; i++) {
            startCountThread();
        }

        System.out.println("Total count is... " + mCount.get());
    }

    private void startCountThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT_UP_TO; i++) {
                    mCount.getAndIncrement();
                }
            }
        }).start();
    }

}
