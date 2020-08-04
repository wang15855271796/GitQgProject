package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CouponDetailActivity;
import com.puyue.www.qiaoge.adapter.home.CommonAdapter;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.model.home.CouponModel;
import com.puyue.www.qiaoge.view.Snap;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;
import com.puyue.www.qiaoge.view.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
public class TeamsFragment extends BaseFragment {

    private Unbinder bind;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.snap)
    SnapUpCountDownTimerView snap;
    @BindView(R.id.rl_more)
    RelativeLayout rl_more;
    List<CouponModel.DataBean.ActivesBean> actives = new ArrayList<>();
    private CommonAdapter commonAdapter;
    String style = 3+"";
    @Override
    public int setLayoutId() {
        return R.layout.fragment_spike;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
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
                Intent intent = new Intent(mActivity,CouponDetailActivity.class);
                startActivity(intent);
            }
        });
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBack(BackEvent backEvent) {
        Log.d("weweeerrrrrssss....",actives.size()+"");
        getSpikeList();
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
    @Override
    public void findViewById(View view) {

    }

    @Override
    public void setViewData() {
        getSpikeList();
    }

    /**
     *组合数据
     */
    private void getSpikeList() {
        IndexHomeAPI.getCouponList(mActivity,3+"")
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
                            if(couponModel.getData()!=null) {
                                rl_more.setVisibility(View.VISIBLE);
                                actives.clear();
                                tv_title.setText(couponModel.getData().getTitle());
                                actives.addAll(couponModel.getData().getActives());
                                tv_desc.setText(couponModel.getData().getDesc());
                                commonAdapter.notifyDataSetChanged();
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
