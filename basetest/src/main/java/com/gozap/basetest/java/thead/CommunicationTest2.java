package com.gozap.basetest.java.thead;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by liuxue on 2020/9/7 0007.
 * description:
 */
class CommunicationTest2 {
    public static void main(String[] args) throws InterruptedException {
        //锁对象
        String lock = new String("");

        ThreadSubtract subtract1Thread = new ThreadSubtract(lock, "subtract1Thread");
        subtract1Thread.start();

        ThreadSubtract subtract2Thread = new ThreadSubtract(lock, "subtract2Thread");
        subtract2Thread.start();

        Thread.sleep(1000);

        ThreadAdd addThread = new ThreadAdd(lock, "addThread");
        addThread.start();

    }

    static class ValueObject {
        public static List<String> list = new ArrayList<>();
    }

    //元素添加线程
    static class ThreadAdd extends Thread {

        private String lock;

        public ThreadAdd(String lock, String name) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("add  data");
                ValueObject.list.add("anyString");
                lock.notifyAll();               // 唤醒所有 wait 线程
            }
        }
    }

    //元素删除线程
    static class ThreadSubtract extends Thread {

        private String lock;

        public ThreadSubtract(String lock, String name) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    while (ValueObject.list.size() == 0) {//不能使用if
                        System.out.println("wait begin ThreadName=" + Thread.currentThread().getName());
                        lock.wait();
                        System.out.println("wait   end ThreadName=" + Thread.currentThread().getName());
                    }
                    System.out.println("1===list size=" + ValueObject.list.size()+"--"+Thread.currentThread().getName());
                    ValueObject.list.remove(0);
                    System.out.println("2===list size=" + ValueObject.list.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
