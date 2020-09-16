package com.android.test

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.test.databind.DataBindActivity
import com.android.test.fragment.ItemFragment
import com.android.test.fragment.OnActionListener
import com.android.test.fragment.ViewFragment

class MainActivity : AppCompatActivity(), View.OnClickListener, OnActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("------------", "onCreate")
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.dataBind).setOnClickListener(this)
        findViewById<Button>(R.id.modeBtn).setOnClickListener(this)
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        var name = intent.getStringExtra("name");
        Log.e("------------", "name:" + name)
        showFragment("fragment1")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("------------", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("------------", "onRestoreInstanceState")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("------------", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.e("------------", "onStart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("------------", "onNewIntent")
        var name1 = getIntent().getStringExtra("name");
        Log.e("------------", "name:" + name1)
        setIntent(intent)
        var name2 = getIntent().getStringExtra("name");
        Log.e("------------", "name:" + name2)

    }

    var mFragmentTransaction = supportFragmentManager.beginTransaction();

    override fun onClick(v: View?) {
        var id = v!!.id
        when (id) {
            R.id.dataBind -> {
                startActivity(Intent(this, DataBindActivity::class.java))
            }
            R.id.modeBtn -> {
                var intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", "MainActivity")
                startActivity(intent)
            }
            R.id.button1 -> {
                hideFragment();
                showFragment("fragment1")
//                showFragment2("fragment1")
            }
            R.id.button2 -> {
                hideFragment();
                showFragment("fragment2")
//                showFragment2("fragment2")
            }
            R.id.button3 -> {
                hideFragment();
                showFragment("fragment3")
//                showFragment2("fragment3")
            }

        }
    }
    //====================================================================================================

    var preTag: String? = null
    fun showFragment(tag: String) {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (null == fragment) {
            if (tag.equals("fragment1")) {
                fragment = ViewFragment.newInstance("", "")
            } else {
                fragment = ItemFragment.newInstance(20, tag)
            }
            fragment?.let {
                supportFragmentManager.beginTransaction().add(R.id.fragment, fragment, tag).commit()
            }
        } else {
            fragment?.let { supportFragmentManager.beginTransaction().show(it).commit() }
        }
        preTag = tag;
    }

    fun hideFragment() {
        if (TextUtils.isEmpty(preTag))
            return
        var fragment = supportFragmentManager.findFragmentByTag(preTag)
        if (null != fragment) {
            supportFragmentManager.beginTransaction().hide(fragment).commit()
        }
    }


    //====================================================================================================

    fun showFragment2(tag: String) {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (null == fragment) {
            fragment = ItemFragment.newInstance(20, tag)
            fragment?.let {
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, tag)
                    .commit()
            }
        } else {
            fragment?.let {
                supportFragmentManager.beginTransaction().replace(R.id.fragment, it).commit()
            }
        }
    }

    override fun showFragmentSuccess(title: String) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
    }


}

