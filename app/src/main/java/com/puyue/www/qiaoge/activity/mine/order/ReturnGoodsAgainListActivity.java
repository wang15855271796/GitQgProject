package com.puyue.www.qiaoge.activity.mine.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.ReturnGoodsAgainListAdapter;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnRequestModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ReturnGoodsAgainListActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private TextView mTvAllSelect;
    private RecyclerView mRvReturnGoods;
    private Button mBtnNext;

    private List<GetOrderDetailModel.DataBean.ProductVOListBean> mListForReturn;
    private ReturnGoodsAgainListAdapter mAdapterReturnGoods;
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
        mTvAllSelect.setVisibility(View.GONE);
        //分开用户是零售还是批发的,零售用户可以选量退货,批发用户只能直接退货
        if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
            //这个用户是零售用户
            mUserType = "1";
        } else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
            //这个用户是批发用户
            mUserType = "2";
        }
        if (mListForReturn != null && mListForReturn.size() > 0) {
            mListReturnGet.clear();
            for (int i = 0; i < mListForReturn.size(); i++) {
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
        mAdapterReturnGoods = new ReturnGoodsAgainListAdapter(R.layout.item_return_goods, mListForReturn, mUserType);
        mAdapterReturnGoods.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AppHelper.showMsg(mContext, "再次申请退货时,不可调整数量");
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
        mBtnNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //再次退货的情况下,直接将所有获取到的数据上传,不能选择
                mListReturnForRequest.clear();
                mListReturnForRequest.addAll(mListReturnGet);
                requestReturnGoods();
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
