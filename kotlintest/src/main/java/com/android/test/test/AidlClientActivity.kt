package com.android.test.test

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.test.R
import com.android.test.databinding.ActivityAidlClientBinding

class AidlClientActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityAidlClientBinding
    var isBind: Boolean = false
    lateinit var service: IMyAidlInterface
    val conn by lazy {
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                isBind = true
                service = IMyAidlInterface.Stub.asInterface(binder)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isBind = false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_aidl_client)
        dataBinding.button1.setOnClickListener {
            var intent = Intent(this, TestAidlService::class.java);
            bindService(intent, conn, BIND_AUTO_CREATE)
        }
        dataBinding.button2.setOnClickListener {
            unbindService(conn)
        }
        dataBinding.button3.setOnClickListener {
            service.name = "HanMeimei"
        }

        dataBinding.button4.setOnClickListener {
            dataBinding.name.text = service.name
        }
    }
}