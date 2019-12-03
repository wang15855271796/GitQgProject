package com.puyue.www.qiaoge.activity.mine.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.ReturnGoodsListAdapter;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnRequestModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/4/16.
 */

public class ReturnGoodsListActivity extends BaseSwipeActivity {

    private ImageView mIvBack;
    private TextView mTvAllSelect;
    private RecyclerView mRvReturnGoods;
    private Button mBtnNext;

    private List<GetOrderDetailModel.DataBean.ProductVOListBean> mListForReturn;
    private Map<Integer, Boolean> isCheck = new HashMap<>();
    private ReturnGoodsListAdapter mAdapterReturnGoods;
    private boolean isAllSelected = false;
    private String orderId;
    private int orderType;
    private String returnProductMainId;
    private String mUserType;
    private List<ReturnRequestModel> mListReturnGet = new ArrayList<>();
    private List<ReturnRequestModel> mListReturnForRequest = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        mListForReturn = (List<GetOrderDetailModel.DataBean.ProductVOListBean>) getIntent().getSerializableExtra("goodsList");
        orderId = getIntent().getStringExtra("orderId");
        orderType = getIntent().getIntExtra("orderType", -1);
        returnProductMainId = getIntent().getStringExtra("returnProductMainId");
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_return_goods);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_return_goods_back);
        mTvAllSelect = (TextView) findViewById(R.id.tv_return_goods_all_select);
        mRvReturnGoods = (RecyclerView) findViewById(R.id.rv_return_goods);
        mBtnNext = (Button) findViewById(R.id.btn_return_goods_next);
    }

    @Override
    public void setViewData() {
        //分开用户是零售还是批发的,零售用户可以选量退货,批发用户只能直接退货
        if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
            //这个用户是零售用户
            mUserType = "1";
        } else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
            //这个用户是批发用户
            mUserType = "2";
        }
        if (mListForReturn != null && mListForReturn.size() > 0) {
            isCheck.clear();
            mListReturnGet.clear();
            for (int i = 0; i < mListForReturn.size(); i++) {
                isCheck.put(i, false);
                //我们拿到上个界面传来的退货商品列表,需组合成自己的model类型,根据自己的model数据类型来上传
                List<Integer> mListProductCombinationPriceId = new ArrayList<>();
                List<Integer> mListReturnNum = new ArrayList<>();
                List<String> mListReturnPrice = new ArrayList<>();
                mListProductCombinationPriceId.clear();
                mListReturnNum.clear();
                mListReturnPrice.clear();
                List<GetOrderDetailModel.DataBean.ProductVOListBean.ProductDescVOListBean> productDescVOList = mListForReturn.get(i).productDescVOList;
                for (int i1 = 0; i1 < productDescVOList.size(); i1++) {
                    mListProductCombinationPriceId.add(productDescVOList.get(i1).productCombinationPriceId);
                    mListReturnNum.add(productDescVOList.get(i1).productNum);
                    mListReturnPrice.add(productDescVOList.get(i1).price);
                }
                mListReturnGet.add(new ReturnRequestModel(mListForReturn.get(i).businessType, mListForReturn.get(i).productId, mListProductCombinationPriceId, mListReturnNum, mListReturnPrice));
            }
        }
        mAdapterReturnGoods = new ReturnGoodsListAdapter(R.layout.item_return_goods, mListForReturn, isCheck, mUserType);
        mAdapterReturnGoods.setOnItemClickListener(new ReturnGoodsListAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(View view, int position, String flag, int standardPosition, int amount) {
                if (flag.equals("check")) {
                    //当前item被点击,把这个item的选择状态改为选中
                    if (isCheck.get(position)) {
                        //如果取消，则设置map集合中为false
                        isCheck.put(position, false);
                    } else {
                        //如果选中，则设置map集合中为true
                        isCheck.put(position, true);
                    }
                    mAdapterReturnGoods.notifyDataSetChanged();
                } else if (flag.equals("reduce")) {
                    //减少某个商品的某个规格的退货数量
                    //position代表着是修改的哪个商品,standardPosition是代表着修改了这个商品的第几个规格数量
                    GetOrderDetailModel.DataBean.ProductVOListBean productVOListBean = mListForReturn.get(position);//这是修改的那个商品
                    GetOrderDetailModel.DataBean.ProductVOListBean.ProductDescVOListBean productDescVOListBean = productVOListBean.productDescVOList.get(standardPosition);//这是修改的那个规格
                    productDescVOListBean.productNum = productDescVOListBean.productNum - 1;//这里修改数量是为了界面显示
                    mAdapterReturnGoods.notifyDataSetChanged();
                    //之后修改我们自己model里面的数量才是要上传的
                    mListReturnGet.get(position).returnNumList.set(standardPosition, amount);
                } else if (flag.equals("add")) {
                    //增加某个商品的某个规格的退货数量
                    GetOrderDetailModel.DataBean.ProductVOListBean productVOListBean = mListForReturn.get(position);//这是修改的那个商品
                    GetOrderDetailModel.DataBean.ProductVOListBean.ProductDescVOListBean productDescVOListBean = productVOListBean.productDescVOList.get(standardPosition);//这是修改的那个规格
                    productDescVOListBean.productNum = productDescVOListBean.productNum + 1;//这里修改数量是为了界面显示
                    mAdapterReturnGoods.notifyDataSetChanged();
                    mListReturnGet.get(position).returnNumList.set(standardPosition, amount);
                }
            }

            @Override
            public void onEventLongClick(View view, int position, String flag) {

            }
        });
        mRvReturnGoods.setLayoutManager(new LinearLayoutManager(mContext));
        mRvReturnGoods.setAdapter(mAdapterReturnGoods);
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        mTvAllSelect.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (isAllSelected) {
                    //正在被全选
                    for (int i = 0; i < isCheck.size(); i++) {
                        isCheck.put(i, false);
                    }
                    mAdapterReturnGoods.notifyDataSetChanged();
                    isAllSelected = false;
                } else {
                    //没有全选中
                    for (int i = 0; i < isCheck.size(); i++) {
                        isCheck.put(i, true);
                    }
                    mAdapterReturnGoods.notifyDataSetChanged();
                    isAllSelected = true;
                }
            }
        });
        mBtnNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //将选择中的商品的id传给后台,开始退货流程
                //获取到列表中哪几个被选中了,要获取到这个商品的id,和这个商品的退货数量
                mListReturnForRequest.clear();
                int selectedGoods = 0;
                for (int i = 0; i < isCheck.size(); i++) {
                    if (isCheck.get(i)) {
                        //这个item是被选中的,才上传
                        selectedGoods++;
                        mListReturnForRequest.add(mListReturnGet.get(i));
                    }
                }
                if (selectedGoods == 0) {
                    //没有商品被选中
                    AppHelper.showMsg(mContext, "暂无商品被选中");
                } else {
                    requestReturnGoods();
                }
            }
        });
    }

    private void requestReturnGoods() {
        Intent intent = new Intent(mContext, ReturnReasonActivity.class);
        String value = mListReturnForRequest.toString();
        intent.putExtra("listString", value);
        intent.putExtra("orderId", orderId);
        intent.putExtra("orderType", orderType);
        intent.putExtra("returnProductMainId", returnProductMainId);
        startActivity(intent);
        finish();
    }

}
