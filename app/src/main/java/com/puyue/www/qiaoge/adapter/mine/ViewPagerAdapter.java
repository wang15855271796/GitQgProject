package com.puyue.www.qiaoge.adapter.mine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ${daff} on 2018/9/20
 */
public class ViewPagerAdapter  extends FragmentPagerAdapter {

    private List<Fragment> list;
    private List<String> stringList;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> stringList) {

        super(fm);
        this.list=list;
        this.stringList=stringList;

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }

}
