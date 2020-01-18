package com.puyue.www.qiaoge.activity.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CartActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.CommonProductAdapter;
import com.puyue.www.qiaoge.adapter.home.GetProductListAdapter;
import com.puyue.www.qiaoge.adapter.home.ItemConditionAdapter;
import com.puyue.www.qiaoge.adapter.home.NewArrivalAdapter;
import com.puyue.www.qiaoge.adapter.home.PriceReductionAdapter;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.adapter.home.SeckillGoodActivity;
import com.puyue.www.qiaoge.adapter.home.SpecialGoodAdapter;
import com.puyue.www.qiaoge.adapter.home.SpikeActiveAdapter;
import com.puyue.www.qiaoge.adapter.home.SpikeActiveNewAdapter;
import com.puyue.www.qiaoge.adapter.home.SpikeActiveQueryAdapter;
import com.puyue.www.qiaoge.adapter.home.TeamActiveQueryAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.cart.GetCartNumAPI;
import com.puyue.www.qiaoge.api.home.GetCommonProductAPI;
import com.puyue.www.qiaoge.api.home.GetMoreSpecialAPI;
import com.puyue.www.qiaoge.api.home.GetProductListAPI;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.ProductListAPI;
import com.puyue.www.qiaoge.api.home.SecKillMoreListAPI;
import com.puyue.www.qiaoge.api.home.SpikeActiveQueryAPI;
import com.puyue.www.qiaoge.api.home.SpikeNewActiveQueryAPI;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.calendar.listener.OnPagerChangeListener;
import com.puyue.www.qiaoge.calendar.utils.CalendarUtil;
import com.puyue.www.qiaoge.calendar.utils.SelectBean;
import com.puyue.www.qiaoge.calendar.weiget.CalendarView;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DividerItemDecoration;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.TwoDeviceHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.GetCommonProductModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
import com.puyue.www.qiaoge.model.home.ItemConditionModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.model.home.SeckillListModel;
import com.puyue.www.qiaoge.model.home.SpecialMoreGoodModel;
import com.puyue.www.qiaoge.model.home.SpikeActiveQueryModel;
import com.puyue.www.qiaoge.model.home.SpikeNewQueryModel;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.model.market.MarketClassifyModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;
import com.puyue.www.qiaoge.view.SuperTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */
//列表
public class HomeGoodsListActivity extends BaseSwipeActivity {

    public TextView tv_num;
    private ImageView mIvBack;
    private TextView mTvTitle;
    private RecyclerView mRvData;
    private RecyclerView mRvSpikeData;
    //秒杀预告，特惠
    private LinearLayout linearLayoutSpike;
    TextView tv_call;
    //秒杀活动
    private SpikeActiveNewAdapter mAdapterNewSpike;
    private List<SpikeNewQueryModel.DataBean> mListSpikeNew = new ArrayList<>();

    private SpikeActiveQueryAdapter mAdapterSpikeQuery;

    //秒杀活动
    private SpikeActiveAdapter mAdapterSpike;
    private List<SpikeActiveQueryModel.DataBean.ListBean> mListSpike = new ArrayList<>();
    //秒杀列表
    private List<SeckillListModel.DataBean.KillsBean> mListSeckill = new ArrayList<>();
    private int currentPosition;
    private String pageType;
    private int pageNum = 1;
    private int pageSize = 9;
    private TextView mTvSpikeTitle;
    private boolean isFirst;
    private RelativeLayout rl_good_cart;
    private SuperTextView text_cart_num;
    int isSelected;
    int shopTypeId;
    boolean isChecked = false;
    private long startTime;
    private long currentTime;
    private long endTime;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            pageType = bundle.getString(AppConstant.PAGETYPE, null);
            isFirst = true;
            Log.i("aaaa", "handleExtra: " + pageType);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_home_goods_list);
    }


    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
        mTvTitle = FVHelper.fv(this, R.id.tv_activity_goods_list_title);
        tv_num = FVHelper.fv(this, R.id.tv_num);
        tv_call =  FVHelper.fv(this, R.id.tv_call);
        mRvData = FVHelper.fv(this, R.id.rv_activity_goods_list);
        linearLayoutSpike = FVHelper.fv(this, R.id.linearLayout_spike);

        mRvSpikeData = FVHelper.fv(this, R.id.recyclerview_spike_content);
        mTvSpikeTitle = FVHelper.fv(this, R.id.tv_spike_content);
        rl_good_cart = FVHelper.fv(this, R.id.rl_good_cart);
        text_cart_num = FVHelper.fv(this, R.id.text_cart_num);
        EventBus.getDefault().register(this);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int warnMe = SharedPreferencesUtil.getInt(mContext, "warnMe");
                if(warnMe==0) {
                    tv_call.setText("取消本场秒杀提醒");
                    SharedPreferencesUtil.saveInt(mContext, "warnMe",1);
                }else {
                    tv_call.setText("添加本场秒杀提醒");
                    SharedPreferencesUtil.saveInt(mContext, "warnMe",0);
                }
            }
        });
    }

    @Override
    public void setViewData() {
        getCartNum();
        judgePageType();//进行差异性的设置。
        judgeRefreshData();

    }

    /**
     * 购物车数量
     */
    private void getCartNum() {
        GetCartNumAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCartNumModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetCartNumModel getCartNumModel) {
                        if (getCartNumModel.isSuccess()) {
                            if(getCartNumModel.getData().getNum().equals("0")) {
                                tv_num.setVisibility(View.GONE);
                            }else {
                                tv_num.setVisibility(View.VISIBLE);

                                tv_num.setText(getCartNumModel.getData().getNum());
                            }
                        } else {
                            AppHelper.showMsg(mContext, getCartNumModel.getMessage());
                        }
                    }
                });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCartNum(ReduceNumEvent event) {
        getCartNum();
    }
    @Override
    public void setClickEvent() {

        rl_good_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    startActivity(new Intent(mContext, CartActivity.class));
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }


            }
        });

    }

    /**
     * 判断是哪种类型的页面
     **/
    private void judgePageType() {
        mTvTitle.setText("限时秒杀");
        linearLayoutSpike.setVisibility(View.VISIBLE);
        mRvData.setLayoutManager(new LinearLayoutManager(mContext));
        mRvData.setBackgroundColor(Color.parseColor("#F5F5F5"));

    }

    /**
     * 判断获取哪个页面的刷新数据
     */
    private void judgeRefreshData() {
        pageNum = 1;
        //秒杀活动
        getNewSpikeTool();

    }


    /**
     * 秒杀专区更多-顶部
     */
    private void getNewSpikeTool() {
        SpikeNewActiveQueryAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SpikeNewQueryModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(SpikeNewQueryModel spikeNewQueryModel) {
                        if (spikeNewQueryModel.isSuccess()) {
                            mListSpikeNew.clear();

                            if (spikeNewQueryModel.getData() != null) {
                                mListSpikeNew.addAll(spikeNewQueryModel.getData());
                                for (int i = 0; i <mListSpikeNew.size() ; i++) {
                                    startTime = mListSpikeNew.get(i).getStartTime();
                                    currentTime = mListSpikeNew.get(i).getCurrentTime();
                                    endTime = mListSpikeNew.get(i).getEndTime();

                                }
                                //秒杀专区-顶部
                                mAdapterNewSpike = new SpikeActiveNewAdapter(mContext, mListSpikeNew,startTime,endTime,currentTime);
                                mRvSpikeData.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                                mRvSpikeData.setAdapter(mAdapterNewSpike);

                                mAdapterNewSpike.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        mAdapterNewSpike.selectPosition(position);
                                        spikeActiveQuery(mListSpikeNew.get(position).getActiveId());
                                        currentPosition = position;
                                        mAdapterNewSpike.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onItemLongClick(View view, int position) {

                                    }
                                });

                                if (isFirst) {
                                    mAdapterNewSpike.selectPosition(0);
                                    spikeActiveQuery(spikeNewQueryModel.getData().get(0).getActiveId());
                                    getStat(spikeNewQueryModel.getData().get(0).getActiveId());
                                } else {
                                    mAdapterNewSpike.selectPosition(currentPosition);
                                    spikeActiveQuery(spikeNewQueryModel.getData().get(currentPosition).getActiveId());
                                }

                                isFirst = false;
                                mAdapterNewSpike.notifyDataSetChanged();
                            }


                        } else {
                            AppHelper.showMsg(mContext, spikeNewQueryModel.getMessage());
                        }

                    }
                });
    }


    /**
     * 秒杀-更多-列表
     */
    private void spikeActiveQuery(int activeId) {
        SecKillMoreListAPI.requestMoreListData(mContext, activeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SeckillListModel>() {



                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(SeckillListModel seckillListModel) {
                        if (seckillListModel.success) {

                            mAdapterSpikeQuery = new SpikeActiveQueryAdapter(R.layout.spike_new_active_product, seckillListModel.data.kills, activeId);
                            mRvData.setAdapter(mAdapterSpikeQuery);

                            mAdapterSpikeQuery.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, SeckillGoodActivity.class);
                                    intent.putExtra(AppConstant.ACTIVEID, mListSeckill.get(position).activeId);
                                    startActivity(intent);
                                }
                            });

                            if(seckillListModel.data.warnMe==0) {
                                tv_call.setText("添加本场秒杀提醒");
                                SharedPreferencesUtil.saveInt(mContext,"warnMe",0);
                            }else {
                                tv_call.setText("取消提醒");
                                SharedPreferencesUtil.saveInt(mContext,"warnMe",1);
                            }
                            int flag = seckillListModel.data.flag;
                            UserInfoHelper.saveSpikePosition(mContext, String.valueOf(flag));

                            mListSeckill.clear();
                            if (seckillListModel.data.kills != null) {
                                mListSeckill.addAll(seckillListModel.data.kills);
                                //写显示倒计时的时间
                                //秒杀列表

                            }

                        } else {

                            AppHelper.showMsg(mActivity, seckillListModel.message);

                        }
                    }
                });
    }

    /**
     * 获取提醒状态  SpikeActiveQueryAPI
     */
    private void getStat(int activeId) {
        SpikeActiveQueryAPI.requestData(mContext, activeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(BaseModel seckillListModel) {
                        ToastUtil.showSuccessMsg(mActivity,seckillListModel.message);
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SelectBean.cleanDate();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isFirst = false;
    }



}

