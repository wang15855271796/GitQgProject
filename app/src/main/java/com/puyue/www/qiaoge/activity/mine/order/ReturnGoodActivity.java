package com.puyue.www.qiaoge.activity.mine.order;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.ReturnGoodDetailChangeAdapter;
import com.puyue.www.qiaoge.adapter.mine.ReturnGoodDetailTwoAdapter;
import com.puyue.www.qiaoge.adapter.mine.ReturnMoneyAdapter;
import com.puyue.www.qiaoge.adapter.mine.ReturnOrderAdapter;
import com.puyue.www.qiaoge.api.mine.order.ReturnOrderApi;
import com.puyue.www.qiaoge.api.mine.order.ReturnOrderDetailAPi;
import com.puyue.www.qiaoge.api.mine.order.ReturnSelectReasonAPI;
import com.puyue.www.qiaoge.api.mine.order.SendImageAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DividerItemDecoration;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderSucModel;
import com.puyue.www.qiaoge.model.mine.order.SendImageModel;
import com.puyue.www.qiaoge.model.mine.wallet.OrderReturnSelectReasonModel;
import com.puyue.www.qiaoge.pictureselectordemo.FullyGridLayoutManager;
import com.puyue.www.qiaoge.pictureselectordemo.GridImageAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ReturnGoodActivity extends BaseSwipeActivity {
    private String orderId;
    private String orderStatus;
    private TextView mTvSelectReason;
    private List<String> mReturnReason = new ArrayList<>();
    private RecyclerView mRyOrderDetail;
    private List<ReturnOrderDetailModel.DataBean.ProductsBean> mProductList = new ArrayList<>();
    private ReturnGoodDetailTwoAdapter mRvDetailAdapter;
    private ReturnGoodDetailChangeAdapter mRvDetailChangeAdapter;
    private ImageView ivBack;
    //退货理由
    private RecyclerView mRySeclectionReason;
    private ReturnOrderAdapter mReturnAdapter;
    private TextView tvCancel;
    private TextView tv_return_money;
    //照相机
    private int maxSelectNum = 3;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private RecyclerView mRecyclerView;
    private PopupWindow pop;
    private TextView tv_post_order;
    public Double offerAmount;
    private ReturnOrderDetailModel mDetailModel;
    private List<String> picList = new ArrayList();
    private EditText et_return_content;
    String returnPic = "";
    double totalPrice = 0.00;
    private boolean isChecked = true;
    private boolean icFirst = true;
    private int orderDeliveryType;
    private CheckBox rd_check;
    private TextView tv_return_way;
    private String channelValue;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_return_order);

    }

    @Override
    public void findViewById() {
        mTvSelectReason = findViewById(R.id.tv_select_reason);
        mRyOrderDetail = findViewById(R.id.recyclerview_return_good);
        mRecyclerView = findViewById(R.id.recycler);
        ivBack = findViewById(R.id.imageViewBreak);
        tv_post_order = findViewById(R.id.tv_post_order);
        tv_return_money = findViewById(R.id.tv_return_money);
        et_return_content = findViewById(R.id.et_return_content);
        rd_check = findViewById(R.id.rd_check);
        tv_return_way = findViewById(R.id.tv_return_way);

    }

    @Override
    public void setViewData() {
        orderId = getIntent().getStringExtra("orderId");
        UserInfoHelper.saveOrderId(mContext, orderId);
        orderStatus = getIntent().getStringExtra("orderStatus");
        orderDeliveryType = getIntent().getIntExtra("orderDeliveryType", 0);
        mRyOrderDetail.setHasFixedSize(true);
        mRyOrderDetail.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        getReturnOrderDetail(orderId);
        //照片
        initWidget();
        //防止复用刷新加载太频繁
        et_return_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                icFirst = true;
            }
        });
    }

    @Override
    public void setClickEvent() {
        mTvSelectReason.setOnClickListener(noDoubleClickListener);
        ivBack.setOnClickListener(noDoubleClickListener);
        tv_post_order.setOnClickListener(noDoubleClickListener);
        rd_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rd_check.isChecked()) {
                    icFirst = true;
                    mRyOrderDetail.setItemViewCacheSize(500);
                    mRvDetailChangeAdapter = new ReturnGoodDetailChangeAdapter(mProductList, mContext);
                    double total_Price = 0.00;
                    for (int i = 0; i < mDetailModel.getData().getProducts().size(); i++) {
                        for (int j = 0; j < mDetailModel.getData().getProducts().get(i).getDetails().size(); j++) {
                            if (new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getPrice()).multiply(new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getNum())).setScale(2).doubleValue() - new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getDeductPrice()).multiply(new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getNum())).setScale(2).doubleValue() > 0) {
                                total_Price += (new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getPrice()).multiply(new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getNum())).setScale(2).doubleValue() - new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getDeductPrice()).multiply(new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getNum())).setScale(2).doubleValue());
                                mDetailModel.getData().getProducts().get(i).getDetails().get(j).setItemPrice(new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getPrice()).multiply(new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getNum())).setScale(2).doubleValue() - new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getDeductPrice()).multiply(new BigDecimal(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getNum())).setScale(2).doubleValue());

                            } else {
                                total_Price += 0;
                                mDetailModel.getData().getProducts().get(i).getDetails().get(j).setItemPrice(0);

                            }
                            mDetailModel.getData().getProducts().get(i).getDetails().get(j).setItemNum(mDetailModel.getData().getProducts().get(i).getDetails().get(j).getNum());
                        }
                    }
                    BigDecimal bg = new BigDecimal(total_Price);
                    double f = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    tv_return_money.setText("￥" + f);
                    totalPrice = f;
                    mRvDetailChangeAdapter.setListener(new ReturnGoodDetailChangeAdapter.OnReturnClickListener() {
                        @Override
                        public void onClick() {
                            double toPrice = 0.00;
                            double fiPrice = 0.00;
                            for (int i = 0; i < mDetailModel.getData().getProducts().size(); i++) {
                                for (int j = 0; j < mDetailModel.getData().getProducts().get(i).getDetails().size(); j++) {
                                    toPrice += mDetailModel.getData().getProducts().get(i).getDetails().get(j).getItemPrice();
                                    if (mDetailModel.getData().getProducts().get(i).getDetails().get(j).getItemPrice() > 0) {

                                    } else {
                                        rd_check.setChecked(false);
                                        mDetailModel.getData().getProducts().get(i).getDetails().get(j).setItemNum(0);
                                        icFirst = true;
                                    }
                                }
                            }
                            BigDecimal bg = new BigDecimal(toPrice);
                            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            tv_return_money.setText("￥" + f1);
                            totalPrice = toPrice;
                        }
                    });

                    mRyOrderDetail.setAdapter(mRvDetailChangeAdapter);

                } else {

                    mRyOrderDetail.removeAllViews();
                    mRvDetailAdapter = new ReturnGoodDetailTwoAdapter(mProductList, mContext);

                    tv_return_money.setText(0 + "");
                    totalPrice = 0;
                    mRvDetailAdapter.setListener(new ReturnGoodDetailTwoAdapter.OnReturnClickListener() {
                        @Override
                        public void onClick() {
                            double toPrice = 0.00;
                            for (int i = 0; i < mDetailModel.getData().getProducts().size(); i++) {
                                for (int j = 0; j < mDetailModel.getData().getProducts().get(i).getDetails().size(); j++) {
                                    toPrice += mDetailModel.getData().getProducts().get(i).getDetails().get(j).getItemPrice();
                                }
                            }
                            BigDecimal bg = new BigDecimal(toPrice);
                            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            tv_return_money.setText("￥" + f1);
                            totalPrice = toPrice;
                        }
                    });

                    mRyOrderDetail.setAdapter(mRvDetailAdapter);
                    mRyOrderDetail.setItemViewCacheSize(500);
                }

            }
        });


        //退款去向
        tv_return_way.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDetailModel.getData().returnChannel.size() > 1) {
                    final Dialog dialog = new Dialog(mActivity, R.style.DialogTheme);
                    View view = View.inflate(mActivity, R.layout.deliver_time_popu, null);
                    RecyclerView mRyReturnMoney = view.findViewById(R.id.ry_select);
                    TextView tvCancel = view.findViewById(R.id.tv_cancel_reason);
                    tvCancel.setVisibility(View.GONE);
                    mRyReturnMoney.setLayoutManager(new LinearLayoutManager(mActivity));
                    ReturnMoneyAdapter adapter = new ReturnMoneyAdapter(R.layout.select_reason, mDetailModel.getData().returnChannel);
                    mRyReturnMoney.setAdapter(adapter);

                    //添加分隔线
                    DividerItemDecoration dividerPreKillDecoration = new DividerItemDecoration(mActivity,
                            DividerItemDecoration.VERTICAL_LIST);
                    dividerPreKillDecoration.setDivider(R.drawable.app_divider);
                    mRyReturnMoney.addItemDecoration(dividerPreKillDecoration);

                    dialog.setContentView(view);
                    dialog.setCanceledOnTouchOutside(true);
                    Window dialogWindow = dialog.getWindow();
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.BOTTOM;
                    dialogWindow.setAttributes(lp);

                    dialog.show();
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                            tv_return_way.setText(mDetailModel.getData().returnChannel.get(position).channelText);
                            channelValue = mDetailModel.getData().returnChannel.get(position).channelValue;
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    //获取退货订单信息
    private void getReturnOrderDetail(String orderId) {
        ReturnOrderDetailAPi.requestReturnOrder(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnOrderDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ReturnOrderDetailModel returnOrderDetailModel) {

                        if (returnOrderDetailModel.isSuccess()) {
                            if (returnOrderDetailModel.getData() != null) {
                                mDetailModel = returnOrderDetailModel;
                                offerAmount = Double.parseDouble(returnOrderDetailModel.getData().getOfferAmount());
                                mProductList.clear();
                                mProductList.addAll(returnOrderDetailModel.getData().getProducts());
                                tv_return_money.setText(0 + "");
                                tv_return_way.setText(returnOrderDetailModel.getData().returnChannel.get(0).channelText);
                                channelValue = returnOrderDetailModel.getData().returnChannel.get(0).channelValue;
                                getOrder(mProductList);

                            }

                        } else {
                            AppHelper.showMsg(mContext, returnOrderDetailModel.getMessage());
                        }
                    }
                });
    }


    private void getOrder(List<ReturnOrderDetailModel.DataBean.ProductsBean> list) {
        mRvDetailAdapter = new ReturnGoodDetailTwoAdapter(list, mContext);
        mRvDetailAdapter.setListener(new ReturnGoodDetailTwoAdapter.OnReturnClickListener() {
            @Override
            public void onClick() {
                double toPrice = 0.00;
                for (int i = 0; i < mDetailModel.getData().getProducts().size(); i++) {
                    for (int j = 0; j < mDetailModel.getData().getProducts().get(i).getDetails().size(); j++) {
                        toPrice += mDetailModel.getData().getProducts().get(i).getDetails().get(j).getItemPrice();
                    }
                }
                BigDecimal bg = new BigDecimal(toPrice);
                double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                tv_return_money.setText("￥" + f1);
                totalPrice = toPrice;

            }
        });
        mRyOrderDetail.setAdapter(mRvDetailAdapter);
        mRyOrderDetail.setItemViewCacheSize(500);

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.tv_select_reason:
                    getSelectReason(orderStatus);
                    break;
                case R.id.imageViewBreak:
                    finish();
                    break;

                case R.id.tv_post_order:
                    if (icFirst) {
                        if (mTvSelectReason.getText().toString() != null) {
//                                for (int i = 0; i < mProductList.size(); i++) {
//                                    for (int j = 0; j < mProductList.get(i).getDetails().size(); j++) {
//                                        returnNumTotal+=mProductList.get(i).getDetails().get(j).getItemNum();
//
//                                    }
//                                }
//                            if (returnNumTotal > 0) {
                                sendMgs();

                                // sendMgs();
//                            } else {
//                                AppHelper.showMsg(mContext, "请选择退货商品");
//                            }

//                        } else {
//                            AppHelper.showMsg(mContext, "请选择退货原因");
                        }
                    }
                    break;
            }
        }
    };

    public void upImage(List<MultipartBody.Part> parts) {

        SendImageAPI.requestImgDetail(mContext, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SendImageModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SendImageModel baseModel) {
                        if (baseModel.success) {
                            returnPic = "";
                            if (baseModel.data != null) {
                                for (int i = 0; i < baseModel.data.length; i++) {
                                    returnPic += baseModel.data[i] + ",";
                                }

                            }
                            Log.i("wwwwbbb", "onNext: " + returnPic);
                            //  sendMgs();
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });
    }

    private void getSelectReason(String orderStatus) {
        ReturnSelectReasonAPI.requestReturnOrderReason(mContext, orderStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderReturnSelectReasonModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OrderReturnSelectReasonModel orderReturnSelectReasonModel) {

                        if (orderReturnSelectReasonModel.isSuccess()) {
                            mReturnReason.clear();
                            mReturnReason.addAll(orderReturnSelectReasonModel.getData());
                            showPopuWindow();
                        }
                    }
                });
    }

    private void showPopuWindow() {
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        View view = View.inflate(this, R.layout.deliver_time_popu, null);
        mRySeclectionReason = view.findViewById(R.id.ry_select);
        tvCancel = view.findViewById(R.id.tv_cancel_reason);
        mReturnAdapter = new ReturnOrderAdapter(R.layout.select_reason, mReturnReason);
        mRySeclectionReason.setLayoutManager(new LinearLayoutManager(mContext));

        //添加分隔线
        DividerItemDecoration dividerPreKillDecoration = new DividerItemDecoration(mActivity,
                DividerItemDecoration.VERTICAL_LIST);
        dividerPreKillDecoration.setDivider(R.drawable.app_divider);
        mRySeclectionReason.addItemDecoration(dividerPreKillDecoration);


        mRySeclectionReason.setAdapter(mReturnAdapter);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);

        dialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mReturnAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dialog.dismiss();
                icFirst = true;
                mTvSelectReason.setText(mReturnReason.get(position));

            }
        });
    }

    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(ReturnGoodActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(ReturnGoodActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(ReturnGoodActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }

            @Override
            public void deletPic(int position) {
                picList.remove(position);
                upImage(filesToMultipartBodyParts(picList));
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @Override
        public void onAddPicClick() {
            showPop();

        }
    };

    private void showPop() {
        View bottomView = View.inflate(ReturnGoodActivity.this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = (TextView) bottomView.findViewById(R.id.tv_album);
        TextView mCamera = (TextView) bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = (TextView) bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(ReturnGoodActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum - selectList.size())
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .compress(true)
                                .isCamera(false)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:


                        //拍照
                        PictureSelector.create(ReturnGoodActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .compress(true)
                                .setOutputCameraPath("/CustomPath")
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_cancel:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);
                    for (int i = 0; i < images.size(); i++) {
                        picList.add(images.get(i).getCompressPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    upImage(filesToMultipartBodyParts(picList));
                    break;
            }
        }
    }

    protected void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public List<MultipartBody.Part> filesToMultipartBodyParts(List<String> localUrls) {
        List<MultipartBody.Part> parts = new ArrayList<>(localUrls.size());
        for (String url : localUrls) {
            File file = new File(url);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("detailFiles", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }


    private void sendMgs() {
        icFirst = false;
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < mProductList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("businessId", mProductList.get(i).getBusinessId());
                jsonObject.put("businessType", mProductList.get(i).getBusinessType());
                jsonObject.put("additionFlag", mProductList.get(i).additionFlag);
                JSONArray jsonArray1 = new JSONArray();
                for (int j = 0; j < mProductList.get(i).getDetails().size(); j++) {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("returnNum", mProductList.get(i).getDetails().get(j).getItemNum());
                    jsonObject1.put("returnUnit", mProductList.get(i).getDetails().get(j).getItemUnitId());
                    jsonObject1.put("priceId", mProductList.get(i).getDetails().get(j).getPriceId());
                    jsonObject1.put("returnTotalAmount", mProductList.get(i).getDetails().get(j).getItemPrice());
                    jsonArray1.put(jsonObject1);
                }
                jsonObject.put("returnNums", jsonArray1);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (isChecked) {
            isChecked = false;
            //生成退货单
            ReturnOrderApi.requestReturnOrder(mContext, orderId, mTvSelectReason.getText().toString(),
                    et_return_content.getText().toString(), totalPrice + "", returnPic, jsonArray, channelValue)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ReturnOrderSucModel>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            isChecked = true;
                            tv_post_order.setClickable(true);
                            AppHelper.showMsg(mContext, e.getMessage());
                        }

                        @Override
                        public void onNext(ReturnOrderSucModel model) {
                            if (model.success) {
                                isChecked = true;
                                AppHelper.showMsg(mContext,"申请退货成功,请等待审核");
                                Intent intent = new Intent(mContext, ReturnGoodDetailActivity.class);
                                intent.putExtra(AppConstant.RETURNPRODUCTMAINID, String.valueOf(model.data));
                                intent.putExtra("orderType", orderDeliveryType);
                                startActivity(intent);
                                finish();
                            } else {
                                AppHelper.showMsg(mContext, model.message);
                                isChecked = true;
                                icFirst = true;
                            }
                        }
                    });
        }
    }
}
