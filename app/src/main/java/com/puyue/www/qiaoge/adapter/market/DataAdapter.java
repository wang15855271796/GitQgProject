package com.puyue.www.qiaoge.adapter.market;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by win7 on 2018/7/24.
 */

public class DataAdapter extends PagerAdapter {
    private List<View> mViews;

    public DataAdapter(List<View> views) {
        this.mViews = views;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(View view, int position, Object object) {
        ((ViewPager) view).removeView(mViews.get(position));
    }

    @Override
    public Object instantiateItem(View view, int position) {

        ((ViewPager) view).addView(mViews.get(position), 0);
        return mViews.get(position);
    }
}