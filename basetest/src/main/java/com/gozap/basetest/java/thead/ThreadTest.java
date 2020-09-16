package com.gozap.basetest.java.thead;

import java.util.concurrent.FutureTask;

/**
 * Create by liuxue on 2020/9/4 0004.
 * description:
 */
class ThreadTest {
    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo("thread1");
        runnableDemo.start();
        ThreadDemo threadDemo = new ThreadDemo("thread2");
        threadDemo.start();

    }

    static class RunnableDemo implements Runnable  {
        private Thread t;
        private String name;

        RunnableDemo(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Running " + name);
            try {
                for (int i = 4; i > 0; i--) {
                    System.out.println("Thread: " + name + ", " + i + "==" + System.currentTimeMillis());
                    // 让线程睡眠一会
                    Thread.sleep(50);
                    System.out.println("Thread: " + name + ", " + i + "==" + System.currentTimeMillis());

                }
            } catch (InterruptedException e) {
                System.out.println("Thread " + name + " interrupted.");
            }
            System.out.println("Thread " + name + " exiting." + "==" + System.currentTimeMillis());
        }


        public void start() {
            System.out.println("Starting " + name);
            if (t == null) {
                t = new Thread(this, name);
                t.start();
            }
        }
    }

    static class ThreadDemo extends Thread {
        private Thread t;
        private String threadName;

        ThreadDemo(String name) {
            this.threadName = name;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("Running " + threadName);
            try {
                for (int i = 4; i > 0; i--) {
                    System.out.println("Thread: " + threadName + ", " + i + "==" + System.currentTimeMillis());
                    // 让线程睡眠一会
                    Thread.sleep(50);
                    System.out.println("Thread: " + threadName + ", " + i + "==" + System.currentTimeMillis());

                }
            } catch (InterruptedException e) {
                System.out.println("Thread " + threadName + " interrupted.");
            }
            System.out.println("Thread " + threadName + " exiting." + "==" + System.currentTimeMillis());
        }

        @Override
        public synchronized void start() {
            super.start();
            System.out.println("Starting " + threadName);
            if (t == null) {
                t = new Thread(this, threadName);
                t.start();
            }
        }
    }


}
