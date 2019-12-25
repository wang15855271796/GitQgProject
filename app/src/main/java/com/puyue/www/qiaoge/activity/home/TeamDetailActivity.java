package com.puyue.www.qiaoge.activity.home;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.TeamActiveQueryAdapter;
import com.puyue.www.qiaoge.adapter.mine.ViewPagerAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.home.TabModel;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/11
 *团购列表
 */
public class TeamDetailActivity extends BaseSwipeActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tab_layout;
    private final String[] mTitles = {"进行中", "待开始"};
    private TeamActiveQueryAdapter teamActiveQueryAdapter;
    TabModel tabModels;
    private ViewPagerAdapters adapter;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_team_list);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);

        tv_title.setText("超值组合");

        adapter = new ViewPagerAdapters(getSupportFragmentManager());
        teamActiveQuery();
        adapter.addFragment(TeamFragment.getInstance());
        adapter.addFragment(TeamFragment1.getInstance());
        viewPager.setAdapter(adapter);
        tab_layout.setViewPager(viewPager, mTitles);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setViewData() {


        //团购
//        teamActiveQueryAdapter = new TeamActiveQueryAdapter(R.layout.item_teame_adapter, teamList, new TeamActiveQueryAdapter.OnClick() {
//            @Override
//            public void addCarOnclick(int position) {
//                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
//                    TeamActiveQueryModel.DataBean.ListBean listBean = teamList.get(position);
//                    addCar(listBean.activeId, "", 11, "1");
//                } else {
//                    AppHelper.showMsg(mActivity, "请先登录");
//                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
//                }
//
//                teamActiveQueryAdapter.notifyDataSetChanged();
//            }
//        });

//        rv_detail.setLayoutManager(new LinearLayoutManager(mContext));
//        rv_detail.setAdapter(teamActiveQueryAdapter);

    }

    @Override
    public void setClickEvent() {

    }

    /**
     * 添加购物车
     * @param businessId
     * @param productCombinationPriceVOList
     * @param businessType
     * @param totalNum
     */
    private void addCar(int businessId, String productCombinationPriceVOList, int businessType, String totalNum) {
        AddCartAPI.requestData(mActivity, businessId, productCombinationPriceVOList, businessType, String.valueOf(totalNum))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddCartModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddCartModel addCartModel) {
                        if (addCartModel.success) {
                            AppHelper.showMsg(mActivity, "成功加入购物车");
//                            getCartNum();
                        } else {
                            AppHelper.showMsg(mActivity, addCartModel.message);
                            Log.e("Crash", "onNext: " + addCartModel.message);
                        }

                    }
                });
    }

    /**
     * 团购活动接口
     */
    private void teamActiveQuery() {
        TeamActiveQueryAPI.changeTab(mContext,11+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TabModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TabModel tabModel) {
                        if (tabModel.isSuccess()) {
                            tabModels = tabModel;



                        }
                    }
                });
    }
}
