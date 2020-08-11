package com.example.app.tab;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.app.R;
import com.google.android.material.tabs.TabLayout;

/**
 * TabLayout自定义选择背景滑块
 * https://www.jianshu.com/p/701d25f36c04
 */
public class TabLayoutActivity extends AppCompatActivity {
    private MViewPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mSectionsPagerAdapter = new MViewPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        SliderLayout sliderLayout = (SliderLayout) findViewById(R.id.sl);
        final TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.clearOnPageChangeListeners();
        mViewPager.addOnPageChangeListener(new SliderLayout.SliderOnPageChangeListener(mTabLayout, sliderLayout));
    }

    class MViewPagerAdapter extends FragmentPagerAdapter {
        public final String[] names = new String[]{"音乐", "电影"};

        public MViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return BlankFragment.newInstance("param1", "param2");
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return names[position];
        }
    }
}