package com.puyue.www.qiaoge.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.adapter.mine.MessageCenterAdapter;
import com.puyue.www.qiaoge.api.mine.message.MessageDetailAPI;
import com.puyue.www.qiaoge.api.mine.message.MessageListAPI;
import com.puyue.www.qiaoge.api.mine.message.UpdateMessageStateAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.MessageDetailModel;
import com.puyue.www.qiaoge.model.mine.UpdateMessageStateModel;
import com.puyue.www.qiaoge.model.mine.MessageListModel;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 首页消息中心
 * Created by Administrator on 2018/11/14.
 */

public class MessageCenterActivity extends BaseSwipeActivity {

    private static final String TAG=MessageCenterActivity.class.getSimpleName();

    private ImageView mIvBack;
    private PtrClassicFrameLayout mPtr;
    private RecyclerView mRv;

    private List<MessageListModel.DataBean.ListBean> mListData = new ArrayList<>();
    private MessageCenterAdapter mAdapterMessageCenter;
    private MessageListModel mModelMessageList;
    private ImageView mIvNoData;

    private int pageNum = 1;

    public  int NewPosition=0;//mRv的item被点击过的数量

    public  int isToUesrNum=0;

    private UpdateMessageStateModel mModelUpdateMessageState;
    private MessageDetailModel mModelMessageDetail;

    //在这个地方拿到数据，并设置返回码把NP传过去
    //

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_message_center);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_message_center_back);
        mPtr = (PtrClassicFrameLayout) findViewById(R.id.ptr_message_center);
        mRv = (RecyclerView) findViewById(R.id.rv_message_center);
        mIvNoData = (ImageView) findViewById(R.id.iv_message_no_data);
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
                pageNum = 1;
                requestMessageList();
            }
        });

        mAdapterMessageCenter = new MessageCenterAdapter(R.layout.item_message_center, mListData);

        getItemStavedNum();


        mAdapterMessageCenter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                //请求接口切换信息状态
                if (mListData.get(position).looked_status == 0) {
                    //此条消息未读才能去修改消息状态
                    //先调详情接口告知后台,然后再调接口修改消息状态
                    requestMessageDetail(mListData.get(position).id);
                }

                if (mListData.get(position).openPage == 0) {
                    //不用跳转其他界面.直接跳转详情页
                    startActivity(MessageDetailActivity.getIntent(mContext, MessageDetailActivity.class, mListData.get(position).id));
                } else if (mListData.get(position).openPage == 1) {
                    if (mListData.get(position).toPage == 0) {
                        //跳转新品列表
                        Intent intent = new Intent(mContext, HomeGoodsListActivity.class);
                        intent.putExtra(AppConstant.PAGETYPE, AppConstant.NEWTYPE);
                        startActivity(intent);
                    } else if (mListData.get(position).toPage == 1) {
                        //跳转秒杀列表
                        Intent intent = new Intent(mContext, HomeGoodsListActivity.class);
                        intent.putExtra(AppConstant.PAGETYPE, AppConstant.SECONDTYPE);
                        startActivity(intent);
                    } else if (mListData.get(position).toPage == 2) {
                        //跳转团购列表
                        Intent intent = new Intent(mContext, HomeGoodsListActivity.class);
                        intent.putExtra(AppConstant.PAGETYPE, AppConstant.GROUPTYPE);
                        startActivity(intent);
                    }
//                    finish();
                }

            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
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
        mRv.setAdapter(mAdapterMessageCenter);
//        requestMessageList();
    }

    //得到这个状态数
    private void getItemStavedNum() {
        isToUesrNum=0;
        for (int i = 0; i < mListData.size() ; i++) {
            if (mListData.get(i).looked_status == 1){//1代表被看过
//                        NewPosition--;
                isToUesrNum++;
                Log.e(TAG, "onItemClick: 2号： "+isToUesrNum+NewPosition);
            }else {
                Log.e(TAG, "onItemClick: 2号： "+mListData.size()+NewPosition );
            }
        }

    }

    private void requestMessageDetail(final int mId) {
        MessageDetailAPI.requestMessageDetail(mContext, mId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MessageDetailModel messageDetailModel) {
                        logoutAndToHome(mContext, messageDetailModel.code);
                        mModelMessageDetail = messageDetailModel;
                        if (mModelMessageDetail.success) {
                            requestUpdateMessageState(mId);
                        } else {
                            AppHelper.showMsg(mContext, mModelMessageDetail.message);
                        }
                    }
                });
    }

    private void requestUpdateMessageState(int id) {
        UpdateMessageStateAPI.requestUpdateMessageState(mContext, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateMessageStateModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateMessageStateModel updateMessageStateModel) {
                        mModelUpdateMessageState = updateMessageStateModel;
                        if (mModelUpdateMessageState.success) {

                        } else {
                            AppHelper.showMsg(mContext, mModelUpdateMessageState.message);
                        }
                    }
                });
    }

    private void requestMessageList() {
        MessageListAPI.requestMessageList(mContext, pageNum, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MessageListModel messageListModel) {
                        mPtr.refreshComplete();
                        logoutAndToHome(mContext, messageListModel.code);
                        mModelMessageList = messageListModel;
                        if (mModelMessageList.success) {
                            updateMessageList();
                        } else {
                           AppHelper.showMsg(mContext, mModelMessageList.message);
                        }
                    }
                });
    }

    private void updateMessageList() {
        if (pageNum == 1) {
            if (mModelMessageList.data.list != null && mModelMessageList.data.list.size() > 0) {
                //有数据
                mIvNoData.setVisibility(View.GONE);
                mRv.setVisibility(View.VISIBLE);
                mListData.clear();
                mListData.addAll(mModelMessageList.data.list);
                NewPosition=mListData.size();
                //得到状态数
                getItemStavedNum();
                mAdapterMessageCenter.notifyDataSetChanged();
            } else {
                //没数据
                mIvNoData.setVisibility(View.VISIBLE);
                mRv.setVisibility(View.GONE);
            }
        } else {
            mListData.addAll(mModelMessageList.data.list);
            NewPosition=mListData.size();
            ////得到状态数
            getItemStavedNum();
            mAdapterMessageCenter.notifyDataSetChanged();
            mAdapterMessageCenter.loadMoreComplete();
        }
    }

    /**
     * 这个设置好返回数据即可
     */
    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                Intent intent = new Intent();//跳回首页
                intent.putExtra("NewPosition",NewPosition-isToUesrNum);
                setResult(102,intent);
                Log.e(TAG, "onNoDoubleClick: "+(NewPosition-isToUesrNum) );
                finish();

            }
        });
        mAdapterMessageCenter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                requestMoreMessage();
            }
        }, mRv);
    }

    private void requestMoreMessage() {
        if (mModelMessageList.data.hasNextPage) {
            //还有下一页数据
            pageNum++;
            requestMessageList();
        } else {
            //没有下一页数据了
            mAdapterMessageCenter.loadMoreEnd();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestMessageList();

    }


}
