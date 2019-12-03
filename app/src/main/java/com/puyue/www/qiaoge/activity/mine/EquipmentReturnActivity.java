package com.puyue.www.qiaoge.activity.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.EquipmentReturnViewPagerAdapter;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.fragment.mine.EquipmentReturnedFragment;
import com.puyue.www.qiaoge.fragment.mine.EquipmentReturningFragment;
import com.puyue.www.qiaoge.fragment.mine.EquipmentWaitReturnFragment;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class EquipmentReturnActivity extends BaseSwipeActivity {

    private SlidingTabLayout mTab;
    private ImageView mIvBack;
    private ViewPager mViewPager;

    private String[] titles = {"待归还", "归还中", "已归还"};
    private EquipmentReturnViewPagerAdapter mAdapterEquipmentReturn;
    private List<Fragment> mListFragments = new ArrayList<>();

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_equipment_return);
    }

    @Override
    public void findViewById() {
        mTab = (SlidingTabLayout) findViewById(R.id.tab_equipment_return);
        mIvBack = (ImageView) findViewById(R.id.iv_equipment_return_back);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_equipment_return);
    }

    @Override
    public void setViewData() {
        mListFragments.add(new EquipmentWaitReturnFragment());
        mListFragments.add(new EquipmentReturningFragment());
        mListFragments.add(new EquipmentReturnedFragment());
        mAdapterEquipmentReturn = new EquipmentReturnViewPagerAdapter(getSupportFragmentManager(), mListFragments);
        mViewPager.setAdapter(mAdapterEquipmentReturn);
        mTab.setViewPager(mViewPager, titles);
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
    }
}
