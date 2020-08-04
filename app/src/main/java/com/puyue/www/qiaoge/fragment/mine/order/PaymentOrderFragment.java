package com.puyue.www.qiaoge.fragment.mine.order;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.MyConfireOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.order.NewOrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.OrderDetailActivity;
import com.puyue.www.qiaoge.adapter.mine.MyOrdersItemAdapter;
import com.puyue.www.qiaoge.adapter.mine.NewOrderDetailAdapter;
import com.puyue.www.qiaoge.api.cart.CancelOrderAPI;
import com.puyue.www.qiaoge.api.cart.DeleteOrderAPI;
import com.puyue.www.qiaoge.api.home.GetOrderDetailAPI;
import com.puyue.www.qiaoge.api.mine.order.MyOrderListAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.cart.CancelOrderModel;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;

/**
 * Created by Administrator on 2018/4/21.
 */
//待付款订单
public class PaymentOrderFragment extends BaseFragment {
    private PtrClassicFrameLayout mPtr;
    private RecyclerView mRv;
    private MyOrdersItemAdapter mAdapterMyOrders;
    private String mType;
    private ImageView mIvNoData;
    private int pageNum = 1;
    private MyOrdersModel mModelMyOrders;

    private NewOrderDetailAdapter adapter;
    private GetOrderDetailModel.DataBean getOrderDetailModel;
    private List<MyOrdersModel.DataBean.ListBean> mListResult = new ArrayList<>();
    private String returnProductMainId = "";
    private String orderId;
    private String orderState = "";
    private int orderStatusRequest;

    private List<GetOrderDetailModel.DataBean.ProductVOListBean> list = new ArrayList<>();


    private int orderDeliveryType;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_my_orders;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void findViewById(View view) {
        mPtr = ((PtrClassicFrameLayout) view.findViewById(R.id.ptr_my_orders));
        mRv = ((RecyclerView) view.findViewById(R.id.rv_my_orders));
        mIvNoData = ((ImageView) view.findViewById(R.id.iv_my_orders_no_data));
    }

    @Override
    public void setViewData() {
        mListResult.clear();
        if (UserInfoHelper.getDeliverType(mActivity) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getDeliverType(mActivity))) {
            orderDeliveryType = Integer.parseInt(UserInfoHelper.getDeliverType(mActivity));
        }
        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                requestOrdersList(1);
            }
        });
        if (orderDeliveryType==0){
            mAdapterMyOrders = new MyOrdersItemAdapter(R.layout.item_my_order, mListResult, 1,orderDeliveryType, new MyOrdersItemAdapter.OnClick() {

                @Override
                public void evaluateNowOnclick(int position) {

                }

                @Override
                public void againBayOnclick(int position) {

                }

                @Override
                public void cancelOnclick(String orderId) {
                    final AlertDialog mDialog = new AlertDialog.Builder(getContext()).create();
                    mDialog.show();
                    mDialog.getWindow().setContentView(R.layout.dailog_cancel);
                    TextView mBtnCancel = (TextView) mDialog.getWindow().findViewById(R.id.btnCancel);
                    TextView mBtnOK = (TextView) mDialog.getWindow().findViewById(R.id.btnOK);

                    mBtnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog.dismiss();
                        }
                    });


                    mBtnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog.dismiss();
                            //取消订单的接口
                            cancelOrder(orderId);
                            //   mPtr.refreshComplete();
                            //   mAdapterMyOrders.notifyDataSetChanged();
                        }
                    });
                }

                @Override
                public void deleteOnclick(String orderId) {
               /* final AlertDialog mDialog = new AlertDialog.Builder(getContext()).create();
                mDialog.show();
                mDialog.getWindow().setContentView(R.layout.dailog_cancel);
                Button mBtnCancel = (Button) mDialog.getWindow().findViewById(R.id.btnCancel);
                Button mBtnOK = (Button) mDialog.getWindow().findViewById(R.id.btnOK);

                mBtnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });


                mBtnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        //取消订单的接口
                       deleteOrder(orderId);
                        //   mPtr.refreshComplete();
                        //   mAdapterMyOrders.notifyDataSetChanged();
                    }
                });*/


                }

                @Override
                public void imageGo(String orderId, String payAmount) {
                    Intent intent = new Intent(getActivity(), MyConfireOrdersActivity.class);
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("remark", "");

                    intent.putExtra("payAmount", Double.parseDouble(payAmount));
                    intent.putExtra("flag", true);

                    startActivity(intent);
                }

                @Override
                public void requestConfirmGetGoods(String orderId) {

                }

                @Override
                public void confirmSelfOrder(String orderId) {

                }

                @Override
                public void confirmSelfReturnOrder(String orderId, int pos) {

                }


            });
        }else if (orderDeliveryType==1){
            mAdapterMyOrders = new MyOrdersItemAdapter(R.layout.item_my_order_self, mListResult, 1,orderDeliveryType, new MyOrdersItemAdapter.OnClick() {

                @Override
                public void evaluateNowOnclick(int position) {

                }

                @Override
                public void againBayOnclick(int position) {

                }

                @Override
                public void cancelOnclick(String orderId) {
                    final AlertDialog mDialog = new AlertDialog.Builder(getContext()).create();
                    mDialog.show();
                    mDialog.getWindow().setContentView(R.layout.dailog_cancel);
                    TextView mBtnCancel = (TextView) mDialog.getWindow().findViewById(R.id.btnCancel);
                    TextView mBtnOK = (TextView) mDialog.getWindow().findViewById(R.id.btnOK);

                    mBtnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog.dismiss();
                        }
                    });


                    mBtnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog.dismiss();
                            //取消订单的接口
                            cancelOrder(orderId);
                            //   mPtr.refreshComplete();
                            //   mAdapterMyOrders.notifyDataSetChanged();
                        }
                    });
                }

                @Override
                public void deleteOnclick(String orderId) {
               /* final AlertDialog mDialog = new AlertDialog.Builder(getContext()).create();
                mDialog.show();
                mDialog.getWindow().setContentView(R.layout.dailog_cancel);
                Button mBtnCancel = (Button) mDialog.getWindow().findViewById(R.id.btnCancel);
                Button mBtnOK = (Button) mDialog.getWindow().findViewById(R.id.btnOK);

                mBtnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });


                mBtnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        //取消订单的接口
                       deleteOrder(orderId);
                        //   mPtr.refreshComplete();
                        //   mAdapterMyOrders.notifyDataSetChanged();
                    }
                });*/


                }

                @Override
                public void imageGo(String orderId, String payAmount) {
                    Intent intent = new Intent(getActivity(), MyConfireOrdersActivity.class);
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("remark", "");

                    intent.putExtra("payAmount", Double.parseDouble(payAmount));
                    intent.putExtra("flag", true);

                    startActivity(intent);
                }

                @Override
                public void requestConfirmGetGoods(String orderId) {

                }

                @Override
                public void confirmSelfOrder(String orderId) {

                }

                @Override
                public void confirmSelfReturnOrder(String orderId, int pos) {

                }


            });
        }

       /* mAdapterMyOrders = new MyOrdersItemAdapter(R.layout.item_my_order, mListResult, 1, new MyOrdersItemAdapter.OnClick() {

            @Override
            public void evaluateNowOnclick(int position) {

            }

            @Override
            public void againBayOnclick(int position) {

            }

            @Override
            public void cancelOnclick(String orderId) {
                final AlertDialog mDialog = new AlertDialog.Builder(getContext()).create();
                mDialog.show();
                mDialog.getWindow().setContentView(R.layout.dailog_cancel);
                TextView mBtnCancel = (TextView) mDialog.getWindow().findViewById(R.id.btnCancel);
                TextView mBtnOK = (TextView) mDialog.getWindow().findViewById(R.id.btnOK);

                mBtnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });


                mBtnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        //取消订单的接口
                        cancelOrder(orderId);
                        //   mPtr.refreshComplete();
                        //   mAdapterMyOrders.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void deleteOnclick(String orderId) {
               *//* final AlertDialog mDialog = new AlertDialog.Builder(getContext()).create();
                mDialog.show();
                mDialog.getWindow().setContentView(R.layout.dailog_cancel);
                Button mBtnCancel = (Button) mDialog.getWindow().findViewById(R.id.btnCancel);
                Button mBtnOK = (Button) mDialog.getWindow().findViewById(R.id.btnOK);

                mBtnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });


                mBtnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        //取消订单的接口
                       deleteOrder(orderId);
                        //   mPtr.refreshComplete();
                        //   mAdapterMyOrders.notifyDataSetChanged();
                    }
                });*//*


            }

            @Override
            public void imageGo(String orderId, String payAmount) {
                Intent intent = new Intent(getActivity(), MyConfireOrdersActivity.class);
                intent.putExtra("orderId", orderId);
                intent.putExtra("remark", "");

                intent.putExtra("payAmount", Double.parseDouble(payAmount));
                intent.putExtra("flag", true);

                startActivity(intent);
            }

            @Override
            public void requestConfirmGetGoods(String orderId) {

            }


        });*/


        mAdapterMyOrders.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到订单详情页去
               /* Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                intent.putExtra(AppConstant.ORDERID, mListResult.get(position).orderId);
                intent.putExtra(AppConstant.ORDERSTATE, "");
                intent.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                startActivity(intent);*/
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    mPtr.setEnabled(false);
                } else {
                    mPtr.setEnabled(true);
                }
            }
        });
        mRv.setAdapter(mAdapterMyOrders);
        mAdapterMyOrders.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                requestOrdersList(1);
            }
        });
//        requestOrdersList(1);
    }

    private void requestOrdersList(int orderStatus) {
        MyOrderListAPI.requestOrderList(getContext(), orderStatus, pageNum, 10, orderDeliveryType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyOrdersModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyOrdersModel myOrdersModel) {
                        mPtr.refreshComplete();
                        logoutAndToHome(getContext(), myOrdersModel.code);
                        mModelMyOrders = myOrdersModel;
                        if (mModelMyOrders.success) {
                            updateOrderList();
                        } else {
                            AppHelper.showMsg(getContext(), mModelMyOrders.message);
                        }
                    }
                });
    }

    private void updateOrderList() {
        if (pageNum == 1) {
            if (mModelMyOrders.data != null && mModelMyOrders.data.list.size() > 0) {
                mIvNoData.setVisibility(View.GONE);
                mRv.setVisibility(View.VISIBLE);
                mListResult.clear();
                mListResult.addAll(mModelMyOrders.data.list);
                mAdapterMyOrders.notifyDataSetChanged();
            } else {
                mIvNoData.setVisibility(View.VISIBLE);
                mRv.setVisibility(View.GONE);
            }
        } else {
            //加载更多数据
            mListResult.addAll(mModelMyOrders.data.list);
            mAdapterMyOrders.notifyDataSetChanged();
        }
        if (mModelMyOrders.data.hasNextPage) {
            //有下一页数据
            mAdapterMyOrders.loadMoreComplete();
        } else {
            //没有下一页数据了
            mAdapterMyOrders.loadMoreEnd();
        }
    }

    //删除订单
    private void deleteOrder(String orderId) {
        DeleteOrderAPI.requestData(getContext(), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CancelOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CancelOrderModel cancelOrderModel) {
                        if (cancelOrderModel.success) {
                            //取消成功
                            AppHelper.showMsg(mContext, "删除订单成功");
                            // getOrderDetail(orderId, orderState, returnProductMainId);
                        } else {
                            AppHelper.showMsg(mContext, cancelOrderModel.message);
                        }
                    }
                });
    }

    //取消订单
    private void cancelOrder(String orderId) {
        CancelOrderAPI.requestData(getContext(), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CancelOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CancelOrderModel cancelOrderModel) {
                        if (cancelOrderModel.success) {
                            //取消成功
                            AppHelper.showMsg(mContext, "取消订单成功");
                            // mPtr.autoRefresh();
                            mPtr.autoRefresh();
                            requestOrdersList(1);

                            // getOrderDetail(orderId, orderState, returnProductMainId);
                        } else {
                            AppHelper.showMsg(mContext, cancelOrderModel.message);
                        }
                    }

                });
    }


    @Override
    public void setClickEvent() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {

            mPtr.autoRefresh();
            pageNum = 1;
            requestOrdersList(1);
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((isVisibleToUser && isResumed())) {
            onResume();
        } else if (!isVisibleToUser) {
            onPause();
        }
    }
}
