package com.gozap.basetest.java.thead;

/**
 * Create by liuxue on 2020/9/7 0007.
 * description:
 */
class CommunicationTest {
    public static Object object = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName()
                        + "获取到了锁...");
                try {
                    System.out.println("线程" + Thread.currentThread().getName()
                            + "阻塞并释放锁...");
                    object.wait();
                } catch (InterruptedException e) {
                }
                System.out.println("线程" + Thread.currentThread().getName()
                        + "执行完成...");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName()
                        + "获取到了锁...");
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName()
                        + "唤醒了正在wait的线程...");
            }
            System.out
                    .println("线程" + Thread.currentThread().getName() + "执行完成...");
        }
    }

}
