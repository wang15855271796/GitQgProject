package com.puyue.www.qiaoge.fragment.market;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.api.market.MarketRightModel;

import java.util.List;

class RvDetailAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.ProdClassifyBean.ListBean,BaseViewHolder> {
    public RvDetailAdapter(int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean item) {

    }

//    private ImageView iv_head;
//    private FlowLayout fl_container;
//    private TextView tv_stock;
//    public RvDetailAdapter(int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> data) {
//        super(layoutResId, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean item) {
//        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
//        fl_container = helper.getView(R.id.fl_container);
//        fl_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int productId = item.getProdSpecs().get(position).getProductId();
//
//                GetProductDetailAPI.getExchangeList(mContext,productId)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<ExchangeProductModel>() {
//
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(ExchangeProductModel exchangeProductModel) {
//                                SearchInnersAdapter itemChooseAdapter = new SearchInnersAdapter(productId,R.layout.item_choose_content,exchangeProductModel.getData().getProdPrices()
//                                        , new SearchInnersAdapter.OnAddCarListener() {
//
//                                    @Override
//                                    public void addCar(int pos, int num, int id, int businessId) {
//                                        if(exchangeProductModel.getData().getInventory()!="0") {
//                                            addCart(pos,num,id,businessId);
//                                        }
//                                    }
//                                });
//
//                                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//                                recyclerView.setAdapter(itemChooseAdapter);
//                            }
//                        });
//            }
//        });
//
//        SearchClassifySpecAdapter searchSpecAdapter = new SearchClassifySpecAdapter(mContext,item.getProdSpecs());
//        fl_container.setAdapter(searchSpecAdapter);
//
//        helper.setText(R.id.tv_name,item.getProductName());
//        helper.setText(R.id.tv_stock_total,item.getInventory());
//        helper.setText(R.id.tv_sale,item.getSalesVolume());
//        helper.setText(R.id.tv_price,item.getMinMaxPrice());
//        helper.setText(R.id.tv_desc,item.getSpecialOffer());
//        tv_stock = helper.getView(R.id.tv_stock);
//        tv_stock.setText(item.getInventory());
//        int productId = item.getProdSpecs().get(0).getProductId();
//        SearchClassifyInnerAdapter searchInnerAdapter = new SearchClassifyInnerAdapter(productId,R.layout.item_choose_content,item.getProdPrices(), new SearchClassifyInnerAdapter.OnAddCarListener() {
//            @Override
//            public void addCar(int pos, int num, int id, int businessId) {
//                if(item.getInventory()!="0") {
//                    addCart(pos,num,id,businessId);
//                }
//
//            }
//        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        recyclerView.setAdapter(searchInnerAdapter);
//
//        ExpandLayout expandLayout = helper.getView(R.id.expanded);
//        expandLayout.initExpand(false);
//        TextView tv_choose_spec = helper.getView(R.id.tv_choose_spec);
//        tv_choose_spec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandLayout.toggleExpand();
//
//            }
//        });
//
//
//        iv_head = helper.getView(R.id.iv_head);
//        Glide.with(mContext).load(item.getDefaultPic()).into(iv_head);
//    }
//
//    /**
//     * 添加购物车
//     * @param
//     * @param pos
//     * @param num
//     * @param
//     */
//    private void addCart(int pos, int num, int id, int businessId) {
//        JSONArray array = new JSONArray();
//        try {
//            JSONObject object = new JSONObject();
//            object.put("productCombinationPriceId",id);
//            object.put("totalNum", num);
//            array.put(object);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String productCombinationPriceVOList = array.toString();
//
//        String totalNum = "";
//        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//            // 特别推荐 先判断是零售用户还是批发用户  在判断是否是批发商品
//            // 批发的商品是零售用户的话没有授权需要弹授权码的弹窗 成为批发用户直接添加购物车
//            // 批发用户购买零售直接添加购物车
//            if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
//                //这个用户是零售用户
////                if ("批发".equals(listBean.type)) {
////                    if (StringHelper.notEmptyAndNull(cell)) {
////                        AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode())) {
////                                    AppHelper.hideAuthorizationDialog();
////                                    showDialog();
////                                } else {
////                                    AppHelper.showMsg(mContext, "请输入完整授权码");
////                                }
////                            }
////                        });
////                    }
////                } else {
//                addCarResult(businessId, productCombinationPriceVOList, 1, totalNum);
//
////                }
//            } else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
//                //这个用户是批发用户
//                addCarResult(businessId, productCombinationPriceVOList, 1, totalNum);
//
//            }
//        } else {
//            AppHelper.showMsg(mContext, "请先登录");
//            mContext.startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
//        }
//    }
//
//
//    private void addCarResult(int businessId, String productCombinationPriceVOList, int businessType, String totalNum) {
//        AddCartAPI.requestData(mContext, businessId, productCombinationPriceVOList, businessType, String.valueOf(totalNum))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<AddCartModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(AddCartModel addCartModel) {
//                        if (addCartModel.success) {
//                            AppHelper.showMsg(mContext, "成功加入购物车");
//
//                        } else {
//                            AppHelper.showMsg(mContext, addCartModel.message);
//                        }
//
//                    }
//                });
//    }
//
//    public interface OnEventClickListener {
//        void onEventClick(View view, String direction, int typeId, int amount, int position, String type);
//    }

}
