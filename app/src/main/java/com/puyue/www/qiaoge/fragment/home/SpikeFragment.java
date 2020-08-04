package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.adapter.home.CommonAdapter;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.model.home.CouponModel;
import com.puyue.www.qiaoge.utils.DateUtils;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;
import com.puyue.www.qiaoge.view.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/1/5
 */
public class SpikeFragment extends BaseFragment {

    private Unbinder bind;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.snap)
    SnapUpCountDownTimerView snap;
    @BindView(R.id.rl_more)
    RelativeLayout rl_more;
    List<CouponModel.DataBean.ActivesBean> actives = new ArrayList<>();
    private CommonAdapter commonAdapter;
    String style = 2+"";

    private long currentTime;
    private long startTime;
    private long endTime;

    private Date currents;
    private Date starts;
    @Override
    public int setLayoutId() {
        return R.layout.fragment_spike;
    }

    @Override
    public void initViews(View view) {
        bind = ButterKnife.bind(this, view);
        initStatusBarWhiteColor();
        EventBus.getDefault().register(this);
//        commonAdapter = new CommonAdapter(style,R.layout.item_common_list, actives);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(commonAdapter);
        rl_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,HomeGoodsListActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void initStatusBarWhiteColor() {
        //设置状态栏颜色为白色，状态栏图标为黑色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getActivity().getWindow().setStatusBarColor(Color.WHITE);
            Log.d("sssssssOOOOO..","ssss");
            StatusBarUtil.setStatusBarLightMode(getActivity());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBack(BackEvent backEvent) {
        getSpikeList();

    }

    @Override
    public void findViewById(View view) {

    }

    @Override
    public void setViewData() {
        getSpikeList();
    }

    /**
     *
     */
    private void getSpikeList() {
        IndexHomeAPI.getCouponList(mActivity,2+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CouponModel>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(CouponModel couponModel) {

                        if(couponModel.isSuccess()) {
                            actives.clear();
                            rl_more.setVisibility(View.VISIBLE);
                            if(couponModel.getData()!=null) {
                                tv_title.setText(couponModel.getData().getTitle());
                                actives.addAll(couponModel.getData().getActives());
                                commonAdapter.notifyDataSetChanged();
                                currentTime = couponModel.getData().getCurrentTime();
                                startTime = couponModel.getData().getStartTime();
                                endTime = couponModel.getData().getEndTime();
                                String current = DateUtils.formatDate(currentTime, "MM月dd日HH时mm分ss秒");
                                String start = DateUtils.formatDate(startTime, "MM月dd日HH时mm分ss秒");
                                try {
                                    currents = Utils.stringToDate(current, "MM月dd日HH时mm分ss秒");
                                    starts = Utils.stringToDate(start, "MM月dd日HH时mm分ss秒");
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                                if(currentTime>startTime) {
                                    //秒杀开始
                                    if(startTime !=0&& endTime !=0) {
                                        snap.setVisibility(View.VISIBLE);
                                        snap.setTime(true, currentTime, startTime, endTime);
                                        snap.changeBackGround(ContextCompat.getColor(mActivity, R.color.white));
                                        snap.changeTypeColor(ContextCompat.getColor(mActivity, R.color.color_F6551A));
                                        tv_time.setVisibility(View.GONE);
                                        snap.start();
                                    }else {
                                        tv_time.setVisibility(View.GONE);
                                        snap.setVisibility(View.GONE);
                                    }

                                }else {
                                    //未开始
                                    boolean exceed2 = DateUtils.isExceed2(currents, starts);

                                    if(exceed2) {
                                        //大于2
                                        tv_time.setText(start+"开抢");
                                        tv_time.setVisibility(View.VISIBLE);
                                        snap.setVisibility(View.GONE);
                                    }else {
                                        //小于2
                                        if(startTime !=0&& endTime !=0) {
                                            snap.setVisibility(View.VISIBLE);
                                            snap.setTime(true, currentTime, startTime, endTime);
                                            snap.changeBackGround(ContextCompat.getColor(mActivity, R.color.white));
                                            snap.changeTypeColor(ContextCompat.getColor(mActivity, R.color.color_F6551A));
                                            tv_time.setVisibility(View.GONE);
                                            snap.start();

                                        }else {
                                            tv_time.setVisibility(View.GONE);
                                            snap.setVisibility(View.GONE);
                                        }
                                    }
                                }
                            }else {
                                rl_more.setVisibility(View.GONE);
                            }

                        }
                    }
                });
    }

    @Override
    public void setClickEvent() {

    }
}
