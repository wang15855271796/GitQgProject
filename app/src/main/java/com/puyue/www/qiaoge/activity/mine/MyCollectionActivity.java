package com.puyue.www.qiaoge.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.EquipmentGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.MasterGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.PlaceGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.SpikeGoodsDetailsActivity;
import com.puyue.www.qiaoge.activity.home.TeamGoodsDetailActivity;
import com.puyue.www.qiaoge.adapter.mine.MyCollectionAdapter;
import com.puyue.www.qiaoge.api.mine.collection.DeleteCollectionAPI;
import com.puyue.www.qiaoge.api.mine.collection.MyCollectionListAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.collection.CollectionListModel;
import com.puyue.www.qiaoge.model.mine.collection.DeleteCollectionModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/10.
 */

public class MyCollectionActivity extends BaseSwipeActivity {

    private ImageView mIvBack;
    private PtrClassicFrameLayout mPtr;
    private RecyclerView mRv;

    private MyCollectionAdapter mAdapterMyCollection;
    private List<CollectionListModel.DataBean> mList = new ArrayList<>();
    private List<Integer> collectionId = new ArrayList<>();
    private Map<Integer, Boolean> isCheck = new HashMap<>();//存储选择状态
    private CollectionListModel mModelCollectionList;
    private LinearLayout mLlAllSelect;
    private CheckBox mCbAllSelect;
    private TextView mTvDelete;
    private boolean isAllSelected = false;
    private int selectedNum = 0;//被选中的数量,一开始为0

    private DeleteCollectionModel mModelDeleteCollection;
    private LinearLayout mLlControl;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_collection);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_my_collection_back);
        mPtr = (PtrClassicFrameLayout) findViewById(R.id.ptr_my_collection);
        mRv = (RecyclerView) findViewById(R.id.rv_my_collection);
        mLlAllSelect = (LinearLayout) findViewById(R.id.ll_collection_all_select);
        mCbAllSelect = (CheckBox) findViewById(R.id.cb_collection_all_select);
        mTvDelete = (TextView) findViewById(R.id.tv_collection_delete);
        mLlControl = (LinearLayout) findViewById(R.id.ll_my_collection_control);
    }

    @Override
    public void setViewData() {
        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                requestCollectionList();
            }
        });
        mAdapterMyCollection = new MyCollectionAdapter(R.layout.item_my_collection, mList, isCheck);
        mAdapterMyCollection.setOnItemClickListener(new MyCollectionAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(View view, int position, String type) {
                if (type.equals("check")) {
                    if (isCheck.get(position)) {
                        //如果取消，则设置map集合中为false
                        isCheck.put(position, false);
                    } else {
                        //如果选中，则设置map集合中为true
                        isCheck.put(position, true);
                    }
                    selectedNum = 0;
                    for (int i = 0; i < isCheck.size(); i++) {
                        if (isCheck.get(i)) {
                            selectedNum++;
                        }
                    }
                    if (selectedNum == mList.size()) {
                        //如果用户一个一个单选,选到全部商品,上面的全选自动选中
                        mCbAllSelect.setChecked(true);
                    } else {
                        mCbAllSelect.setChecked(false);
                    }
                    mAdapterMyCollection.notifyDataSetChanged();
                } else if (type.equals("jump")) {

                    Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, mList.get(position).getProductMainId());
                    intent.putExtra("priceType", SharedPreferencesUtil.getString(mContext,"priceType"));
                    startActivity(intent);

                    }

            }

            @Override
            public void onEventLongClick(View view, int position, String type) {

            }
        });
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    mPtr.setEnabled(false);
                } else {
                    mPtr.setEnabled(true);
                }
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRv.setAdapter(mAdapterMyCollection);
        mAdapterMyCollection.setEmptyView(AppHelper.getLoadingView(mContext));
        requestCollectionList();
    }

    /**
     * 获取收藏列表
     */
    private void requestCollectionList() {
        MyCollectionListAPI.requestCollectionList(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CollectionListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CollectionListModel collectionListModel) {
                        mPtr.refreshComplete();
                        logoutAndToHome(mContext, collectionListModel.code);

                        mModelCollectionList = collectionListModel;
                        if (mModelCollectionList.success) {
                            updateCollectionList();
                        } else {
                            AppHelper.showMsg(mContext, mModelCollectionList.message);
                        }
                    }
                });
    }

    private void updateCollectionList() {
        if (mModelCollectionList.getData() != null && mModelCollectionList.getData().size() > 0) {
            mLlControl.setVisibility(View.VISIBLE);
            mList.clear();
            mList.addAll(mModelCollectionList.getData());
            mAdapterMyCollection.setNewData(mList);
            //默认所有设备都是不被选中的状态
            isCheck.clear();
            for (int i = 0; i < mList.size(); i++) {
                isCheck.put(i, false);
            }
            mAdapterMyCollection.notifyDataSetChanged();
        } else {
            mLlControl.setVisibility(View.GONE);
            mList.clear();
            mAdapterMyCollection.notifyDataSetChanged();
        }
        if (mAdapterMyCollection.getData().size() == 0) {
            AppHelper.cancleLottieAnimation(mAdapterMyCollection.getEmptyView());
            mAdapterMyCollection.setEmptyView(AppHelper.getEmptyView(mContext));
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        mLlAllSelect.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (isAllSelected) {
                    //正在被全选
                    mCbAllSelect.setChecked(false);
                    for (int i = 0; i < isCheck.size(); i++) {
                        isCheck.put(i, false);
                    }
                    mAdapterMyCollection.notifyDataSetChanged();
                    isAllSelected = false;
                } else {
                    //没有全选中
                    mCbAllSelect.setChecked(true);
                    for (int i = 0; i < isCheck.size(); i++) {
                        isCheck.put(i, true);
                    }
                    mAdapterMyCollection.notifyDataSetChanged();
                    isAllSelected = true;
                }
            }
        });
        mTvDelete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                collectionId.clear();
                for (int i = 0; i < isCheck.size(); i++) {
                    if (isCheck.get(i)) {
                        collectionId.add(mList.get(i).getId());
                    }
                }
                if (collectionId != null && collectionId.size() > 0) {
                    showDeleteCollection();
                } else {
                    AppHelper.showMsg(mActivity, "请先选中商品");
                }
            }
        });
    }

    private void showDeleteCollection() {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_delete_collection);
        TextView mTvCancel = (TextView) window.findViewById(R.id.tv_dialog_delete_collection_cancel);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.tv_dialog_delete_collection_confirm);
        mTvCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                deleteCollection(collectionId.toString());
                alertDialog.dismiss();
            }
        });
    }

    private void deleteCollection(String s) {
        DeleteCollectionAPI.requestDeleteCollection(mContext, s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DeleteCollectionModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DeleteCollectionModel deleteCollectionModel) {
                        mModelDeleteCollection = deleteCollectionModel;
                        if (mModelDeleteCollection.success) {
                            //删除成功
                            AppHelper.showMsg(mContext, "删除成功");
                            mPtr.autoRefresh();
                        } else {
                            AppHelper.showMsg(mActivity, mModelDeleteCollection.message);
                        }
                    }
                });
    }
}
