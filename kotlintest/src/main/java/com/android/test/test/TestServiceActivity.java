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
import com.android.test.databinding.ActivityTestServiceBinding;

/**
 * service
 */
public class TestServiceActivity extends AppCompatActivity {


    private ActivityTestServiceBinding dataBind;
    //===========================================================================
    private TestTwoService service;
    private boolean isBind = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isBind = true;
            TestTwoService.MyBinder myBinder = (TestTwoService.MyBinder) binder;
            service = myBinder.getService();
            Log.i("Kathy", "ActivityA - onServiceConnected");
            int num = service.getRandomNumber();
            Log.i("Kathy", "ActivityA - getRandomNumber = " + num);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            Log.i("Kathy", "ActivityA - onServiceDisconnected");
        }
    };

    //===========================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_test_service);
        //===========================startServcie=======================================
        Log.i("Kathy", "Thread ID = " + Thread.currentThread().getId());

        dataBind.start1.setOnClickListener(v -> {
            Intent intentOne = new Intent(this, TestOneService.class);
            intentOne.putExtra("from","intentOne");
            startService(intentOne);
        });
        dataBind.start2.setOnClickListener(v -> {
            Intent intentTwo = new Intent(this, TestOneService.class);
            intentTwo.putExtra("from","intentTwo");

            startService(intentTwo);
        });

        dataBind.start3.setOnClickListener(v -> {
            Intent intentThree = new Intent(this, TestOneService.class);
            intentThree.putExtra("from","intentThree");
            startService(intentThree);
        });

        dataBind.stopService.setOnClickListener(v -> {
            Intent intentFour = new Intent(this, TestOneService.class);
            stopService(intentFour);
        });

        //==============================bindService======================================
        dataBind.button1.setOnClickListener(v -> {
            //单击了“bindService”按钮
            Intent intent = new Intent(this, TestTwoService.class);
            intent.putExtra("from", "ActivityA");
            Log.i("Kathy", "----------------------------------------------------------------------");
            Log.i("Kathy", "ActivityA 执行 bindService");
            bindService(intent, connection, BIND_AUTO_CREATE);
        });

        dataBind.button2.setOnClickListener(v -> {
            if (isBind) {
                Log.i("Kathy",
                        "----------------------------------------------------------------------");
                Log.i("Kathy", "ActivityA 执行 unbindService");
                unbindService(connection);
            }
        });

        dataBind.button3.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityB.class);
            Log.i("Kathy",
                    "----------------------------------------------------------------------");
            Log.i("Kathy", "ActivityA 启动 ActivityB");
            startActivity(intent);
        });
        dataBind.button4.setOnClickListener(v -> {
            Log.i("Kathy",
                    "----------------------------------------------------------------------");
            Log.i("Kathy", "ActivityA 执行 finish");
            this.finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Kathy", "ActivityA - onDestroy");
    }
}