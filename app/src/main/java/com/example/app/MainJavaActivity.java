package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.video.MainFragment;
import com.example.myapplication.video.VideoPlayerFragment;
import com.example.myapplication.view.TabItem;

import java.util.ArrayList;
import java.util.List;

public class MainJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private int curIndex = 0;
    private TabItem tabItem1, tabItem2, tabItem3, tabItem4;
    private List<TabItem> tabItems = new ArrayList<>();

    private Fragment[] mFragments;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tabItem1 = findViewById(R.id.tab1);
        tabItem2 = findViewById(R.id.tab2);
        tabItem3 = findViewById(R.id.tab3);
        tabItem4 = findViewById(R.id.tab4);
        tabItems.add(tabItem1);
        tabItems.add(tabItem2);
        tabItems.add(tabItem3);
        tabItems.add(tabItem4);
        for (int i = 0; i < tabItems.size(); i++) {
            tabItems.get(i).setOnClickListener(this);
        }
        tabItems.get(0).setChecked(true);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragments = new Fragment[4];
        mFragments[0] = MainFragment.newInstance();
        mFragments[1] = VideoPlayerFragment.newInstance();
        mFragments[2] = MainFragment.newInstance();
        mFragments[3] = MainFragment.newInstance();

        mFragmentTransaction.add(R.id.fragment, mFragments[0]);
        mFragmentTransaction.add(R.id.fragment, mFragments[1]);
        mFragmentTransaction.add(R.id.fragment, mFragments[2]);
        mFragmentTransaction.add(R.id.fragment, mFragments[3]);

        mFragmentTransaction.hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);
        mFragmentTransaction.show(mFragments[curIndex]).commitAllowingStateLoss();
    }

    @Override
    public void onClick(View view) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.tab1: {
                changeFragment(0);
                break;
            }
            case R.id.tab2: {
                changeFragment(1);
                break;
            }
            case R.id.tab3: {
                changeFragment(2);
                break;
            }
            case R.id.tab4: {
                changeFragment(3);
                break;
            }
        }
    }


    private void changeFragment(int index) {
        if (curIndex == index) {
            return;
        }
        curIndex = index;

        for (int i = 0; i < tabItems.size(); i++) {
            tabItems.get(i).setChecked(curIndex == i);
        }

        mFragmentTransaction.hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);
        mFragmentTransaction.show(mFragments[curIndex]).commitAllowingStateLoss();
    }
}
