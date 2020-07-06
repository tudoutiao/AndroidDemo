package com.android.test.base;

import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragmentList;
    private List<String> mTabNameList;
    private Fragment mCurFragment;

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
        mCurFragment = (Fragment) object;
    }

    public Fragment getCurFragment() {
        return mCurFragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
