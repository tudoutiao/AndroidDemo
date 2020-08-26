package com.android.test.test;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Create by liuxue on 2020/8/24 0024.
 * description:
 */
public class TestOneService extends Service {

    public TestOneService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Kathy", "onCreate - Thread ID = " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Kathy", "onStartCommand - Thread ID = " + Thread.currentThread().getId() + "--" + intent.getStringExtra("from"));
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Kathy", "onBind - Thread ID = " + Thread.currentThread().getId());

        return null;
    }

    @Override
    public void onDestroy() {
        Log.i("Kathy", "onDestroy - Thread ID = " + Thread.currentThread().getId());
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Kathy", "onUnbind - Thread ID = " + Thread.currentThread().getId());

        return super.onUnbind(intent);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.i("Kathy", "unbindService - Thread ID = " + Thread.currentThread().getId());
    }

}
