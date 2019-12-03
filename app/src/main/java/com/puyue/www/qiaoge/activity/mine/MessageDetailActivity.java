package com.puyue.www.qiaoge.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.message.MessageDetailAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.MessageDetailModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MessageDetailActivity extends BaseSwipeActivity {
    public static final String ID = "id";

    private int mId;

    private MessageDetailModel mModelMessageDetail;
    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView mTvTime;
    private TextView mTvContent;

    public static Intent getIntent(Context context, Class<?> cls, int id) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtra(ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        mId = getIntent().getIntExtra(ID, 0);
        if (savedInstanceState != null) {
            mId = savedInstanceState.getInt(ID);
        }
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_message_detail);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_message_detail_back);
        mTvTitle = (TextView) findViewById(R.id.tv_message_detail_title);
        mTvTime = (TextView) findViewById(R.id.tv_message_detail_time);
        mTvContent = (TextView) findViewById(R.id.tv_message_detail);
    }

    @Override
    public void setViewData() {
        requestMessageDetail();
    }

    private void requestMessageDetail() {
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
                            updateText();
                        } else {
                            AppHelper.showMsg(mContext, mModelMessageDetail.message);
                        }
                    }
                });
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
    }

    private void updateText() {
        mTvTitle.setText(mModelMessageDetail.data.title);
        mTvTime.setText(mModelMessageDetail.data.gmtCreateTime);
        mTvContent.setText(mModelMessageDetail.data.content);
    }
}
