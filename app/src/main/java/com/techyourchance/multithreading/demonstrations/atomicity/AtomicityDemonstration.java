package com.techyourchance.multithreading.demonstrations.atomicity;

import com.techyourchance.multithreading.demonstrations.visibility.VisibilityDemonstration;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class AtomicityDemonstration {

    private static final AtomicityDemonstration instatnce = new AtomicityDemonstration();
    private static final int COUNT_UP_TO = 1000;
    private static final int NUM_OF_COUNTER_THREADS = 100;
    private volatile int mCount;
    public static void main(String[] args) {
        instatnce.startCount();
    }

    private void startCount() {
        mCount = 0;

        for (int i = 0; i < NUM_OF_COUNTER_THREADS; i++) {
            startCountThread();
        }

        try {
            //全てのスレッドが終わるのをまつ。
            sleep(2000);
        } catch (Exception e) {

        }
        System.out.println("Total count is... " + mCount);
    }

    private void startCountThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT_UP_TO; i++) {
//                    int j = mCount;
//                    mCount = j + 1;
                    mCount++;
                }
            }
        }).start();
    }

}
