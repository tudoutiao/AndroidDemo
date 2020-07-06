package com.caidanmao_group.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.caidanmao.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragmentList;
    private List<String> mTabNameList;
    private BaseFragment mCurFragment;

    public ListFragmentAdapter(FragmentManager fm, ArrayList<BaseFragment> linkFragmentLists, ArrayList<String> mTabNameList) {
        super(fm);
        this.mTabNameList = mTabNameList;
        this.mFragmentList = linkFragmentLists;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabNameList.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurFragment = (BaseFragment) object;
    }

    public BaseFragment getCurFragment() {
        return mCurFragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
