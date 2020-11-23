package com.gozap.new_modular_javapoet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gozap.annotation.ARouter;

@ARouter(path="MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
