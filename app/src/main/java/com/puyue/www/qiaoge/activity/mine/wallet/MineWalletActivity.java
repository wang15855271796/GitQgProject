package com.puyue.www.qiaoge.activity.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.WalletAdapter;
import com.puyue.www.qiaoge.api.mine.GetSubUserListAPI;
import com.puyue.www.qiaoge.api.mine.GetWallertRecordByPageAPI;
import com.puyue.www.qiaoge.api.mine.GetWalletAmountAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.GetSubUserListModel;
import com.puyue.www.qiaoge.model.mine.GetWallertRecordByPageModel;
import com.puyue.www.qiaoge.model.mine.GetWalletAmountModel;
import com.puyue.www.qiaoge.view.SpinerPopWindow;
import com.puyue.www.qiaoge.view.datepicker.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/9.
 */

/*
public class MineWalletActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private LinearLayout mLlTime;
    private ImageView mIvArrow;
    private TextView mTvAmount;
    private RadioButton mRbAll;
    private RadioButton mRbOut;
    private RadioButton mRbIn;
    private PtrClassicFrameLayout mPtrRefresh;
    private RecyclerView mRvData;
    private TextView mTvWithDraw;
    private TextView mTvRecharge;
    private LinearLayout mLlTimeSelect;
    private RadioGroup mRgType;
    private LinearLayout mLlAccount;
    private ImageView mIvNoData;

    private CustomDatePicker mCpDate;
    private String selectDate;

    private WalletAdapter mAdapterWallet;
    private List<GetWallertRecordByPageModel.DataBean.ListBean> mListData = new ArrayList<>();

    private TextView mTvAccount;
    private TextView mTvType;
    private List<String> mListAccount = new ArrayList<>();
    private List<Integer> mListAccountID = new ArrayList<>();
    private List<String> mListType = new ArrayList<>();
    private List<Byte> mListTypeID = new ArrayList<>();
    private SpinerPopWindow<String> mSpinerAccount;
    private SpinerPopWindow<String> mSpinerType;

    private boolean mainAccount;
    private String[] typeString = {"全部", "购物", "充值", "提现", "退款"};
    private byte recordType = 0;
    private String flowRecordType = "0";
    private String year;
    private String month;
    private String subUserId;
    private int pageNum = 1;
    private int pageSize = 10;


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            mainAccount = bundle.getBoolean(AppConstant.ACCOUNTTYPE, false);
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
        setContentView(R.layout.activity_mine_wallet);
    }

    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_mine_wallet_back);
        mLlTime = FVHelper.fv(this, R.id.ll_mine_wallet_time);
        mIvArrow = FVHelper.fv(this, R.id.iv_mine_wallet_arrow);
        mTvAmount = FVHelper.fv(this, R.id.tv_mine_wallet_amount);
        mRbAll = FVHelper.fv(this, R.id.rb_mine_wallet_all);
        mRbOut = FVHelper.fv(this, R.id.rb_mine_wallet_out);
        mRbIn = FVHelper.fv(this, R.id.rb_mine_wallet_in);
        mPtrRefresh = FVHelper.fv(this, R.id.ptr_mine_wallet);
        mRvData = FVHelper.fv(this, R.id.rv_mine_wallet);
        mTvWithDraw = FVHelper.fv(this, R.id.tv_mine_wallet_withdraw);
        mTvRecharge = FVHelper.fv(this, R.id.tv_mine_wallet_recharge);
        mLlTimeSelect = FVHelper.fv(this, R.id.ll_activity_wallet_time);
        mIvBack = FVHelper.fv(this, R.id.iv_mine_wallet_back);
        mLlTime = FVHelper.fv(this, R.id.ll_mine_wallet_time);
        mIvArrow = FVHelper.fv(this, R.id.iv_mine_wallet_arrow);
        mTvAmount = FVHelper.fv(this, R.id.tv_mine_wallet_amount);
        mRbAll = FVHelper.fv(this, R.id.rb_mine_wallet_all);
        mRbOut = FVHelper.fv(this, R.id.rb_mine_wallet_out);
        mRbIn = FVHelper.fv(this, R.id.rb_mine_wallet_in);
        mTvWithDraw = FVHelper.fv(this, R.id.tv_mine_wallet_withdraw);
        mTvRecharge = FVHelper.fv(this, R.id.tv_mine_wallet_recharge);
        mRgType = FVHelper.fv(this, R.id.rg_mine_wallet);
        mLlAccount = FVHelper.fv(this, R.id.ll_activity_child_account);

        mTvAccount = FVHelper.fv(this, R.id.tv_activity_wallet_account);
        mTvType = FVHelper.fv(this, R.id.tv_activity_wallet_type);
        mIvNoData = FVHelper.fv(this, R.id.iv_mine_wallet_no_data);
    }

    @Override
    public void setViewData() {
        if (mainAccount) {
            //是主账号,正常显示,获取子账号
            getSubUserList();
        } else {
            mLlAccount.setVisibility(View.GONE);
            mTvWithDraw.setVisibility(View.GONE);
        }

        View hiddenView = getLayoutInflater().inflate(R.layout.view_date_picker, mLlTimeSelect, false); //hiddenView是隐藏的View，
        mLlTimeSelect.addView(hiddenView);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        selectDate = now.split(" ")[0];
        mCpDate = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                selectDate = time.split(" ")[0];
                year = selectDate.split("-")[0];
                month = selectDate.split("-")[1];
                Log.d("---->",selectDate+"-selectDate--->"+year+"--year-->"+month+"---");
                getWallertRecord(pageNum, pageSize, recordType, year, month, subUserId, flowRecordType);
                mIvArrow.setImageResource(R.mipmap.icon_arrow_down);
                mLlTime.setTag("down");
                mLlTimeSelect.setVisibility(View.GONE);
            }
        }, "2017-12-01 00:00", now, hiddenView); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        mCpDate.setCancleClickListener(new CustomDatePicker.OnCancleClickListener() {
            @Override
            public void onItemClick(View view) {
                mIvArrow.setImageResource(R.mipmap.icon_arrow_down);
                mLlTime.setTag("down");
                mLlTimeSelect.setVisibility(View.GONE);
            }
        });
        mCpDate.showSpecificTime(false); // 不显示时和分
        mCpDate.setIsLoop(false); // 不允许循环滚动
        mAdapterWallet = new WalletAdapter(R.layout.item_mine_wallet, mListData);
        mRvData.setLayoutManager(new LinearLayoutManager(mContext));
        mRvData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    mPtrRefresh.setEnabled(false);
                } else {
                    mPtrRefresh.setEnabled(true);
                }
            }
        });
        mRvData.setAdapter(mAdapterWallet);
        initSpinner();
//        getWalletAmount();
//        getWallertRecord(pageNum, pageSize, recordType, year, month, subUserId, flowRecordType);
    }

    private void initSpinner() {
        for (byte i = 0; i < typeString.length; i++) {
            mListType.add(typeString[i]);
            mListTypeID.add(i);
        }
        mSpinerType = new SpinerPopWindow<String>(this, mListType, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSpinerType.dismiss();
                mTvType.setText(mListType.get(i));
                flowRecordType = String.valueOf(i);
                pageNum = 1;
                getWallertRecord(pageNum, pageSize, recordType, year, month, subUserId, flowRecordType);
                mTvType.setBackgroundResource(R.mipmap.icon_spinner_down);

            }
        });
        mTvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvType.setBackgroundResource(R.mipmap.icon_spinner_up);
                mSpinerType.setWidth(mTvType.getWidth());
                mSpinerType.showAsDropDown(mTvType);
            }
        });
        mSpinerType.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mTvType.setBackgroundResource(R.mipmap.icon_spinner_down);
            }
        });
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mLlTime.setOnClickListener(noDoubleClickListener);
        mTvWithDraw.setOnClickListener(noDoubleClickListener);
        mTvRecharge.setOnClickListener(noDoubleClickListener);
        mAdapterWallet.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                getWallertRecord(pageNum, pageSize, recordType, year, month, subUserId, flowRecordType);
            }
        }, mRvData);
        mRgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_mine_wallet_all:
                        //全部
                        pageNum = 1;
                        recordType = 0;
                        getWallertRecord(pageNum, pageSize, recordType, year, month, subUserId, flowRecordType);
                        break;
                    case R.id.rb_mine_wallet_in:
                        //收入
                        pageNum = 1;
                        recordType = 2;
                        getWallertRecord(pageNum, pageSize, recordType, year, month, subUserId, flowRecordType);
                        break;
                    case R.id.rb_mine_wallet_out:
                        //支出
                        pageNum = 1;
                        recordType = 1;
                        getWallertRecord(pageNum, pageSize, recordType, year, month, subUserId, flowRecordType);
                        break;
                    default:
                        break;
                }
            }
        });
        mRbAll.setChecked(true);
        mPtrRefresh.disableWhenHorizontalMove(true);
        mPtrRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                year = null;
                month = null;
                subUserId = null;
                flowRecordType = "0";
                mTvAccount.setText("请选择");
                mTvType.setText("请选择");
                getWallertRecord(pageNum, pageSize, recordType, year, month, subUserId, flowRecordType);
                getWalletAmount();
            }
        });

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == mLlTime) {
                if ("up".equals(mLlTime.getTag())) {
                    mIvArrow.setImageResource(R.mipmap.icon_arrow_down);
                    mLlTime.setTag("down");
                    mLlTimeSelect.setVisibility(View.GONE);
                } else {
                    mIvArrow.setImageResource(R.mipmap.icon_arrow_up);
                    mLlTime.setTag("up");
                    mLlTimeSelect.setVisibility(View.VISIBLE);
                    mCpDate.show(selectDate);
                }
            } else if (view == mTvWithDraw) {
                startActivity(new Intent(mContext, WithDrawActivity.class));
            } else if (view == mTvRecharge) {
                startActivity(new Intent(mContext, RechargeActivity.class));
            }
        }
    };

    */
/**
     * 获取子账号
     *//*

    private void getSubUserList() {
        GetSubUserListAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetSubUserListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetSubUserListModel getSubUserListModel) {
                        if (getSubUserListModel.success) {
                            for (int i = 0; i < getSubUserListModel.data.size(); i++) {
                                mListAccount.add(getSubUserListModel.data.get(i).subUserName);
                                mListAccountID.add(getSubUserListModel.data.get(i).subUserId);
                            }
                            mListAccount.add("全部");
                            mListAccountID.add(0);

                            mSpinerAccount = new SpinerPopWindow<String>(mContext, mListAccount, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    mSpinerAccount.dismiss();
                                    mTvAccount.setText(mListAccount.get(i));
                                    subUserId = mListAccountID.get(i).toString();
                                    pageNum = 1;
                                    getWallertRecord(pageNum, pageSize, recordType, year, month, subUserId, flowRecordType);
                                    mTvAccount.setBackgroundResource(R.mipmap.icon_spinner_down);
                                }
                            });
                            mTvAccount.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mTvAccount.setBackgroundResource(R.mipmap.icon_spinner_up);
                                    mSpinerAccount.setWidth(mTvAccount.getWidth());
                                    mSpinerAccount.showAsDropDown(mTvAccount);
                                }
                            });
                            mSpinerAccount.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    mTvAccount.setBackgroundResource(R.mipmap.icon_spinner_down);
                                }
                            });
                        } else {
                           AppHelper.showMsg(mContext, getSubUserListModel.message);
                        }

                    }
                });
    }

    */
/**
     * 获取账户余额
     *//*

    private void getWalletAmount() {
        GetWalletAmountAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetWalletAmountModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetWalletAmountModel getWalletAmountModel) {
                        if (getWalletAmountModel.success) {
                            mTvAmount.setText(getWalletAmountModel.data);
                        } else {
                           AppHelper.showMsg(mContext, getWalletAmountModel.message);
                        }

                    }
                });
    }

    */
/**
     * 获取支出记录
     * (0全部，1支出，2收入)|Byte
     * (0全部，1购物，2充值，3提现，4退款)
     *//*

    private void getWallertRecord(final int pageNum, int pageSize, byte recordType, String year, String month, String subUserId, String flowRecordType) {
        GetWallertRecordByPageAPI.requestData(mContext, pageNum, pageSize, recordType, year, month, flowRecordType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetWallertRecordByPageModel>() {
                    @Override
                    public void onCompleted() {
                        mPtrRefresh.refreshComplete();

                    }

                    @Override
                    public void onError(Throwable e) {
                        mPtrRefresh.refreshComplete();
                    }

                    @Override
                    public void onNext(GetWallertRecordByPageModel getWallertRecordByPageModel) {
                        logoutAndToHome(mContext, getWallertRecordByPageModel.code);
                        if (getWallertRecordByPageModel.success) {
                            if (getWallertRecordByPageModel.data.list != null && getWallertRecordByPageModel.data.list.size() > 0) {
                                mRvData.setVisibility(View.VISIBLE);
                                mIvNoData.setVisibility(View.GONE);
                                if (pageNum == 1) {
                                    mAdapterWallet.setNewData(getWallertRecordByPageModel.data.list);
                                } else {
                                    mAdapterWallet.addData(getWallertRecordByPageModel.data.list);
                                }
                            } else {
                                mRvData.setVisibility(View.GONE);
                                mIvNoData.setVisibility(View.VISIBLE);
                            }
                            if (getWallertRecordByPageModel.data.hasNextPage) {
                                mAdapterWallet.loadMoreComplete();
                            } else {
                                mAdapterWallet.loadMoreEnd(false);
                            }
                        } else {
                           AppHelper.showMsg(MineWalletActivity.this, getWallertRecordByPageModel.message);
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPtrRefresh.autoRefresh();
    }

}*/
