package com.puyue.www.qiaoge.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.ReasonAdapter;
import com.puyue.www.qiaoge.api.home.CancleAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.CancleModel;
import com.puyue.www.qiaoge.model.CancleReasonModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/6/4
 */
public class ReasonActivity extends BaseSwipeActivity {
    @BindView(R.id.tv_commit)
    TextView tv_commit;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    private List<CancleReasonModel.DataBean> data;
    private ReasonAdapter reasonAdapter;
    List<CancleReasonModel.DataBean> list = new ArrayList<>();
    private String datas;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_reason);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas = edit.getText().toString().trim();
                String data = SharedPreferencesUtil.getString(mActivity,"data");

                if(datas!=null) {
                    getIsCancle(datas);

                }

                if(data!=null) {
                    getIsCancle(data);
                }

            }
        });


        reasonAdapter = new ReasonAdapter(R.layout.item_reason,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(reasonAdapter);

        reasonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SharedPreferencesUtil.saveString(mActivity,"data",list.get(position).getCode());
                reasonAdapter.selectionPosition(position);
                reasonAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getIsCancle(String cancleReason) {
        CancleAPI.requestData(mContext,cancleReason)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CancleModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(CancleModel cancleModel) {
                        if (cancleModel.isSuccess()) {
                            if(cancleModel.getData()!=null) {
                                CancleModel.DataBean data = cancleModel.getData();
                                double balance = data.getBalance();
                                if(data.getWeekOrderNum()==0&&data.getNoOrderNum()==0&&data.getSubUserNum()==0&&balance==0.0) {
                                    Intent intent = new Intent(mActivity,DealsActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("data",data);
                                    intent.putExtras(bundle);
                                    intent.putExtra("reason",cancleReason);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mActivity,DealsActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("data",data);
                                    intent.putExtras(bundle);
                                    intent.putExtra("reason",cancleReason);
                                    startActivity(intent);
                                }
                            }
                        } else {
                            AppHelper.showMsg(mContext, cancleModel.getMessage());
                        }
                    }
                });
    }

    @Override
    public void setViewData() {
        getReasonList();
    }

    private void getReasonList() {
        CancleAPI.getList(mContext,"APP_USER_CANCEL")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CancleReasonModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(CancleReasonModel cancleReasonModel) {
                        if (cancleReasonModel.isSuccess()) {
                            if(cancleReasonModel.getData()!=null) {
                                data = cancleReasonModel.getData();
                                list.addAll(data);
                                reasonAdapter.notifyDataSetChanged();
                            }
                        } else {
                            AppHelper.showMsg(mContext, cancleReasonModel.getMessage());
                        }
                    }
                });
    }

    @Override
    public void setClickEvent() {

    }
}
