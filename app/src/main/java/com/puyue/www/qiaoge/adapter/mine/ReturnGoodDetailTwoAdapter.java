package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;
import com.puyue.www.qiaoge.view.EasySwipeMenuLayout;
import com.puyue.www.qiaoge.view.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${王文博} on 2019/5/23
 */
public class ReturnGoodDetailTwoAdapter extends RecyclerView.Adapter<ReturnGoodDetailTwoAdapter.ReturnOrderViewHolder> {
    private List<ReturnOrderDetailModel.DataBean.ProductsBean> mListProduct;
    private Context context;
    //侧滑菜单里面的
    ReturnNumAdapter mReturnNumAdapter;
    private int selectPosition;


    private String orderId;
    private int businessId;
    private int businessType;
    public OnReturnClickListener listener;

    public OnReturnClickListener getListener() {
        return listener;
    }

    public void setListener(OnReturnClickListener listener) {
        this.listener = listener;
    }

    public interface OnReturnClickListener {
        void onClick();

    }

    public ReturnGoodDetailTwoAdapter(List<ReturnOrderDetailModel.DataBean.ProductsBean> mListProduct, Context context) {
        this.mListProduct = mListProduct;
        this.context = context;
        for (int i = 0; i < mListProduct.size(); i++) {
            for (int j = 0; j < mListProduct.get(i).getDetails().size(); j++) {
                mListProduct.get(i).getDetails().get(j).setBusinessId(mListProduct.get(i).getBusinessId());
                mListProduct.get(i).getDetails().get(j).setBusinessType(mListProduct.get(i).getBusinessType());

            }
        }
    }

    @NonNull
    @Override
    public ReturnOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.return_good_detail_new, null);
        return new ReturnOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnOrderViewHolder holder, int position) {

        double totalPrice = 0.00;

        //必须局部变量，不然editview使用的时候会复用
        //规格
        List<ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean> mListSpec = new ArrayList<>();
        ReturnSpecAdapter mSpecAdaPter;
        List<ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean> mListReturnNum = new ArrayList<>();
        selectPosition = position;
        holder.tvProductName.setText(mListProduct.get(position).getProductName());
        Glide.with(context).load(mListProduct.get(position).getFlagUrl()).into(holder.ivFlag);
        Glide.with(context).load(mListProduct.get(position).getDefaultPic()).into(holder.ivProduct);
        //侧滑菜单
        //数量

        mListReturnNum.clear();
        mListReturnNum.addAll(mListProduct.get(position).getDetails());
        mReturnNumAdapter = new ReturnNumAdapter(R.layout.return_order_num, mListReturnNum,mListProduct.get(position).additionFlag);
        mReturnNumAdapter.setListener(new ReturnNumAdapter.OnReturnClickListener() {
            @Override
            public void onEventClick() {
                listener.onClick();
            }
        });

        holder.mRyNum.setLayoutManager(new GridLayoutManager(context, 1));


        holder.mRyNum.setAdapter(mReturnNumAdapter);
        mReturnNumAdapter.notifyDataSetChanged();

        //规格
        mListSpec.clear();

        mListSpec.addAll(mListProduct.get(position).getDetails());
        holder.mRySpec.setLayoutManager(new LinearLayoutManager(context));
        mSpecAdaPter = new ReturnSpecAdapter(R.layout.retrun_order_spec, mListSpec);
        orderId = UserInfoHelper.getOrderId(context);

        businessId = mListProduct.get(position).getBusinessId();
        businessType = mListProduct.get(position).getBusinessType();

        final boolean[] isSelect = {true};
        mSpecAdaPter.setClick(new ReturnSpecAdapter.OnItemClick() {
            @Override
            public void delete() {
                EasySwipeMenuLayout easySwipeMenuLayout = holder.itemView.findViewById(R.id.es);
                if (isSelect[0]) {
                    easySwipeMenuLayout.handlerSwipeMenu(State.RIGHTOPEN);
                    isSelect[0] = false;
                } else {
                    easySwipeMenuLayout.handlerSwipeMenu(State.CLOSE);
                    isSelect[0] = true;
                }


            }
        });

        holder.mRySpec.setAdapter(mSpecAdaPter);


    }

    @Override
    public int getItemCount() {
        return mListProduct.size();
    }

    public class ReturnOrderViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProduct;
        private ImageView ivFlag;
        private TextView tvProductName;
        //规格
        private RecyclerView mRySpec;
        private RecyclerView mRyNum;

        public ReturnOrderViewHolder(View itemView) {
            super(itemView);


            ivProduct = itemView.findViewById(R.id.iv_good_item);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            ivFlag = itemView.findViewById(R.id.iv_flag);
            mRySpec = itemView.findViewById(R.id.ry_spc);

            mRyNum = itemView.findViewById(R.id.ry_return_num);
        }
    }
}




