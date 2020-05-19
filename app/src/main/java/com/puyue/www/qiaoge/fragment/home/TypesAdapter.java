package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SelectionGoodActivity;
import com.puyue.www.qiaoge.api.home.IndexInfoModel;
import com.puyue.www.qiaoge.helper.StringHelper;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ${王涛} on 2020/1/4
 */
public class TypesAdapter extends BaseMultiItemQuickAdapter<IndexInfoModel.DataBean.ClassifyListBean,BaseViewHolder> {

    List<IndexInfoModel.DataBean.ClassifyListBean> data;
    private double random;
    private int anInt;
    private CountDownTimer countDownTimer1;

    public TypesAdapter(List<IndexInfoModel.DataBean.ClassifyListBean> data) {
        super(data);
        this.data = data;
        addItemType(IndexInfoModel.DataBean.ClassifyListBean.SHORT, R.layout.item_list_type);
        addItemType(IndexInfoModel.DataBean.ClassifyListBean.LONG, R.layout.item_list_type1);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexInfoModel.DataBean.ClassifyListBean item) {

        RelativeLayout rl_bg = helper.getView(R.id.rl_bg);
        TextView tv_small_title = helper.getView(R.id.tv_small_title);
        TextView tv_title = helper.getView(R.id.tv_title);
        ImageView iv_pic = helper.getView(R.id.iv_pic);

        rl_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SelectionGoodActivity.class);
                intent.putExtra("productId",item.getId());
                intent.putExtra("title",item.getTitle());
                mContext.startActivity(intent);
            }
        });

        switch (helper.getItemViewType()) {
            case IndexInfoModel.DataBean.ClassifyListBean.SHORT:
                tv_small_title.setText(item.getSecTitle());
                tv_title.setText(item.getTitle());

                if(data.size()>0) {
                    if(helper.getLayoutPosition()==0) {
                        rl_bg.setBackgroundResource(R.drawable.shouye_orange_bg);

                        if (data.get(0).getProdPics().size()>0) {

                            Glide.with(mContext).load(item.getProdPics().get(0)).into(iv_pic);
                        }
                    }else if(helper.getLayoutPosition()==1) {

                        if (data.get(1).getProdPics().size()>0) {
                            Glide.with(mContext).load(item.getProdPics().get(0)).into(iv_pic);
                            if(countDownTimer1==null) {
                                countDownTimer1 = new CountDownTimer(5000,1000) {
                                    int i = 0;
                                    @Override
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        try {
                                            Glide.with(mContext).load(item.getProdPics().get(i)).into(iv_pic);
                                            i++;
                                            if(i==data.get(1).getProdPics().size()) {
                                                i = 0;
                                            }
                                        }catch (Exception e) {

                                        }
                                        start();
                                    }
                                }.start();
                            }

                        }

                        rl_bg.setBackgroundResource(R.drawable.shouye_hot_red_bg);
                    }else if(helper.getLayoutPosition()==2) {
                        if (data.get(2).getProdPics().size()>0) {
//                            new CountDownTimer(5000,1000) {
//                                int i = 0;
//                                @Override
//                                public void onTick(long millisUntilFinished) {
//
//                                }
//
//                                @Override
//                                public void onFinish() {
//                                    Glide.with(mContext).load(item.getProdPics().get(i)).into(iv_pic);
//                                    i++;
//                                    if(i==data.get(1).getProdPics().size()) {
//                                        i = 0;
//
//                                    }
//
//                                }
//                            }.start();
                        }
                        rl_bg.setBackgroundResource(R.drawable.shouye_pink_bg);
                    }else if(helper.getLayoutPosition()==3) {

                        rl_bg.setBackgroundResource(R.drawable.shouye_red_bg);
                        if (data.get(3).getProdPics().size()>0) {
//                            new CountDownTimer(5000,1000) {
//                                int i = 0;
//                                @Override
//                                public void onTick(long millisUntilFinished) {
//
//                                }
//
//                                @Override
//                                public void onFinish() {
//                                    Glide.with(mContext).load(item.getProdPics().get(i)).into(iv_pic);
//                                    i++;
//                                    if(i==data.get(1).getProdPics().size()) {
//                                        i = 0;
//                                    }
//
//                                }
//                            }.start();
                        }

                    }else if(helper.getLayoutPosition()==4) {
                        if (data.get(4).getProdPics().size()>0) {
//                            new CountDownTimer(5000,1000) {
//                                int i = 0;
//                                @Override
//                                public void onTick(long millisUntilFinished) {
//
//                                }
//
//                                @Override
//                                public void onFinish() {
//                                    Glide.with(mContext).load(item.getProdPics().get(i)).into(iv_pic);
//                                    i++;
//                                    if(i==data.get(1).getProdPics().size()) {
//                                        i = 0;
//                                    }
//
//                                }
//                            }.start();
                        }
                        rl_bg.setBackgroundResource(R.drawable.shouye_yellow_bg);
                    }else if(helper.getLayoutPosition()==5) {
                        if (data.get(5).getProdPics().size()>0) {
//                            new CountDownTimer(5000,1000) {
//                                int i = 0;
//                                @Override
//                                public void onTick(long millisUntilFinished) {
//
//                                }
//
//                                @Override
//                                public void onFinish() {
//                                    Glide.with(mContext).load(item.getProdPics().get(i)).into(iv_pic);
//                                    i++;
//                                    if(i==data.get(1).getProdPics().size()) {
//                                        i = 0;
//                                    }
//
//                                }
//                            }.start();
                        }
                        rl_bg.setBackgroundResource(R.drawable.shouye_purple_bg);
                }
             }
                break;
            case IndexInfoModel.DataBean.ClassifyListBean.LONG:
                ImageView iv_type1 = helper.getView(R.id.iv_type1);
                ImageView iv_type2 = helper.getView(R.id.iv_type2);
                ImageView iv_type3 = helper.getView(R.id.iv_type3);
                tv_small_title.setText(item.getSecTitle());
                tv_title.setText(item.getTitle());
                Glide.with(mContext).load(data.get(3).getProdPics().get(0)).into(iv_type1);
                break;

            default:
                break;
    }

    }

    public void cancle() {
        countDownTimer1.cancel();
    }
}
