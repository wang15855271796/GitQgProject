package com.puyue.www.qiaoge.adapter.mine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class EquipmentReturnViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mListFragments;

    public EquipmentReturnViewPagerAdapter(FragmentManager fm, List<Fragment> mListFragments) {
        super(fm);
        this.mListFragments = mListFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListFragments.size();
    }
}
