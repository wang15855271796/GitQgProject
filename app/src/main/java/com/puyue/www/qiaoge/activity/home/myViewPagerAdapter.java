package com.puyue.www.qiaoge.activity.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ${王涛} on 2020/5/22
 */
public class myViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> list;
    public myViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list){
        super(fm);
        this.list=list;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
