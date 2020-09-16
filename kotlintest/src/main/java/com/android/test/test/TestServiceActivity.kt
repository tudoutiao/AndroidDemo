package com.android.test.test

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.test.R
import com.android.test.databinding.ActivityTestServiceBinding
import com.android.test.test.TestTwoService.MyBinder

/**
 * service
 */
class TestServiceActivity : AppCompatActivity() {
    lateinit var dataBind: ActivityTestServiceBinding

    //===================================bindservice========================================
    lateinit var service: TestTwoService
    var isBind = false
    val connection: ServiceConnection =
        object : ServiceConnection {
            override fun onServiceConnected(
                name: ComponentName,
                binder: IBinder
            ) {
                isBind = true
                val myBinder = binder as MyBinder
                service = myBinder.service
                Log.i("Kathy", "ActivityA - onServiceConnected")
                val num = service.getRandomNumber()
                Log.i("Kathy", "ActivityA - getRandomNumber = $num")
            }

            override fun onServiceDisconnected(name: ComponentName) {
                isBind = false
                Log.i("Kathy", "ActivityA - onServiceDisconnected")
            }
        }

    //===============================aidl test============================================
    lateinit var aidlService: IMyAidlInterface
    var isAidlBind=false
    val connAidlService by lazy {
        object : ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {
               isAidlBind=false
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                isAidlBind=true
                aidlService=IMyAidlInterface.Stub.asInterface(service);
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind =
            DataBindingUtil.setContentView(this, R.layout.activity_test_service)
        //===========================startServcie=======================================
        Log.i("Kathy", "Thread ID = " + Thread.currentThread().id)
        dataBind.start1.setOnClickListener { v: View? ->
            val intentOne =
                Intent(this, TestOneService::class.java)
            intentOne.putExtra("from", "intentOne")
            startService(intentOne)
        }
        dataBind.start2.setOnClickListener { v: View? ->
            val intentTwo =
                Intent(this, TestOneService::class.java)
            intentTwo.putExtra("from", "intentTwo")
            startService(intentTwo)
        }
        dataBind.start3.setOnClickListener { v: View? ->
            val intentThree =
                Intent(this, TestOneService::class.java)
            intentThree.putExtra("from", "intentThree")
            startService(intentThree)


        }
        dataBind.stopService.setOnClickListener { v: View? ->
            val intentFour =
                Intent(this, TestOneService::class.java)
            stopService(intentFour)
        }

        //==============================bindService======================================
        dataBind.button1.setOnClickListener { v: View? ->
            //单击了“bindService”按钮
            val intent =
                Intent(this, TestTwoService::class.java)
            intent.putExtra("from", "ActivityA")
            Log.i(
                "Kathy",
                "----------------------------------------------------------------------"
            )
            Log.i("Kathy", "ActivityA 执行 bindService")
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        dataBind.button2.setOnClickListener { v: View? ->
            if (isBind) {
                Log.i(
                    "Kathy",
                    "----------------------------------------------------------------------"
                )
                Log.i("Kathy", "ActivityA 执行 unbindService")
                unbindService(connection)
            }
        }
        dataBind.button3.setOnClickListener { v: View? ->
            val intent = Intent(this, ActivityB::class.java)
            Log.i(
                "Kathy",
                "----------------------------------------------------------------------"
            )
            Log.i("Kathy", "ActivityA 启动 ActivityB")
            startActivity(intent)
        }
        dataBind.button4.setOnClickListener { v: View? ->
            Log.i(
                "Kathy",
                "----------------------------------------------------------------------"
            )
            Log.i("Kathy", "ActivityA 执行 finish")
            finish()
        }
        //===================================================================================
        dataBind.button5.setOnClickListener {
            var intent = Intent(this, AidlService::class.java);
            bindService(intent, connAidlService, BIND_AUTO_CREATE)
        }
        dataBind.button6.setOnClickListener {
            unbindService(connAidlService)
        }
        dataBind.button7.setOnClickListener {
            aidlService.name = "HanMeimei"
        }

        dataBind.button8.setOnClickListener {
            dataBind.name.text = aidlService.name
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Kathy", "ActivityA - onDestroy")
    }
}