package com.puyue.www.qiaoge.adapter.mine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.puyue.www.qiaoge.base.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class MyOrdersViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> mTitleList;
    private List<BaseFragment> mFragmentList;
    private FragmentManager mFragmentManager;

    private FragmentTransaction mCurTransaction;
    public MyOrdersViewPagerAdapter(FragmentManager fm, List<String> mTitleList, List<BaseFragment> mFragmentList) {
        super(fm);
        this.mTitleList = mTitleList;
        this.mFragmentList = mFragmentList;
        this.mFragmentManager =fm;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (mFragmentList == null) {
            return 0;
        } else {
            return mFragmentList.size();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    /**
     * 清除缓存fragment
     * @param container ViewPager
     */
    public void clear(ViewGroup container){
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }

        for(int i=0;i<mFragmentList.size();i++){
            long itemId = this.getItemId(i);
            String name = makeFragmentName(container.getId(), itemId);
            Fragment fragment = this.mFragmentManager.findFragmentByTag(name);
            if(fragment != null){//根据对应的ID，找到fragment，删除
                mCurTransaction.remove(fragment);
            }
        }
        mCurTransaction.commitNowAllowingStateLoss();
    }
    /**
     * 等同于FragmentPagerAdapter的makeFragmentName方法，
     * 由于父类的该方法是私有的，所以在此重新定义
     * @param viewId
     * @param id
     * @return
     */
    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

}
