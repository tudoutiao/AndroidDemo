package com.android.test.test;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.test.R;
import com.android.test.databinding.ActivityBBinding;

public class ActivityB extends AppCompatActivity {

    private ActivityBBinding dataBind;
    private TestTwoService service;
    private boolean isBind = false;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isBind = true;
            TestTwoService.MyBinder myBinder = (TestTwoService.MyBinder) binder;
            service = myBinder.getService();
            Log.i("Kathy", "ActivityB - onServiceConnected");
            int num = service.getRandomNumber();
            Log.i("Kathy", "ActivityB - getRandomNumber = " + num);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            Log.i("Kathy", "ActivityB - onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_b);
        dataBind.button5.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestTwoService.class);
            intent.putExtra("from", "ActivityB");
            Log.i("Kathy", "----------------------------------------------------------------------");
            Log.i("Kathy", "ActivityB 执行 bindService");
            bindService(intent, conn, BIND_AUTO_CREATE);
        });
        dataBind.button6.setOnClickListener(v -> {
            if (isBind) {
                Log.i("Kathy", "----------------------------------------------------------------------");
                Log.i("Kathy", "ActivityB 执行 unbindService");
                unbindService(conn);
            }
        });

        dataBind.button7.setOnClickListener(v -> {
            Log.i("Kathy", "----------------------------------------------------------------------");
            Log.i("Kathy", "ActivityB 执行 finish");
            this.finish();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Kathy", "ActivityB - onDestroy");
    }
}