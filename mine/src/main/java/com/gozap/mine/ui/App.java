package com.gozap.mine.ui;

import android.app.Application;

/**
 * Create by liuxue on 2020/7/27 0027.
 * description:
 */
public class App extends Application {
    public static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

    }

    public static App getInstance(){
        return mInstance;
    }
}
