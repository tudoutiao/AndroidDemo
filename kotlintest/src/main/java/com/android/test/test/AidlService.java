package com.android.test.test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class AidlService extends Service {
    public AidlService() {
    }

    private String name;

    private Binder aidlBinder = new IMyAidlInterface.Stub() {
        @Override
        public void setName(String s) {
            name = s;
        }

        @Override
        public String getName() {
            return name;
        }
    };

    @Override
    public void onCreate() {
        Log.i("Kathy", "TestTwoService - onCreate - Thread = " + Thread.currentThread().getName());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Kathy", "TestTwoService - onStartCommand - startId = " + startId + ", Thread = " + Thread.currentThread().getName());
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Kathy", "TestTwoService - onBind - Thread = " + Thread.currentThread().getName());
        return aidlBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Kathy", "TestTwoService - onUnbind - from = " + intent.getStringExtra("from"));
        return false;
    }

    @Override
    public void onDestroy() {
        Log.i("Kathy", "TestTwoService - onDestroy - Thread = " + Thread.currentThread().getName());
        super.onDestroy();
    }


}
