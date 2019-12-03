package com.puyue.www.qiaoge.activity.home;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.TeamActiveQueryAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
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
 */
public class TeamDetailActivity extends BaseSwipeActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rv_detail)
    RecyclerView rv_detail;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    private TeamActiveQueryAdapter teamActiveQueryAdapter;
    //团购集合
    List<TeamActiveQueryModel.DataBean.ListBean> teamList = new ArrayList<>();
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.common_detail);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        tv_title.setText("团购专区");
        setTranslucentStatus();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setViewData() {
        teamActiveQuery("version","1");

        //团购
        teamActiveQueryAdapter = new TeamActiveQueryAdapter(R.layout.item_teame_adapter, teamList, new TeamActiveQueryAdapter.OnClick() {
            @Override
            public void addCarOnclick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    TeamActiveQueryModel.DataBean.ListBean listBean = teamList.get(position);
                    addCar(listBean.activeId, "", 11, "1");
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }

                teamActiveQueryAdapter.notifyDataSetChanged();
            }
        });

        rv_detail.setLayoutManager(new LinearLayoutManager(mContext));
        rv_detail.setAdapter(teamActiveQueryAdapter);

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
    private void teamActiveQuery(String version,String clientType) {
        TeamActiveQueryAPI.requestData(mContext,version,clientType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TeamActiveQueryModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TeamActiveQueryModel teamActiveQueryModel) {
                        if (teamActiveQueryModel.success) {
                            teamList.clear();
                            if (teamActiveQueryModel.data.list != null) {
                                teamList.addAll(teamActiveQueryModel.data.list);
                                Log.d("dfdrerererereeww..",teamList.size()+"");
                                teamActiveQueryAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                });
    }
}
