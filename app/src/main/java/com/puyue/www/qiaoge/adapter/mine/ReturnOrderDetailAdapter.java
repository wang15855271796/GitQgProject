package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;
import com.puyue.www.qiaoge.pictureselectordemo.GridImageAdapter;
import com.puyue.www.qiaoge.view.SwipeRecycler;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/20
 */
public class ReturnOrderDetailAdapter extends RecyclerView.Adapter<ReturnOrderDetailAdapter.ReturnViewHolder> {
    private Context context;

    private List<ReturnOrderDetailModel.DataBean.ProductsBean> mList;
    private SwipeRecycler recyclerView;

    public ReturnOrderDetailAdapter(Context context, List<ReturnOrderDetailModel.DataBean.ProductsBean> list, SwipeRecycler recyclerView) {
        this.context = context;
        this.mList = list;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ReturnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.return_good_detail_new, recyclerView, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = recyclerView.getScreenWidth() + recyclerView.dp2px(120);
        view.setLayoutParams(layoutParams);

        View main = view.findViewById(R.id.rl_good);
        ViewGroup.LayoutParams mainLayoutParams = main.getLayoutParams();
        mainLayoutParams.width = recyclerView.getScreenWidth();
        main.setLayoutParams(mainLayoutParams);

        return new ReturnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnViewHolder holder, int position) {

        //请注意，为了有良好的体验，请记得每次点击事件生效时，关闭菜单
        //recycler.closeEx();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.closeEx();
                //  Snackbar.make(MainActivity.this.findViewById(R.id.root), position + "", Snackbar.LENGTH_SHORT).show();
            }
        });


        holder.tvProductName.setText(mList.get(position).getProductName());

        Glide.with(context).load(mList.get(position).getFlagUrl()).into(holder.ivFlag);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ReturnViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProduct;
        private ImageView ivFlag;
        private TextView tvProductName;


        public ReturnViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.iv_good_item);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            ivFlag = itemView.findViewById(R.id.iv_flag);

        }
    }


}
