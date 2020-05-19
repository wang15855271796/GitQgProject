package com.puyue.www.qiaoge.fragment.home;


import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${王涛} on 2020/1/4
 */
public class HomeFragmentss extends BaseFragment{
    Unbinder binder;
    @BindView(R.id.content)
    FrameLayout frameLayout;
    NewFragment newFragment;
//    @BindView(R.id.ll_top)
//    LinearLayout ll_top;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rl_top)
    RelativeLayout rl_top;
    @Override
    public int setLayoutId() {
        return R.layout.test1;
    }
    private int mMaxScrollSize;
    @Override
    public void initViews(View view) {
        binder = ButterKnife.bind(this, view);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if(Math.abs(verticalOffset)>300) {
//                    ll_top.setVisibility(View.VISIBLE);
////                    toolbar2.setVisibility(View.VISIBLE);
//                }else {
//                    ll_top.setVisibility(View.INVISIBLE);
////                    toolbar2.setVisibility(View.GONE);
//                }
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                if(totalScrollRange ==Math.abs(verticalOffset)) {
                    rl_top.setTop(90);
                }else {
                    rl_top.setTop(0);
                }

//                if (mMaxScrollSize == 0){
//                    mMaxScrollSize = appBarLayout.getTotalScrollRange();
//                }
//                int currentScrollPercentage = (Math.abs(verticalOffset)) * 100 / mMaxScrollSize;
//                float alpha=(float) (1 - currentScrollPercentage/20.0);
//                ll_top.setAlpha(1);
            }
        });
    }

    @Override
    public void findViewById(View view) {

    }

    @Override
    public void setViewData() {
        switchRb4();
    }

    @Override
    public void setClickEvent() {

    }

    private void switchRb4() {
        fragmentTransaction = supportFragmentManager.beginTransaction();
        if (newFragment == null) {
            newFragment = new NewFragment();
            fragmentTransaction.add(R.id.content, newFragment, NewFragment.class.getCanonicalName());
        }
        fragmentTransaction.show(newFragment);

        fragmentTransaction.commitAllowingStateLoss();
    }

}
