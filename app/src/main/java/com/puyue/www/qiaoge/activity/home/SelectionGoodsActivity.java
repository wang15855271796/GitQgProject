package com.puyue.www.qiaoge.activity.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.adapter.home.SelectionGoodsAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.api.market.MarketGoodsAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DividerItemDecoration;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
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

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by ${daff}
 * on 2018/10/24
 * 备注 精选分类
 */
public class SelectionGoodsActivity extends BaseSwipeActivity {
    private TextView textViewTitle;
    private ImageView ivBack;
    private int pageNum = 1;
    private int productId;
    private SelectionGoodsAdapter adapter;
    private List<MarketGoodsModel.DataBean.ListBean> list = new ArrayList<>();
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecyclerView recyclerView;
    private MarketGoodsModel mModelMarketGoods;
    private LinearLayout linearLayoutData;
    private String title;
    private String cell; // 客服电话
    private List<GetRegisterShopModel.DataBean> list1 = new ArrayList<>();
    int isSelected;
    int shopTypeId;
    boolean isChecked = false;
    RegisterShopAdapterTwo mRegisterAdapter;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_selectgoods);
    }

    @Override
    public void findViewById() {
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        ivBack = (ImageView) findViewById(R.id.iv_return_reason_back);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.ptr_activity_goods_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutData = (LinearLayout) findViewById(R.id.linearLayoutData);

    }

    @Override
    public void setViewData() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    ptrClassicFrameLayout.setEnabled(false);
                } else {
                    ptrClassicFrameLayout.setEnabled(true);
                }
            }
        });
        productId = getIntent().getIntExtra("productId",0);
        title = getIntent().getStringExtra("title");
        textViewTitle.setText(title);

        adapter = new SelectionGoodsAdapter(R.layout.item_getproduct_adaptertwo, list, new SelectionGoodsAdapter.onClick() {
            @Override
            public void shoppingCartOnclick(int position) {
                setRecommendOnclick(position);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        requestMarketGoods(productId);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                requestMarketGoods(productId);
            }
        }, recyclerView);
    }

    @Override
    public void setClickEvent() {
        ivBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });

        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                requestMarketGoods(productId);
            }
        });
    }
    private void showDialog() {

        GetRegisterShopAPI.requestData(mActivity, AppHelper.getAuthorizationCode())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetRegisterShopModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ccca", "onError: " + "网络错误");
                    }

                    @Override
                    public void onNext(GetRegisterShopModel getRegisterShopModel) {

                        if (getRegisterShopModel.isSuccess()) {
                            list1.clear();
                            list1.addAll(getRegisterShopModel.getData());
                            //    mRegisterAdapter.notifyDataSetChanged();
                            AlertDialog alertDialog = new AlertDialog.Builder(mActivity).create();
                            alertDialog.show();
                            Window window = alertDialog.getWindow();
                            window.setContentView(R.layout.dialog_auth_shop_type);
                            window.setGravity(Gravity.CENTER);
                            RecyclerView rl_shop_type = window.findViewById(R.id.rl_shop_type);
                            TextView tv_dialog_cancel = window.findViewById(R.id.tv_dialog_cancel);
                            TextView tv_dialog_sure = window.findViewById(R.id.tv_dialog_sure);
                            LinearLayoutManager linearLayoutManager = new GridLayoutManager(mActivity, 3);
                            rl_shop_type.setLayoutManager(linearLayoutManager);
                            mRegisterAdapter = new RegisterShopAdapterTwo(mActivity, list1);
                            rl_shop_type.setAdapter(mRegisterAdapter);

                            mRegisterAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    isSelected = position;
                                    shopTypeId = list1.get(isSelected).getId();
                                    isChecked = true;
                                }

                                @Override
                                public void onItemLongClick(View view, int position) {

                                }
                            });
                            tv_dialog_sure.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (isChecked) {
                                        alertDialog.dismiss();
                                        updateUserInvitation(AppHelper.getAuthorizationCode(), shopTypeId);
                                    }


                                    else {
                                        AppHelper.showMsg(mActivity, "请选择店铺类型");
                                    }}
                            });

                            tv_dialog_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                    AppHelper.setAuthorizationCode("");
                                }
                            });
                        } else {
                            AppHelper.setAuthorizationCode("");
                            AppHelper.showMsg(mActivity, getRegisterShopModel.getMessage());
                        }
                    }
                });

    }

    /**
     * 提交验证码
     */
    private void updateUserInvitation(String code,int shopTypeId) {
        UpdateUserInvitationAPI.requestData(mActivity, code,shopTypeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateUserInvitationModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateUserInvitationModel updateUserInvitationModel) {
                        if (updateUserInvitationModel.isSuccess()) {
                            UserInfoHelper.saveUserType(mActivity, AppConstant.USER_TYPE_WHOLESALE);

                            pageNum = 1;
                            requestMarketGoods(productId);

                        } else {
                            AppHelper.showMsg(mActivity, updateUserInvitationModel.getMessage());
                        }
                    }
                });
    }



    /**
     * 特别推荐购物车的点击事件
     */
    private void setRecommendOnclick(int position) {
        MarketGoodsModel.DataBean.ListBean listBean= list.get(position);
        JSONArray array = new JSONArray();
        try {
            JSONObject object = new JSONObject();
            object.put("productCombinationPriceId", list.get(position).productCombinationPriceId);
            object.put("totalNum", "1");
            array.put(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String productCombinationPriceVOList = array.toString();

        String totalNum = "";
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
            // 特别推荐 先判断是零售用户还是批发用户  在判断是否是批发商品
            // 批发的商品是零售用户的话没有授权需要弹授权码的弹窗 成为批发用户直接添加购物车
            // 批发用户购买零售直接添加购物车
            if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_RETAIL)) {
                //这个用户是零售用户
                if ("批发".equals(listBean.type)) {
                    if (StringHelper.notEmptyAndNull(cell)) {
                        AppHelper.showAuthorizationDialog(mActivity, cell, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) ) {
                                    AppHelper.hideAuthorizationDialog();
                                  showDialog();
                                } else {
                                    AppHelper.showMsg(mActivity, "请输入完整授权码");
                                }
                            }
                        });
                    }
                } else {
                    addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);

                }
            } else if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_WHOLESALE)) {
                //这个用户是批发用户

                addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);

            }
        } else {
            AppHelper.showMsg(mActivity, "请先登录");
            startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
        }
    }
    /**
     * 加入购物车
     */
    private void addCar(int businessId, String productCombinationPriceVOList, int businessType, String totalNum) {
        AddCartAPI.requestData(mActivity, businessId, productCombinationPriceVOList, businessType, String.valueOf(totalNum))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddCartModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddCartModel addCartModel) {
                        if (addCartModel.success) {
                            AppHelper.showMsg(mActivity, "成功加入购物车");

                        } else {
                            AppHelper.showMsg(mActivity, addCartModel.message);
                        }

                    }
                });
    }

    private void requestMarketGoods(int productId) {
        MarketGoodsAPI.requestSelectionGoods(mContext, pageNum, 9, productId, -1, "","","","","","")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MarketGoodsModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(MarketGoodsModel marketGoodsMode) {
                        ptrClassicFrameLayout.refreshComplete();
                        mModelMarketGoods = marketGoodsMode;
                        if (marketGoodsMode.success) {
                            updateMarketGoods();

                        } else {
                            AppHelper.showMsg(getContext(), marketGoodsMode.message);
                        }
                    }
                });
    }

    private void updateMarketGoods() {
        if (pageNum == 1) {
            list.clear();
            if (mModelMarketGoods.data.list != null && mModelMarketGoods.data.list.size() > 0) {
                ptrClassicFrameLayout.setVisibility(View.VISIBLE);
                linearLayoutData.setVisibility(View.GONE);

                list.addAll(mModelMarketGoods.data.list);
                adapter.notifyDataSetChanged();
            } else {
                ptrClassicFrameLayout.setVisibility(View.GONE);
                linearLayoutData.setVisibility(View.VISIBLE);
            }
        } else {
            list.addAll(mModelMarketGoods.data.list);
            adapter.notifyDataSetChanged();
            adapter.loadMoreComplete();
        }
        if (mModelMarketGoods.data.hasNextPage) {
            //还有下一页数据
            adapter.loadMoreComplete();

        } else {

            adapter.loadMoreEnd(false);
        }
    }

}
