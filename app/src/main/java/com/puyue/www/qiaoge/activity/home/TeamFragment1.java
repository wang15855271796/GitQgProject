package com.puyue.www.qiaoge.activity.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/12/24
 */
public class TeamFragment1 extends BaseFragment {

    private Unbinder bind;
    @BindView(R.id.recyclerView)
    RecyclerView recycleView;

    //团购集合
    List<TeamActiveQueryModel.DataBean> teamList = new ArrayList<>();
    private Team2Adapter team2Adapter;

    public static TeamFragment1 getInstance() {
        TeamFragment1 fragment = new TeamFragment1();
        return fragment;
    }


    @Override
    public int setLayoutId() {
        return R.layout.fragment_team;
    }

    @Override
    public void initViews(View view) {
        bind = ButterKnife.bind(this, view);
        team2Adapter = new Team2Adapter(R.layout.item_coupons_list, teamList, new Team2Adapter.Onclick() {
            @Override
            public void addDialog() {

            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        recycleView.setAdapter(team2Adapter);

        getTeamList();

    }

    /**
     * 团购列表
     * @param
     * @param
     */
    private void getTeamList() {
        TeamActiveQueryAPI.requestData(mActivity,3+"",1+"")
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
                        if (teamActiveQueryModel.isSuccess()) {
                            teamList.clear();
                            if (teamActiveQueryModel.getData() != null) {
                                teamList.addAll(teamActiveQueryModel.getData());
                                team2Adapter.notifyDataSetChanged();

                            }
                        }
                    }
                });
    }

    @Override
    public void findViewById(View view) {

    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }
}
