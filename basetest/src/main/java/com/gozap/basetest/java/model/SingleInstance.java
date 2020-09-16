package com.gozap.basetest.java.model;

/**
 * Create by liuxue on 2020/9/9 0009.
 * description:
 */
class SingleInstance {
    private static final Object mLock=new Object();
    private static SingleInstance instance;
    public static SingleInstance getInstance(){
        synchronized (mLock){
            if(null==instance)
                instance=new SingleInstance();
        }
        return instance;
    }
}
