package com.puyue.www.qiaoge.adapter.cart;

/**
 * Created by ${王涛} on 2019/10/7
 */
//public class CartsAdapter extends BaseQuickAdapter<CartsListModel.DataBean.ListBean.ValidListBean, BaseViewHolder> {
//
//    private ImageView iv;
//    private RecyclerView recyclerView;
//    Context context;
////    CartPriceAdapter itemChooseAdapter;
//
//    public CartsAdapter(Context context,int layoutResId, @Nullable List<CartsListModel.DataBean.ListBean.ValidListBean> data) {
//        super(layoutResId, data);
//        this.context = context;
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, CartsListModel.DataBean.ListBean.ValidListBean item) {
//        iv = helper.getView(R.id.iv_head);
//        recyclerView = helper.getView(R.id.recyclerView);
//        Glide.with(mContext).load(item.getPicUrl()).into(iv);
//        helper.setText(R.id.tv_title,item.getName());
//        //内部的列表
////        cartPriceAdapter = new ItemChooseAdapter(item.getProductDescVOList());
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
////        recyclerView.setAdapter(itemChooseAdapter);
//
//    }
//}
