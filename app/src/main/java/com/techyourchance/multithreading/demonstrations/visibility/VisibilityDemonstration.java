package com.techyourchance.multithreading.demonstrations.visibility;

public class VisibilityDemonstration {

    private static int sCount = 0;

    public static void main(String[] args) {
        new Consumer().start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            return;
        }
        new Producer().start();
    }

    /**
     * sCountに変更があれば出力する
     */
    static class Consumer extends Thread {
        @Override
        public void run() {
            int localValue = -1;
            while (true) {
                if (localValue != sCount) {
                    System.out.println("Consumer: detected count change " + sCount);
                    localValue = sCount;
                }
                if (sCount >= 5) {
                    break;
                }
            }
            System.out.println("Consumer: terminating");
        }
    }

    /**
     * sCountを１秒に一回インクリメントする
     */
    static class Producer extends Thread {
        @Override
        public void run() {
            while (sCount < 5) {
                int localValue = sCount;
                localValue++;
                System.out.println("Producer: incrementing count to " + localValue);
                sCount = localValue;
                //１秒待つことでProducerの後に必ずConsumerが実行される
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println("Producer: terminating");
        }
    }

}
