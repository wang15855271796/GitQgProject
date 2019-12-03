package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.ReturnGoodActivity;
import com.puyue.www.qiaoge.event.ImageAdapterEvent;
import com.puyue.www.qiaoge.model.mine.order.OrderEvaluateListModel;
import com.puyue.www.qiaoge.model.mine.order.OrderEvaluateModel;
import com.puyue.www.qiaoge.pictureselectordemo.FullyGridLayoutManager;
import com.puyue.www.qiaoge.pictureselectordemo.GridImageAdapter;
import com.puyue.www.qiaoge.view.StarBarView;
import com.puyue.www.qiaoge.view.flowtaglayout.FlowTagLayout;
import com.puyue.www.qiaoge.view.flowtaglayout.MemberEntityImpl;
import com.puyue.www.qiaoge.view.flowtaglayout.OnTagSelectListener;
import com.puyue.www.qiaoge.view.flowtaglayout.TagAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */


public class OrderEvaluateAdapter extends BaseQuickAdapter<OrderEvaluateListModel, BaseViewHolder> implements TagAdapter.OnclickListener {

    private SaveEditListener onClick;

    private ArrayList<MemberEntityImpl> list;

    private Context context;


    private OnStatusListener onStatusListener;

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnStatusListener {
        void onSetStatusListener(int pos);

        void onDeleteListener(int pos, int tagPos);

        void onSetListener(int pos);

    }

    public interface SaveEditListener {
        void saveEdit(int position, String string);


    }


    public interface ShowPop {

    }

    public OrderEvaluateAdapter(Context context, int layoutResId, @Nullable List<OrderEvaluateListModel> data, ArrayList<MemberEntityImpl> list, SaveEditListener onClick) {
        super(layoutResId, data);
        this.onClick = onClick;

        this.context = context;
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final OrderEvaluateListModel item) {
    ImageView iv;
        StarBarView sbv_star_bar;
        TextView tv_status;
        ImageView iv_add;
        RecyclerView mRecyclerView;
        FlowTagLayout flComment;

        helper.setText(R.id.tv_item_order_evaluate_name, item.name);
        iv = helper.getView(R.id.iv_good);
        sbv_star_bar = helper.getView(R.id.sbv_star_bar);
        tv_status = helper.getView(R.id.tv_status);
        iv_add = helper.getView(R.id.iv_add);
        mRecyclerView = helper.getView(R.id.recycler);
        flComment = helper.getView(R.id.fl_comment);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);


        flComment.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);

        TagAdapter<LocalMedia> mSizeTagAdapter1 = new TagAdapter<>(mContext);
        mSizeTagAdapter1.setOnStatusClickListener( this);

        flComment.setAdapter(mSizeTagAdapter1);
        mSizeTagAdapter1.onlyAddAll(list.get(helper.getLayoutPosition()).getCommentImgs());
        flComment.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, View view, List<Integer> selectedList) {
                switch (view.getId()) {
                    case R.id.ll_del:
                        onStatusListener.onDeleteListener(helper.getLayoutPosition(), selectedList.get(0));
                        break;
                    case R.id.iv_comment_image:
                        if (list.get(helper.getLayoutPosition()).getCommentImgs().size() > 0) {
                            if (list.get(helper.getLayoutPosition()).getCommentImgs().size() == selectedList.get(0)) {
                                onStatusListener.onSetStatusListener(helper.getLayoutPosition());
                            } else {
                                onStatusListener.onSetListener(selectedList.get(0));
                            }
                        } else {
                            onStatusListener.onSetStatusListener(helper.getLayoutPosition());
                        }
                        break;
                }
            }
        });
        setStarName(tv_status, Float.parseFloat(item.getStar()));
        Glide.with(mContext).load(item.picUrl).into(iv);
        ((EditText) helper.getView(R.id.edit_item_order_evaluate)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // SaveEditListener listener = (SaveEditListener) mContext;
                if (s != null) {
                    onClick.saveEdit(helper.getAdapterPosition(), ((EditText) helper.getView(R.id.edit_item_order_evaluate)).getText().toString());
                }
            }
        });


        sbv_star_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setStarName(tv_status, sbv_star_bar.getStarRating());
                if (sbv_star_bar.getStarRating() == 5.0f) {
                    item.setStar(5 + "");
                } else if (sbv_star_bar.getStarRating() == 4.0f) {
                    item.setStar(4 + "");
                } else if (sbv_star_bar.getStarRating() == 3.0f) {
                    item.setStar(3 + "");
                } else if (sbv_star_bar.getStarRating() == 2.0f) {
                    item.setStar(2 + "");
                } else if (sbv_star_bar.getStarRating() == 1.0f) {
                    item.setStar(1 + "");
                }


            }
        });



    }

    /*

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


    @Override
    public void onSetClickListener(int pos) {

    }

    @Override
    public void onSetClickStatusListener(int pos) {

    }

    @Override
    public void onDeleteClickListener(int pos, int tagPos) {

    }

}


