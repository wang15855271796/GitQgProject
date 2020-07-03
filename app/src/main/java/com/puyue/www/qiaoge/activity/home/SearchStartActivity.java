package com.puyue.www.qiaoge.activity.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xrecyclerview.DensityUtil;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.flow.FlowLayout;
import com.puyue.www.qiaoge.activity.flow.TagAdapter;
import com.puyue.www.qiaoge.activity.flow.TagFlowLayout;
import com.puyue.www.qiaoge.adapter.HotKeyAdapter;
import com.puyue.www.qiaoge.adapter.cart.RecommendAdapter;
import com.puyue.www.qiaoge.adapter.home.SearchHistoryAdapter;
import com.puyue.www.qiaoge.api.cart.RecommendApI;
import com.puyue.www.qiaoge.api.home.CancleAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.HotKeyModel;
import com.puyue.www.qiaoge.model.home.RecommendModel;
import com.puyue.www.qiaoge.view.SearchView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/13.
 */

public class SearchStartActivity extends BaseSwipeActivity implements View.OnFocusChangeListener ,SearchView.SearchViewListener, OnGetSuggestionResultListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.et_goods)
    EditText etGoods;
    @BindView(R.id.fl_search)
    TagFlowLayout fl_search;
    @BindView(R.id.tv_arrow)
    TextView tv_arrow;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.search_recycleView)
    RecyclerView search_recycleView;
    private TextView mIvSearch;
    private TextView mTvCancle;
    private ImageView iv_clear;
    private LinearLayout iv_back;
    private String searchType;
    private String searchWord;
    private List<String> mListHistory = new ArrayList<>();
    private AlertDialog mDialog;
    private String common;
    RecommendAdapter recommendAdapter;
    List<String> list = new ArrayList<>();
    private TagAdapter mRecordsAdapter;
    List<HotKeyModel.DataBean> data;
    HotKeyAdapter hotKeyAdapter;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {

        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            searchType = bundle.getString(AppConstant.SEARCHTYPE, null);
            common=bundle.getString("good_buy",null);
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
        setContentView(R.layout.activity_seach_start);
        ButterKnife.bind(this);
    }

    @Override
    public void findViewById() {
        mIvSearch = FVHelper.fv(this, R.id.iv_activity_search_start);
        mTvCancle = FVHelper.fv(this, R.id.tv_activity_search_cancle);
        iv_clear = FVHelper.fv(this, R.id.iv_clear);
        iv_back = FVHelper.fv(this, R.id.iv_back);

        //获得推荐商品列表
        getRecommendList();

    }

    private void getRecommendList() {
        RecommendApI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecommendModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(RecommendModel recommendModel) {
                        if (recommendModel.isSuccess()) {
                            list.clear();
                            list.addAll(recommendModel.getData());
                            recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
                            recommendAdapter = new RecommendAdapter(R.layout.item_recommend, list);
                            recyclerView.setAdapter(recommendAdapter);
                            recommendAdapter.notifyDataSetChanged();
                        } else {
                            AppHelper.showMsg(mContext, recommendModel.getMessage());
                        }
                    }
                });

    }

    @Override
    public void setViewData() {
        searchWord = UserInfoHelper.getUserSearchContent(mContext);
        etGoods.setOnFocusChangeListener(this);
        searchView.setSearchViewListener(this);
        String history = UserInfoHelper.getUserSearchHistory(mContext);
        if (StringHelper.notEmptyAndNull(history)) {
            for (Object o : history.split(",")) {
                mListHistory.add((String) o);
            }
            iv_clear.setVisibility(View.VISIBLE);
        } else {
            iv_clear.setVisibility(View.GONE);
        }

        mRecordsAdapter = new TagAdapter<String>(mListHistory) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_search,fl_search, false);
                //为标签设置对应的内容
                tv.setText(s);
                return tv;
            }
        };

        //view加载完成时回调
        fl_search.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                boolean isOverFlow = fl_search.isOverFlow();
                boolean isLimit = fl_search.isLimit();
                if (isLimit && isOverFlow) {
                    tv_arrow.setVisibility(View.VISIBLE);
                } else {
                    tv_arrow.setVisibility(View.GONE);
                }
            }
        });

        fl_search.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public void onTagClick(View view, int position, FlowLayout parent) {
                String pos = mListHistory.get(position);
                UserInfoHelper.saveUserContent(mContext, pos);
                Intent intent = new Intent(mContext, SearchReasultActivity.class);
                intent.putExtra(AppConstant.SEARCHTYPE, searchType);
                intent.putExtra(AppConstant.SEARCHWORD, pos);

                if (common!=null&&StringHelper.notEmptyAndNull(common)){
                    intent.putExtra("good_buy","common");
                }else {
                    intent.putExtra("good_buy","");
                }

                savaHistory(pos);
                startActivity(intent);
                finish();
            }
        });

        tv_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_search.setLimit(false);
                mRecordsAdapter.notifyDataChanged();
            }
        });
        fl_search.setAdapter(mRecordsAdapter);

        editKeyBoard();
    }

    @Override
    public void setClickEvent() {

        mIvSearch.setOnClickListener(noDoubleClickListener);
        mTvCancle.setOnClickListener(noDoubleClickListener);
        iv_clear.setOnClickListener(noDoubleClickListener);
        iv_back.setOnClickListener(noDoubleClickListener);
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == iv_back) {
                finish();
            }
            if (view == mTvCancle) {
                hintKbTwo();
                finish();
            }
            else if (view == mIvSearch) {
                if (etGoods.getText().toString().isEmpty()) {
                    //传参和输入都是空
                    AppHelper.showMsg(mContext, "请输入商品名称");

                } else if (!etGoods.getText().toString().isEmpty()) {
                    UserInfoHelper.saveUserContent(mContext, etGoods.getText().toString());
                    //输入不为空,优先输入
                    UserInfoHelper.saveUserHistory(mContext, etGoods.getText().toString());
                    Intent intent = new Intent(mContext, SearchReasultActivity.class);
                    intent.putExtra(AppConstant.SEARCHTYPE, searchType);
                    intent.putExtra(AppConstant.SEARCHWORD, etGoods.getText().toString());
                    if (common!=null&&StringHelper.notEmptyAndNull(common)){

                        intent.putExtra("good_buy","common");
                    }else {
                        intent.putExtra("good_buy","");
                    }

                    startActivity(intent);
                    finish();
                    savaHistory(etGoods.getText().toString());
                    //  finish();
                }
                else {
                    //默认关键字
                    Intent intent = new Intent(mContext, SearchReasultActivity.class);
                    intent.putExtra(AppConstant.SEARCHTYPE, searchType);
                    intent.putExtra(AppConstant.SEARCHWORD, searchWord);
                    if (common!=null&&StringHelper.notEmptyAndNull(common)){

                        intent.putExtra("good_buy","common");
                    }else {
                        intent.putExtra("good_buy","");
                    }

                    startActivity(intent);
                    savaHistory(searchWord);
                    //  finish();
                }
            } else if (view == iv_clear) {
                showCleanDialog();
            }
        }
    };

    private void savaHistory(String text) {
        //移除相同的元素
        for (int i = 0; i < mListHistory.size(); i++) {
            if (mListHistory.get(i).equals(text)) {
                mListHistory.remove(i);
            }
        }
        for (int i = 0; i < mListHistory.size(); i++) {
            text = text + "," + mListHistory.get(i);
        }
        UserInfoHelper.saveUserHistory(mContext, text);

    }

    /*
     * 显示清除历史记录弹窗
     */
    private void showCleanDialog() {
        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_clean_history);
        mDialog.getWindow().findViewById(R.id.tv_dialog_message_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().findViewById(R.id.tv_dialog_message_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoHelper.cleanUserSearchHistory(mContext);
                mListHistory.clear();
                iv_clear.setVisibility(View.GONE);
                mRecordsAdapter.notifyDataChanged();
                mDialog.dismiss();
            }
        });
    }

    // 调用键盘弹出搜索按钮键盘
    protected void editKeyBoard() {
        etGoods.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (etGoods.getText().toString().isEmpty()) {
                        //传参和输入都是空
                        UserInfoHelper.saveUserHistory(mContext, etGoods.getText().toString());
                        AppHelper.showMsg(mContext, "请输入商品名称");
                    } else if (!etGoods.getText().toString().isEmpty()) {
                        UserInfoHelper.saveUserContent(mContext, etGoods.getText().toString());
                        //输入不为空,优先输入
                        Intent intent = new Intent(mContext, SearchReasultActivity.class);
                        intent.putExtra(AppConstant.SEARCHTYPE, searchType);
                        intent.putExtra(AppConstant.SEARCHWORD, etGoods.getText().toString());
                        if (common!=null&&StringHelper.notEmptyAndNull(common)){

                            intent.putExtra("good_buy","common");
                        }else {
                            intent.putExtra("good_buy","");
                        }
                        startActivity(intent);
                        finish();
                        savaHistory(etGoods.getText().toString());

                    } else {
                        //默认关键字
                        Intent intent = new Intent(mContext, SearchReasultActivity.class);
                        intent.putExtra(AppConstant.SEARCHTYPE, searchType);
                        intent.putExtra(AppConstant.SEARCHWORD, searchWord);
                        if (common!=null&&StringHelper.notEmptyAndNull(common)){

                            intent.putExtra("good_buy","common");
                        }else {
                            intent.putExtra("good_buy","");
                        }

                        startActivity(intent);
                        finish();
                        savaHistory(searchWord);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    //此方法只是关闭软键盘
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }



    //    判断EditText是否市区焦点
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus) {
            etGoods.setHint("请输入商品名称");
        }else {
            etGoods.setHint("");
        }
    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {

    }

    @Override
    public void onRefreshAutoComplete(String text) {
        if(!text.equals("")) {
            search_recycleView.setVisibility(View.VISIBLE);
            getReasonList(text);
        }else {
            search_recycleView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSearch(String text) {
        search_recycleView.setVisibility(View.GONE);

        if (text.isEmpty()) {
            //传参和输入都是空
            AppHelper.showMsg(mContext, "请输入商品名称");

        } else if (!text.isEmpty()) {
            UserInfoHelper.saveUserContent(mContext, text);
            //输入不为空,优先输入
            UserInfoHelper.saveUserHistory(mContext, text);
            Intent intent = new Intent(mContext, SearchReasultActivity.class);
            intent.putExtra(AppConstant.SEARCHTYPE, searchType);
            intent.putExtra(AppConstant.SEARCHWORD, text);
            if (common!=null&&StringHelper.notEmptyAndNull(common)){
                intent.putExtra("good_buy","common");
            }else {
                intent.putExtra("good_buy","");
            }

            startActivity(intent);
            finish();
            savaHistory(text);
            //  finish();
        }

    }

    private void getReasonList(String text) {
        CancleAPI.getHot(mContext,text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HotKeyModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HotKeyModel hotKeyModel) {
                        if (hotKeyModel.isSuccess()) {
                            if(hotKeyModel.getData()!=null) {
                                data = hotKeyModel.getData();
                                hotKeyAdapter = new HotKeyAdapter(R.layout.item_key_hot,data);
                                search_recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
                                search_recycleView.setAdapter(hotKeyAdapter);

                                hotKeyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        Intent intent = new Intent(mContext,SearchReasultActivity.class);
                                        intent.putExtra(AppConstant.SEARCHWORD,data.get(position).getKey()+data.get(position).getKeyBegin()+data.get(position).getKeyEnd());
                                        mContext.startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        } else {
                            AppHelper.showMsg(mContext, hotKeyModel.getMessage());
                        }
                    }
                });
    }


}
