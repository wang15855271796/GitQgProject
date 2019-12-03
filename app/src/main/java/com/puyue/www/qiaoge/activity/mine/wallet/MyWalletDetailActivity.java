package com.puyue.www.qiaoge.activity.mine.wallet;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.AccountSelectAdapter;
import com.puyue.www.qiaoge.adapter.mine.MyWalletDetailAdapter;
import com.puyue.www.qiaoge.adapter.mine.SearchAccountAdapter;
import com.puyue.www.qiaoge.adapter.mine.WalletAdapter;
import com.puyue.www.qiaoge.api.home.GetSumPriceAPI;
import com.puyue.www.qiaoge.api.mine.GetWallertRecordByPageAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SearchAccountAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DividerItemDecoration;
import com.puyue.www.qiaoge.helper.DividerItemDecorationTwo;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.GetSumPriceModel;
import com.puyue.www.qiaoge.model.home.SearchListModel;
import com.puyue.www.qiaoge.model.mine.GetWallertRecordByPageModel;
import com.puyue.www.qiaoge.popupwindow.MyWallDetailPopuwindow;
import com.puyue.www.qiaoge.view.datepicker.CustomDatePicker;
import com.puyue.www.qiaoge.view.selectmenu.MenuBarTwo;
import com.puyue.www.qiaoge.view.selectmenu.MyListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @author ${daff}
 * @date 2018/9/22.
 * 备注 我的明细
 */
public class MyWalletDetailActivity extends BaseSwipeActivity {
    private Toolbar toolbar;
    private TextView textViewDetailed;
    private LinearLayout linearLayoutOnclick;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecyclerView recyclerView;
    private LinearLayout noData;// 没有数据的界面
    private LinearLayout data; // 没有数据的界面
    private TextView textScreen;// 筛选
    private int pageNum = 1;
    private MyWalletDetailAdapter adapter;
    private List<GetWallertRecordByPageModel.DataBean.ListBean> mListData;
    private LoadingDailog dialog;

    private String selectDate;
    private String year;
    private String month;
    private LinearLayout mLlTimeSelect;

    private MyWallDetailPopuwindow popuwindow;
    List listPopuwindow = new ArrayList();
    private String recordType;
    private CustomDatePicker mCpDate;
    private ImageView detailedImage;

    //筛选
    private TextView tv_select;
    //账户选择
    private MenuBarTwo mb_bar;
    //明细选择
    private TextView tv_detail_select;
    //月份选择
    private TextView tv_month_select;
    //收入
    private TextView tv_income;
    //支出
    private TextView tv_expenditure;
    //筛选弹窗
    private PopupWindow popupWindow;
    //明细弹窗
    private PopupWindow mPopupWindowOne;
    //
    private List<SearchListModel.DataBean.List1Bean> mList1 = new ArrayList<>();//筛选
    private List<SearchListModel.DataBean.List2Bean> mList2 = new ArrayList<>();//账户
    private List<SearchListModel.DataBean.List3Bean> mList3 = new ArrayList<>();//全部明细

    private LinearLayout ll_account;
    private boolean isSelected;

    private String phone;
    private String walletRecordChannelType;

    private ArrayList<View> viewList = new ArrayList<>();
    private ArrayList<String> contentThree = new ArrayList<>();
    private ArrayList<String> titles;

    private int showType;

    private TextView tv_tool;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_wallet_detail);
    }

    @Override
    public void findViewById() {
        mLlTimeSelect = FVHelper.fv(this, R.id.ll_activity_wallet_time);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textViewDetailed = (TextView) findViewById(R.id.textViewDetailed);
        linearLayoutOnclick = (LinearLayout) findViewById(R.id.linearLayoutOnclick);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.ptrClassicFrameLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        noData = (LinearLayout) findViewById(R.id.noData);
        data = (LinearLayout) findViewById(R.id.data);
        textScreen = (TextView) findViewById(R.id.textScreen);
        detailedImage = (ImageView) findViewById(R.id.detailedImage);

        tv_select = findViewById(R.id.tv_select);
        mb_bar = findViewById(R.id.mb_bar);
        tv_detail_select = findViewById(R.id.tv_detail_select);
        tv_month_select = findViewById(R.id.tv_month_select);
        tv_income = findViewById(R.id.tv_income);
        tv_expenditure = findViewById(R.id.tv_expenditure);
        ll_account = findViewById(R.id.ll_account);
        tv_tool = findViewById(R.id.tv_tool);

    }

    @Override
    public void setViewData() {
        mListData = new ArrayList<>();
        mListData.clear();
        showType = getIntent().getIntExtra("showType", 0);

        if (showType == 1) {
            tv_tool.setText("我的账单");
        } else if (showType == 2) {
            tv_tool.setText("余额明细");
        }


        final Calendar mCalendar = Calendar.getInstance();
        long time = System.currentTimeMillis();
        mCalendar.setTimeInMillis(time);
        int i = mCalendar.get(Calendar.MONTH) + 1;

        month = i + "";
        year = mCalendar.get(Calendar.YEAR) + "";


        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                .setMessage("获取数据中")
                .setCancelable(false)
                .setCancelOutside(false);
        dialog = loadBuilder.create();
        setRecyclerView();
        requsetSearchMsg(showType);
        setTime();
        getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
        requsetPrice(recordType, year, month, phone, walletRecordChannelType,showType);

        titles = new ArrayList<>();


        titles.add("全部账户");


    }

    MyListView myListView2;

    /**
     * 获取搜索条件
     */
    private void requsetSearchMsg(int showType) {
        SearchAccountAPI.requestSearchList(mContext,showType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchListModel searchListModel) {
                        if (searchListModel.isSuccess()) {
                            mList1.clear();
                            mList2.clear();
                            mList3.clear();
                            contentThree.clear();
                            viewList.clear();
                            if (searchListModel.getData().getList1().size() > 0) {
                                mList1.addAll(searchListModel.getData().getList1());
                            }
                            if (searchListModel.getData().getList3().size() > 0) {
                                mList3.addAll(searchListModel.getData().getList3());
                            }
                            if (searchListModel.getData().getList2() != null && searchListModel.getData().getList2().size() > 0) {
                                mb_bar.setVisibility(View.VISIBLE);

                                mList2.addAll(searchListModel.getData().getList2());

                                if (mList2.size() > 0) {
                                    for (int j = 0; j < mList2.size(); j++) {
                                        contentThree.add(mList2.get(j).getValue());
                                    }
                                }

                                if (myListView2 == null) {
                                    myListView2 = new MyListView(mActivity, contentThree);
                                }

                                viewList.add(0, myListView2);
                                mb_bar.setView(titles, viewList);
                                myListView2.setOnSelectListener(new MyListView.OnSelectListener() {
                                    @Override
                                    public void getValue(String value, int position) {
                                        mb_bar.setTitle(value);
                                        pageNum = 1;

                                        phone = mList2.get(position).getKey();
                                        mListData.clear();
                                        getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
                                        requsetPrice(recordType, year, month, phone, walletRecordChannelType,showType);
                                    }
                                });

                            } else {
                                mb_bar.setVisibility(View.GONE);
                            }


                        } else {
                            AppHelper.showMsg(mContext, searchListModel.getMessage());
                        }


                    }
                });


    }

    @Override
    public void setClickEvent() {
        toolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
                requsetPrice(recordType, year, month, phone, walletRecordChannelType,showType);
            }
        }, recyclerView);

        linearLayoutOnclick.setOnClickListener(noDoubleClickListener);
        textScreen.setOnClickListener(noDoubleClickListener);
        tv_month_select.setOnClickListener(noDoubleClickListener);

        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //筛选弹窗
                showSelectPup();
            }
        });

        tv_detail_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //明细弹窗
                showDetailPup();
            }
        });
    }

    /**
     * 明细弹窗
     */
    private void showDetailPup() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.pop_account_detail, null, false);//引入弹窗布局
        mPopupWindowOne = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置背景透明
        bgAlpha(0.3f);

        //设置进出动画
        mPopupWindowOne.setAnimationStyle(R.style.PopupWindowAni);
        View parentView = LayoutInflater.from(mContext).inflate(R.layout.activity_my_wallet_detail, null);

        mPopupWindowOne.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RecyclerView rl_select_two = vPopupWindow.findViewById(R.id.rl_select_two);


        rl_select_two.setLayoutManager(new LinearLayoutManager(mContext));

        AccountSelectAdapter accountSelectAdapter = new AccountSelectAdapter(R.layout.search_account_select, mList3);

        rl_select_two.setAdapter(accountSelectAdapter);

        accountSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    walletRecordChannelType = mList3.get(position).getKey();
                    mPopupWindowOne.dismiss();
                    dialog.show();
                    tv_detail_select.setText(mList3.get(position).getValue());
                    mListData.clear();
                    getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
                    requsetPrice(recordType, year, month, phone, walletRecordChannelType,showType);


            }
        });
        //添加分隔线
        DividerItemDecoration dividerPreKillDecoration = new DividerItemDecoration(mActivity,
                DividerItemDecoration.VERTICAL_LIST);
        dividerPreKillDecoration.setDivider(R.drawable.app_divider);
        rl_select_two.addItemDecoration(dividerPreKillDecoration);

        mPopupWindowOne.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1f);
            }
        });


    }

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    /**
     * 筛选
     */
    private void showSelectPup() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.popup_select, null, false);//引入弹窗布局
        popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置背景透明
        //  addBackground();

        //设置进出动画
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);

        //引入依附的布局
        //  View parentView = LayoutInflater.from(mContext).inflate(R.layout.activity_my_wallet_detail, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移


        RecyclerView rl_select = vPopupWindow.findViewById(R.id.rl_select);
        TextView tv_cancel = vPopupWindow.findViewById(R.id.tv_cancel);
        TextView tv_ok = vPopupWindow.findViewById(R.id.tv_ok);
        SearchAccountAdapter madater;
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        rl_select.setLayoutManager(new GridLayoutManager(mContext, 3));
        madater = new SearchAccountAdapter(R.layout.search_list, mList1);

        rl_select.setAdapter(madater);

        popupWindow.showAsDropDown(ll_account, 0, 0);


        madater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                madater.selectPosition(position);
                isSelected = true;
                recordType = mList1.get(position).getKey();

            }
        });

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected) {
                    isSelected = false;
                    dialog.show();
                    popupWindow.dismiss();
                    pageNum = 1;
                    mListData.clear();
                    getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
                    requsetPrice(recordType, year, month, phone, walletRecordChannelType,showType);

                } else {
                    AppHelper.showMsg(mContext, "请选择筛选类型");
                }
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1f);
            }
        });

    }

    private void addBackground() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }


    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.linearLayoutOnclick:
                    detailedImage.setImageResource(R.mipmap.ic_mine_detailed);

                    if (null == popuwindow) {
                        setPopuwindow();
                    }
                    popuwindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
                    break;
                case R.id.textScreen:
                    if ("up".equals(textScreen.getTag())) {

                        textScreen.setTag("down");
                        mLlTimeSelect.setVisibility(View.GONE);
                    } else {
                        detailedImage.setImageResource(R.mipmap.ic_waill_up);

                        textScreen.setTag("up");
                        mLlTimeSelect.setVisibility(View.VISIBLE);
                        mCpDate.show(selectDate);
                    }
                    break;
                case R.id.tv_month_select:
                    mLlTimeSelect.setVisibility(View.VISIBLE);
                    mCpDate.show(selectDate);

                    break;
            }
        }
    };

    /**
     * 网络请求
     */
    private void getWallertRecord(final int pageNum, String recordType, String year, String month, String phone, String walletRecordChannelType, int showType) {
        GetWallertRecordByPageAPI.requestData(mContext, pageNum, 10, recordType, year, month, "0", phone, showType, walletRecordChannelType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetWallertRecordByPageModel>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetWallertRecordByPageModel getWallertRecordByPageModel) {
                        dialog.dismiss();
                        ptrClassicFrameLayout.refreshComplete();

                        if (getWallertRecordByPageModel.success) {

                            if (getWallertRecordByPageModel.data.list != null && getWallertRecordByPageModel.data.list.size() > 0) {
                                data.setVisibility(View.VISIBLE);
                                noData.setVisibility(View.GONE);
                                if (pageNum == 1) {

                                    adapter.setNewData(getWallertRecordByPageModel.data.list);
                                } else {
                                    adapter.addData(getWallertRecordByPageModel.data.list);
                                }
                            } else {
                                data.setVisibility(View.GONE);
                                noData.setVisibility(View.VISIBLE);
                            }
                            if (getWallertRecordByPageModel.data.hasNextPage) {
                                adapter.loadMoreComplete();
                            } else {
                                adapter.loadMoreEnd(false);
                            }
                        } else {
                            AppHelper.showMsg(MyWalletDetailActivity.this, getWallertRecordByPageModel.message);
                        }
                    }
                });
    }

    private void setRecyclerView() {
        adapter = new MyWalletDetailAdapter(R.layout.item_my_wallet_detail, mListData);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    ptrClassicFrameLayout.setEnabled(false);
                } else {
                    ptrClassicFrameLayout.setEnabled(true);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                mListData.clear();
            /*    year = null;
                month = null;*/
                getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
                requsetSearchMsg(showType);
                requsetPrice(recordType, year, month, phone, walletRecordChannelType,showType);

            }
        });


    }


    //  明细筛选
    private void setPopuwindow() {
        listPopuwindow.add("全部明细");
        listPopuwindow.add("收入明细");
        listPopuwindow.add("支出明细");
        popuwindow = new MyWallDetailPopuwindow(mContext, listPopuwindow, new MyWallDetailPopuwindow.ItemClick() {
            @Override
            public void GridviewOnClick(int position) {

                switch (position) {
                    case 0: // 全部明细
                        popuwindow.dismiss();
                        detailedImage.setImageResource(R.mipmap.ic_mine_detailed);
                        textViewDetailed.setText("全部明细");
                        recordType = 0 + "";
                        pageNum = 1;
                        mListData.clear();
                        getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
                        break;
                    case 1: // 收入明细
                        popuwindow.dismiss();
                        detailedImage.setImageResource(R.mipmap.ic_mine_detailed);
                        textViewDetailed.setText("收入明细");
                        recordType = 2 + "";
                        pageNum = 1;
                        mListData.clear();
                        getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
                        break;
                    case 2: //支出明细
                        popuwindow.dismiss();
                        detailedImage.setImageResource(R.mipmap.ic_mine_detailed);
                        textViewDetailed.setText("支出明细");
                        recordType = 1 + "";
                        pageNum = 1;
                        mListData.clear();
                        getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
                        break;
                }

            }
        });
    }

    private void setTime() {

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
                tv_month_select.setText(year + "-" + month);
                dialog.show();
                mListData.clear();
                getWallertRecord(pageNum, recordType, year, month, phone, walletRecordChannelType, showType);
                requsetPrice(recordType, year, month, phone, walletRecordChannelType,showType);
                textScreen.setTag("down");
                mLlTimeSelect.setVisibility(View.GONE);
            }
        }, "2017-12-01 00:00", now, hiddenView); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        mCpDate.setCancleClickListener(new CustomDatePicker.OnCancleClickListener() {
            @Override
            public void onItemClick(View view) {

                textScreen.setTag("down");
                mLlTimeSelect.setVisibility(View.GONE);
            }
        });
        mCpDate.showSpecificTime(false); // 不显示时和分
        mCpDate.setIsLoop(false); // 不允许循环滚动
    }


    /**
     * 账单价格总计
     */

    public void requsetPrice(String types, String year, String month, String phone, String walletRecordChannelType,int showType) {
        GetSumPriceAPI.requestSumPrice(mContext, types, year, month, phone, showType, walletRecordChannelType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetSumPriceModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetSumPriceModel getSumPriceModel) {


                        if (getSumPriceModel.isSuccess()) {


                            tv_income.setText("收入" + "￥ " + String.valueOf(getSumPriceModel.getData().getIn()));
                            tv_expenditure.setText("支出" + "￥ " + String.valueOf(getSumPriceModel.getData().getOut()));
                        } else {

                            AppHelper.showMsg(mContext, getSumPriceModel.getMessage());


                        }

                    }
                });

    }

}
