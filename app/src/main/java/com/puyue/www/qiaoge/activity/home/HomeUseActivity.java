package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.market.MarketFirstAdapter;
import com.puyue.www.qiaoge.adapter.market.MarketGoodsAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.api.market.MarketGoodsAPI;
import com.puyue.www.qiaoge.api.market.MarketGoodsClassifyAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.TwoDeviceHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.model.market.MarketClassifyModel;
import com.puyue.www.qiaoge.model.market.MarketGoodsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/9.
 */

public class HomeUseActivity extends BaseSwipeActivity {
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {

    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }
//
//    private Toolbar mToolbar;
//    private TextView mTvSearch;
//    private RecyclerView mRvFirst;
//    private RecyclerView mRvSecond;
//    private PtrClassicFrameLayout mPtrDetail;
//    private RecyclerView mRvDetail;
//    private ImageView mIvNoData;
//
//    private int mFirstClassifyNum;
//    private int mSecondClassifyNum;
//    private int mFirstCode;
//    private int mSecondCode;
//    private int pageNum = 1;//切换一级分类和二级分类的时候都要将这个pageNum置为1
//    private LoadingDailog dialog;
//
//    private MarketFirstAdapter mAdapterMarketFirst;
////    private MarketSecondAdapter mAdapterMarketSecond;
//    private MarketGoodsAdapter mAdapterMarketDetail;
//    private MarketClassifyModel mModelMarketGoodsClassify;
//    private MarketGoodsModel mModelMarketGoods;
//
//    private List<MarketClassifyModel.DataBean> mList = new ArrayList<>();
//    private List<MarketClassifyModel.DataBean.SecondClassifyListBean> mListSecondNow = new ArrayList<>();
//    private List<MarketGoodsModel.DataBean.ListBean> mListGoods = new ArrayList<>();
//    private String cell; // 客服电话
//    @Override
//    public boolean handleExtra(Bundle savedInstanceState) {
//        return false;
//    }
//
//    @Override
//    public void setContentView() {
//        setContentView(R.layout.activity_home_use);
//    }
//
//    @Override
//    public void findViewById() {
//        mToolbar = (Toolbar) findViewById(R.id.toolbar_home_use);
//        mTvSearch = (TextView) findViewById(R.id.tv_home_use_search);
//        mRvFirst = (RecyclerView) findViewById(R.id.rv_home_use_first);
//        mRvSecond = (RecyclerView) findViewById(R.id.rv_home_use_second);
//        mPtrDetail = (PtrClassicFrameLayout) findViewById(R.id.ptr_home_use_detail);
//        mRvDetail = (RecyclerView) findViewById(R.id.rv_home_use_detail);
//        mIvNoData = (ImageView) findViewById(R.id.iv_home_use_no_data);
//    }
//
//    @Override
//    public void setViewData() {
//        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
//                .setMessage("获取数据中")
//                .setCancelable(false)
//                .setCancelOutside(false);
//        dialog = loadBuilder.create();
//        mPtrDetail.setPtrHandler(new PtrHandler() {
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                pageNum = 1;
//                requestMarketGoods(mFirstCode, mSecondCode);
//            }
//        });
//        mRvFirst.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
//        mRvSecond.setLayoutManager(new LinearLayoutManager(mContext));
//        mRvDetail.setLayoutManager(new LinearLayoutManager(mContext));
//        mRvDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (recyclerView.canScrollVertically(-1)) {
//                    mPtrDetail.setEnabled(false);
//                } else {
//                    mPtrDetail.setEnabled(true);
//                }
//            }
//        });
//        mAdapterMarketFirst = new MarketFirstAdapter(mContext, mList);
//        mAdapterMarketFirst.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                //点击一级分类的时候,不止是将样式变换过来,还要将二级分类的数据刷新,并且二级分类第一个为被选中状态,三级详情列表显示第一个分组
//                mFirstClassifyNum = position;
//                mAdapterMarketFirst.selectPosition(mFirstClassifyNum);
//                mListSecondNow.clear();
//                mListSecondNow.addAll(mList.get(mFirstClassifyNum).secondClassifyList);
////                mAdapterMarketSecond.notifyDataSetChanged();
////                mAdapterMarketSecond.selectPosition(0);
//                //这时候是点击了一级分类,,默认这个一级分类的二级分类选中第一个"全部商品"
//                //这个时候只需要传一级分类的id,二级分类给空即可
//                mFirstCode = mList.get(mFirstClassifyNum).firstClassifyId;
////                mSecondCode = mList.get(mFirstClassifyNum).secondClassifyList.get(0).secondClassifyId;
//                mSecondCode = -1;
//                //确定一个新的id,需要将pageNum置为1
////                mPtrDetail.autoRefresh();
//                dialog.show();
//                pageNum = 1;
//                requestMarketGoods(mFirstCode, mSecondCode);
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
//        mAdapterMarketSecond = new MarketSecondAdapter(mListSecondNow);
//        mAdapterMarketSecond.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                //在点击二级列表的时候,需要将样式修改过来,然后刷新三级详情列表数据
//                mSecondClassifyNum = position;
////                mAdapterMarketSecond.selectPosition(position);
//                //这时候是确定一级分类,去点击别的二级分类的时候
//                //取得二级分类的id,上传
//                if (mSecondClassifyNum != 0) {
//                    mSecondCode = mList.get(mFirstClassifyNum).secondClassifyList.get(mSecondClassifyNum).secondClassifyId;
//                } else if (mSecondClassifyNum == 0) {
//                    mSecondCode = -1;
//                }
////                mPtrDetail.autoRefresh();
//                dialog.show();
//                pageNum = 1;
//                requestMarketGoods(mFirstCode, mSecondCode);
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
//        mAdapterMarketDetail = new MarketGoodsAdapter(R.layout.item_market_goods, mListGoods, new MarketGoodsAdapter.Onclick() {
//            @Override
//            public void addCar(int pos) {
//                setRecommendOnclick(pos);
//                mAdapterMarketDetail.notifyDataSetChanged();
//            }
//
//            @Override
//            public void priceList(int pos) {
//
//            }
//        });
//        mAdapterMarketDetail.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
//                intent.putExtra(AppConstant.ACTIVEID, mListGoods.get(position).productId);
//                startActivity(intent);
//            }
//        });
//        mAdapterMarketDetail.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                pageNum++;
//                requestMarketGoods(mFirstCode, mSecondCode);
//            }
//        }, mRvDetail);
//        mRvFirst.setAdapter(mAdapterMarketFirst);
////        mRvSecond.setAdapter(mAdapterMarketSecond);
//        mRvDetail.setAdapter(mAdapterMarketDetail);
//        dialog.show();
//        requestGoodsList();
//        getCustomerPhone();
//    }
//
//    private void requestGoodsList() {
//        MarketGoodsClassifyAPI.requestMarketGoods(mContext, AppConstant.RETAIL)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MarketClassifyModel>() {
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
//                    public void onNext(MarketClassifyModel marketGoodsModel) {
//                        AppHelper.UserLogout(mContext, marketGoodsModel.code, 0);
//                        mModelMarketGoodsClassify = marketGoodsModel;
//                        if (mModelMarketGoodsClassify.success) {
//                            updateGoodsList();
//                        } else {
//                            if (marketGoodsModel.code == AppConstant.ANOTHER_PLACE_LOGIN) {
//                                TwoDeviceHelper.logoutAndToHome(mActivity);
//                            } else {
//                                AppHelper.showMsg(mActivity, mModelMarketGoodsClassify.message);
//                            }
//
//                        }
//                    }
//                });
//    }
//
//    private void updateGoodsList() {
//        mList.clear();
//        mList.addAll(mModelMarketGoodsClassify.data);
//        mAdapterMarketFirst.notifyDataSetChanged();
//        mListSecondNow.clear();
//        mListSecondNow.addAll(mModelMarketGoodsClassify.data.get(0).secondClassifyList);
////        mAdapterMarketSecond.notifyDataSetChanged();
//        //默认两个分级列表第一个都是选中状态
//        mAdapterMarketFirst.selectPosition(0);
////        mAdapterMarketSecond.selectPosition(0);
//        //获取到第一分类和对应的第二分类,然后请求接口
//        //默认显示第一个一级分类的第一个二级分类的数据
//        //默认的第一个一级分类都是"全部商品"
//        mFirstCode = mList.get(0).firstClassifyId;
//        mSecondCode = -1;
//        requestMarketGoods(mFirstCode, mSecondCode);
//    }
//
//    private void requestMarketGoods(int firstClassifyId, int secondClassifyId) {
//        MarketGoodsAPI.requestMarketGoods(mContext, pageNum, 10, firstClassifyId, secondClassifyId, AppConstant.RETAIL)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MarketGoodsModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
////                        Toast.makeText(mActivity, "错误");
//                    }
//
//                    @Override
//                    public void onNext(MarketGoodsModel marketGoodsModel) {
//                        dialog.dismiss();
//                        mPtrDetail.refreshComplete();
//                        mModelMarketGoods = marketGoodsModel;
//                        if (mModelMarketGoods.success) {
//                            updateMarketGoods();
//                        } else {
//                           AppHelper.showMsg(mContext, mModelMarketGoods.message);
//                        }
//                    }
//                });
//    }
//
//    private void updateMarketGoods() {
//        if (pageNum == 1) {
//            if (mModelMarketGoods.data.list != null && mModelMarketGoods.data.list.size() > 0) {
//                mRvDetail.setVisibility(View.VISIBLE);
//                mIvNoData.setVisibility(View.GONE);
//                mListGoods.clear();
//                mListGoods.addAll(mModelMarketGoods.data.list);
//                mAdapterMarketDetail.notifyDataSetChanged();
//            } else {
//                mRvDetail.setVisibility(View.GONE);
//                mIvNoData.setVisibility(View.VISIBLE);
//            }
//        } else {
//            mListGoods.addAll(mModelMarketGoods.data.list);
//            mAdapterMarketDetail.notifyDataSetChanged();
//            mAdapterMarketDetail.loadMoreComplete();
//        }
//        if (mModelMarketGoods.data.hasNextPage) {
//            //还有下一页数据
//            mAdapterMarketDetail.loadMoreComplete();
//        } else {
//            mAdapterMarketDetail.loadMoreEnd();
//        }
//    }
//
//    @Override
//    public void setClickEvent() {
//        mToolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View view) {
//                finish();
//            }
//        });
//        mTvSearch.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View view) {
//                Intent intent = new Intent(mContext, SearchStartActivity.class);
//                intent.putExtra(AppConstant.SEARCHTYPE, AppConstant.HOME_SEARCH);
//                intent.putExtra("flag","first");
//                startActivity(intent);
//            }
//        });
//    }
//
//    // 添加购物车
//    private void setRecommendOnclick(int position) {
//        MarketGoodsModel.DataBean.ListBean listBean = mListGoods.get(position);
//        JSONArray array = new JSONArray();
//        try {
//            JSONObject object = new JSONObject();
//            object.put("productCombinationPriceId", mListGoods.get(position).productCombinationPriceId);
//            object.put("totalNum", "1");
//            array.put(object);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String productCombinationPriceVOList = array.toString();
//
//        String totalNum = "";
//        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
//            // 特别推荐 先判断是零售用户还是批发用户  在判断是否是批发商品
//            // 批发的商品是零售用户的话没有授权需要弹授权码的弹窗 成为批发用户直接添加购物车
//            // 批发用户购买零售直接添加购物车
//            if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_RETAIL)) {
//                //这个用户是零售用户
//                if ("批发".equals(listBean.type)) {
//                    if (StringHelper.notEmptyAndNull(cell)) {
//                        AppHelper.showAuthorizationDialog(mActivity, cell, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
//                                    AppHelper.hideAuthorizationDialog();
//                                    updateUserInvitation(AppHelper.getAuthorizationCode());
//                                } else {
//                                    AppHelper.showMsg(mActivity, "请输入完整授权码");
//                                }
//                            }
//                        });
//                    }
//                } else {
//                    addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);
//
//                }
//            } else if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_WHOLESALE)) {
//                //这个用户是批发用户
//
//                addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);
//
//            }
//        } else {
//            AppHelper.showMsg(mActivity, "请先登录");
//            startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
//        }
//    }
//
//
//    /**
//     * 加入购物车
//     */
//    private void addCar(int businessId, String productCombinationPriceVOList, int businessType, String totalNum) {
//        AddCartAPI.requestData(mActivity, businessId, productCombinationPriceVOList, businessType, String.valueOf(totalNum))
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
//                            AppHelper.showMsg(mActivity, "成功加入购物车");
//
//                        } else {
//                            AppHelper.showMsg(mActivity, addCartModel.message);
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * 获取客服电话
//     */
//    private void getCustomerPhone() {
//        PublicRequestHelper.getCustomerPhone(mActivity, new OnHttpCallBack<GetCustomerPhoneModel>() {
//            @Override
//            public void onSuccessful(GetCustomerPhoneModel getCustomerPhoneModel) {
//                if (getCustomerPhoneModel.isSuccess()) {
//                    cell = getCustomerPhoneModel.getData();
//                } else {
//                    AppHelper.showMsg(mActivity, getCustomerPhoneModel.getMessage());
//                }
//            }
//
//            @Override
//            public void onFaild(String errorMsg) {
//            }
//        });
//    }
//
//    /**
//     * 提交验证码
//     */
//    private void updateUserInvitation(String code) {
//        UpdateUserInvitationAPI.requestData(mActivity, code,1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<UpdateUserInvitationModel>() {
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
//                    public void onNext(UpdateUserInvitationModel updateUserInvitationModel) {
//                        if (updateUserInvitationModel.isSuccess()) {
//                            UserInfoHelper.saveUserType(mActivity, AppConstant.USER_TYPE_WHOLESALE);
//                            dialog.show();
//                            requestMarketGoods(mFirstCode, mSecondCode);
//                        } else {
//                            AppHelper.showMsg(mActivity, updateUserInvitationModel.getMessage());
//                        }
//                    }
//                });
//    }
}
