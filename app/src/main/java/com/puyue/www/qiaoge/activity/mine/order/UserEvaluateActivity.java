package com.puyue.www.qiaoge.activity.mine.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.UserEvaluateAdapter;
import com.puyue.www.qiaoge.api.mine.order.GetEvaDetailAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DividerItemDecoration;
import com.puyue.www.qiaoge.helper.DividerItemDecorationTwo;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.mine.order.GetEvaDetailModel;
import com.puyue.www.qiaoge.view.StarBarView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王文博} on 2019/8/22
 */
public class UserEvaluateActivity extends BaseSwipeActivity {


    private String orderId;
    //司机评价星级
    private StarBarView sbv_star_bar;
    private TextView tv_driver_name;
    private TextView tv_evaluate_status;
    private TextView tv_send_time;
    private TextView tv_driver_content;
    private float star;


    private RecyclerView mRv;

    private UserEvaluateAdapter evaluateAdapter;
    private List<GetEvaDetailModel.DataBean.ListBean> mList = new ArrayList<>();

    private int orderDeliveryType;
    private RelativeLayout rl_driver;


    private ImageView iv_evaluate_back;
    private LinearLayout linearLayout;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.user_evaluate_activity);
    }

    @Override
    public void findViewById() {
        sbv_star_bar = findViewById(R.id.sbv_star_bar);
        tv_driver_name = findViewById(R.id.tv_driver_name);
        tv_evaluate_status = findViewById(R.id.tv_evaluate_status);
        tv_driver_content = findViewById(R.id.tv_driver_content);
        tv_send_time = findViewById(R.id.tv_send_time);
        mRv = (RecyclerView) findViewById(R.id.rv_order_evaluate);
        rl_driver = findViewById(R.id.rl_driver);
        iv_evaluate_back = findViewById(R.id.iv_evaluate_back);
        linearLayout = findViewById(R.id.linearLayout);
    }

    @Override
    public void setViewData() {
        mList.clear();

        orderId = getIntent().getStringExtra("orderId");
        orderDeliveryType = getIntent().getIntExtra("orderDeliveryType", 0);

        if (orderDeliveryType == 0) {
            rl_driver.setVisibility(View.VISIBLE);
        } else {
            rl_driver.setVisibility(View.GONE);
        }
        getData(orderId);


    }

    @Override
    public void setClickEvent() {
        iv_evaluate_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getData(String orderId) {
        GetEvaDetailAPI.requestEval(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetEvaDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetEvaDetailModel getEvaDetailModel) {

                        if (getEvaDetailModel.isSuccess()) {
                            if (getEvaDetailModel.getData().getList() == null) {
                                linearLayout.setVisibility(View.GONE);
                            } else {
                                linearLayout.setVisibility(View.VISIBLE);
                                mList.addAll(getEvaDetailModel.getData().getList());
                            }

                            if (getEvaDetailModel.getData().getDriverName() == null) {
                                rl_driver.setVisibility(View.GONE);


                                mRv.setLayoutManager(new LinearLayoutManager(mContext));
                                evaluateAdapter = new UserEvaluateAdapter(R.layout.item_user_evaluate, mList);

                                mRv.setAdapter(evaluateAdapter);

                                //添加分隔线
                                DividerItemDecoration dividerPreKillDecoration = new DividerItemDecoration(mActivity,
                                        DividerItemDecoration.VERTICAL_LIST);
                                dividerPreKillDecoration.setDivider(R.drawable.app_divider);
                                mRv.addItemDecoration(dividerPreKillDecoration);

                            } else {
                                rl_driver.setVisibility(View.VISIBLE);

                                tv_driver_name.setText("司机"+getEvaDetailModel.getData().getDriverName());
                                tv_send_time.setText(getEvaDetailModel.getData().getSendTime()+"送达");
                                if (orderDeliveryType == 0) {
                                    if (getEvaDetailModel.getData().getLevel().equals("5")) {
                                        star = 5.0f;
                                    } else if (getEvaDetailModel.getData().getLevel().equals("4")) {
                                        star = 4.0f;

                                    } else if (getEvaDetailModel.getData().getLevel().equals("3")) {
                                        star = 3.0f;

                                    } else if (getEvaDetailModel.getData().getLevel().equals("2")) {
                                        star = 2.0f;

                                    } else if (getEvaDetailModel.getData().getLevel().equals("1")) {
                                        star = 1.0f;

                                    }


                                    sbv_star_bar.setStarRating(star);
                                    setStarName(tv_evaluate_status, star);
                                    sbv_star_bar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            sbv_star_bar.setStarSolid(star);

                                        }
                                    });

                                    if (getEvaDetailModel.getData().getCommentContent() != null && StringHelper.notEmptyAndNull(getEvaDetailModel.getData().getCommentContent())) {
                                        tv_driver_content.setVisibility(View.VISIBLE);
                                        tv_driver_content.setText(getEvaDetailModel.getData().getCommentContent());
                                    } else {
                                        tv_driver_content.setVisibility(View.GONE);
                                    }


                                    mRv.setLayoutManager(new LinearLayoutManager(mContext));
                                    evaluateAdapter = new UserEvaluateAdapter(R.layout.item_user_evaluate, mList);

                                    mRv.setAdapter(evaluateAdapter);

                                    //添加分隔线
                                    DividerItemDecoration dividerPreKillDecoration = new DividerItemDecoration(mActivity,
                                            DividerItemDecoration.VERTICAL_LIST);
                                    dividerPreKillDecoration.setDivider(R.drawable.app_divider);
                                    mRv.addItemDecoration(dividerPreKillDecoration);
                                } else if (orderDeliveryType == 1) {
                                    mRv.setLayoutManager(new LinearLayoutManager(mContext));
                                    evaluateAdapter = new UserEvaluateAdapter(R.layout.item_user_evaluate, mList);

                                    mRv.setAdapter(evaluateAdapter);

                                    //添加分隔线
                                    DividerItemDecoration dividerPreKillDecoration = new DividerItemDecoration(mActivity,
                                            DividerItemDecoration.VERTICAL_LIST);
                                    dividerPreKillDecoration.setDivider(R.drawable.app_divider);
                                    mRv.addItemDecoration(dividerPreKillDecoration);
                                }
                            }


                            if (getEvaDetailModel.getData().getLevel() != null && StringHelper.notEmptyAndNull(getEvaDetailModel.getData().getLevel())) {
                                sbv_star_bar.setVisibility(View.VISIBLE);
                            } else {
                                sbv_star_bar.setVisibility(View.GONE);
                            }



/*
                            if (getEvaDetailModel.getData().getLevel().equals("5")) {
                                star = 5.0f;
                            } else if (getEvaDetailModel.getData().getLevel().equals("4")) {
                                star = 4.0f;

                            } else if (getEvaDetailModel.getData().getLevel().equals("3")) {
                                star = 3.0f;

                            } else if (getEvaDetailModel.getData().getLevel().equals("2")) {
                                star = 2.0f;

                            } else if (getEvaDetailModel.getData().getLevel().equals("1")) {
                                star = 1.0f;

                            }


                            sbv_star_bar.setStarRating(star);
                            setStarName(tv_evaluate_status, star);
                            sbv_star_bar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    sbv_star_bar.setStarSolid(star);

                                }
                            });

                            if (getEvaDetailModel.getData().getCommentContent() != null && StringHelper.notEmptyAndNull(getEvaDetailModel.getData().getCommentContent())) {
                                tv_driver_content.setVisibility(View.VISIBLE);
                                tv_driver_content.setText(getEvaDetailModel.getData().getCommentContent());
                            } else {
                                tv_driver_content.setVisibility(View.GONE);
                            }



                            mRv.setLayoutManager(new LinearLayoutManager(mContext));
                            evaluateAdapter = new UserEvaluateAdapter(R.layout.item_user_evaluate, mList);

                            mRv.setAdapter(evaluateAdapter);

                            //添加分隔线
                            DividerItemDecoration dividerPreKillDecoration = new DividerItemDecoration(mActivity,
                                    DividerItemDecoration.VERTICAL_LIST);
                            dividerPreKillDecoration.setDivider(R.drawable.app_divider);
                            mRv.addItemDecoration(dividerPreKillDecoration);*/
                        } else {
                            AppHelper.showMsg(mContext, getEvaDetailModel.getMessage());
                        }
                    }
                });


    }


    /**
     * 设置星星文字
     */
    private void setStarName(TextView tv_content, float star_num) {
        if (star_num == 5.0f) {
            tv_content.setText("很满意");

        } else if (star_num == 4.0f) {
            tv_content.setText("满意");

        } else if (star_num == 3.0f) {
            tv_content.setText("一般");

        } else if (star_num == 2.0f) {
            tv_content.setText("不满意");

        } else if (star_num == 1.0f) {
            tv_content.setText("非常差");

        }

    }

}
