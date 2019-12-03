package com.puyue.www.qiaoge.activity.mine.order;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.puyue.www.qiaoge.R;

import com.puyue.www.qiaoge.adapter.mine.OrderEvaluateAdapter;
import com.puyue.www.qiaoge.api.home.GetWalletDetailAPI;
import com.puyue.www.qiaoge.api.mine.order.GetEvaDetailAPI;
import com.puyue.www.qiaoge.api.mine.order.OrderEvaluateAPI;
import com.puyue.www.qiaoge.api.mine.order.SendImageAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.order.GetEvaDetailModel;
import com.puyue.www.qiaoge.model.mine.order.OrderEvaluateListModel;
import com.puyue.www.qiaoge.model.mine.order.OrderEvaluateModel;
import com.puyue.www.qiaoge.model.mine.order.SendImageModel;
import com.puyue.www.qiaoge.pictureselectordemo.FullyGridLayoutManager;
import com.puyue.www.qiaoge.pictureselectordemo.GridImageAdapter;
import com.puyue.www.qiaoge.view.StarBarView;
import com.puyue.www.qiaoge.view.flowtaglayout.MemberEntityImpl;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/10.
 */

public class OrderEvaluateActivity extends BaseSwipeActivity implements OrderEvaluateAdapter.OnStatusListener {

    private RecyclerView mRv;
    private ImageView mIvBack;
    private List<OrderEvaluateListModel> mListEvaluate;
    private String orderId;
    private OrderEvaluateAdapter mAdapterOrderEvaluate;
    private TextView mBtn;
    private HashMap<Integer, String> mMapEvaluate = new HashMap<>();
    private List<Integer> mListId = new ArrayList<>();
    private List<Integer> mListType = new ArrayList<>();
    private List<String> mListContent = new ArrayList<>();
    private OrderEvaluateModel mModelOrderEvaluate;

    private StarBarView sbv_star_bar;//星级

    private TextView tv_evaluate_status;//满意程度
    private EditText et_content;

    private List<String> mListStar = new ArrayList<>();

    private String star;

    private int driverType;

    private String driverName;
    private String sendTime;


    private TextView tv_driver_name;
    private TextView tv_send_time;
    private int driverId;

//相机

    private PopupWindow pop;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;


    private ArrayList<MemberEntityImpl> mList;
    private int item_pos = 0;


    String returnPic = "";
    private OrderEvaluateListModel orderEvaluateListModel;
    //选择图片位置
    private int addPicPosition;

    private List<String> mListPic = new ArrayList<>();

    private TextView tv_driver;
    private TextView tv_good;
    private List<String> mListPicTwo = new ArrayList<>();

    private HashMap<Integer, String> mPicUrl = new HashMap<>();

    private List<List<String>> mReturnPic = new ArrayList<>();

    private int orderDeliveryType;
    private RelativeLayout rl_driver;

   //图片凭证
    private String[] mPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        orderId = getIntent().getStringExtra("orderId");
        orderDeliveryType = getIntent().getIntExtra("orderDeliveryType", 0);
        mListEvaluate = ((List<OrderEvaluateListModel>) getIntent().getSerializableExtra("evaluateList"));
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_order_evaluate);
    }

    @Override
    public void findViewById() {
        mRv = (RecyclerView) findViewById(R.id.rv_order_evaluate);
        mIvBack = (ImageView) findViewById(R.id.iv_evaluate_back);
        mBtn = (TextView) findViewById(R.id.btn_order_evaluate);
        sbv_star_bar = findViewById(R.id.sbv_star_bar);
        tv_evaluate_status = findViewById(R.id.tv_evaluate_status);
        et_content = findViewById(R.id.et_content);
        tv_driver_name = findViewById(R.id.tv_driver_name);
        tv_send_time = findViewById(R.id.tv_send_time);
        tv_driver = findViewById(R.id.tv_driver);
        rl_driver = findViewById(R.id.rl_driver);
        tv_good = findViewById(R.id.tv_good);
    }

    @Override
    public void setViewData() {

        mMapEvaluate.clear();
        mListStar.clear();

        mPic=new String[mListEvaluate.size()];
        for (int i = 0; i < mPic.length; i++) {
            mPic[i] ="";
        }



        if (orderDeliveryType == 0) {
            rl_driver.setVisibility(View.VISIBLE);
        } else {
            rl_driver.setVisibility(View.GONE);
        }
        mList = new ArrayList<MemberEntityImpl>();


        setStarName(tv_evaluate_status, 5.0f);
        if (mListEvaluate != null && mListEvaluate.size() > 0) {
            //默认所有的评价先是空字符串
            for (int i = 0; i < mListEvaluate.size(); i++) {
                mMapEvaluate.put(i, "");
            }

            for (int i = 0; i < mListEvaluate.size(); i++) {
                mListStar.add(mListEvaluate.get(i).getStar());
            }

            mListStar.add(0, "5");
            for (int i = 0; i < mListEvaluate.size(); i++) {
                MemberEntityImpl commentsInfo = new MemberEntityImpl();
                List<LocalMedia> commentImgs = new ArrayList<>();
                commentsInfo.setCommentImgs(commentImgs);
                mList.add(commentsInfo);
            }


            mRv.setLayoutManager(new LinearLayoutManager(mContext));
            mAdapterOrderEvaluate = new OrderEvaluateAdapter(mContext, R.layout.item_order_evaluate, mListEvaluate, mList, new OrderEvaluateAdapter.SaveEditListener() {
                @Override
                public void saveEdit(int position, String string) {
                    mMapEvaluate.put(position, string);
                }


            });
            mAdapterOrderEvaluate.setOnStatusListener(this);
            mRv.setAdapter(mAdapterOrderEvaluate);
            mRv.setItemViewCacheSize(50);
        } else {
            AppHelper.showMsg(mContext, "订单商品数据错误!");
        }

        getData(orderId);


    }


    @Override
    public void onSetStatusListener(int pos) {
        addPicture(pos);
        addPicPosition = pos;
        this.item_pos = pos;


    }


    /**
     * 进入相册添加图片
     */
    private void addPicture(int pos) {


        if (item_pos != pos) {
            selectList = new ArrayList<>();
        }

        //   showPop();

        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(3)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量

                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选
                .previewImage(true)// 是否可预览图片
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(false)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                //   .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                .isGif(true)// 是否显示gif图片.

                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }



    @Override
    public void onDeleteListener(int pos, int tagPos) {
        MemberEntityImpl commentsInfo = mList.get(pos);
        List<LocalMedia> commentImgs = commentsInfo.getCommentImgs();
        commentImgs.remove(tagPos);

        commentsInfo.setCommentImgs(commentImgs);
        mList.set(pos, commentsInfo);
     mAdapterOrderEvaluate.notifyItemChanged(pos,1);
        Log.i("eeerarqt", "onDeleteListener: " + pos);


        mListPicture.clear();

        for (int i = 0; i < mList.size(); i++) {
            //压缩路径
            List<String> picList = new ArrayList();
            for (int j = 0; j < mList.get(i).getCommentImgs().size(); j++) {
                picList.add(mList.get(i).getCommentImgs().get(j).getCompressPath());

            }
            mListPicture.add(i, picList);

        }


            //   String substring1 = s.substring(1, s.length() - 1);
            //     list1.add(substring1);


            upImage(filesToMultipartBodyParts(mListPicture.get(pos)), pos);




    }

    @Override
    public void onSetListener(int pos) {
        PictureSelector.create(mActivity).externalPicturePreview(pos, selectList);
    }

    private List<List<String>> mListPicture = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);

                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    MemberEntityImpl commentsInfo = mList.get(item_pos);
                    commentsInfo.setCommentImgs(selectList);
                    mList.set(item_pos, commentsInfo);


                    mListPicture.clear();
                    for (int i = 0; i < mList.size(); i++) {
                        //压缩路径

                        List<String> picList = new ArrayList();
                        for (int j = 0; j < mList.get(i).getCommentImgs().size(); j++) {
                            picList.add(mList.get(i).getCommentImgs().get(j).getCompressPath());

                        }
                        mListPicture.add(i, picList);

                    }










                    /*commentAdapter.setList(selectList);
                    commentAdapter.setClick(false);*/
                   mAdapterOrderEvaluate.notifyItemChanged(item_pos,1);
                    upImage(filesToMultipartBodyParts(mListPicture.get(item_pos)), item_pos);
                    /*recyclerView.smoothScrollToPosition(selectList.size());
                    if (selectList.size() > 0) {
                        upFinish = false;
                        biaoshi = false;
                    } else {
                        upFinish = true;
                    }
                    checkProgress();
                    putPicture(0);*/

                    break;

            }
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

    private void getData(String orderId) {
        GetEvaDetailAPI.requestEval(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetEvaDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetEvaDetailModel getEvaDetailModel) {

                        if (getEvaDetailModel.isSuccess()) {
                            driverType = getEvaDetailModel.getData().getBusinessType();
                            driverName = getEvaDetailModel.getData().getDriverName();
                            sendTime = getEvaDetailModel.getData().getSendTime();
                            tv_driver_name.setText("司机"+driverName);
                            tv_send_time.setText(sendTime+"送达");
                            driverId = getEvaDetailModel.getData().driverId;
                        } else {
                            AppHelper.showMsg(mContext, getEvaDetailModel.getMessage());
                        }
                    }
                });


    }

    private boolean isPost = true;
    private List<String> getmListPicTwo = new ArrayList<>();

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //如果用户没有输入完所有商品的评价,提示用户退出要提示
                backEvent();
            }
        });
        mBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //提交评价,由于我们是按照position在map里面存储评价数据的,所以可以直接取出来上传给后台
                requestOrderEvaluate();
            }
        });

        sbv_star_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarName(tv_evaluate_status, sbv_star_bar.getStarRating());


            }
        });

        tv_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
mListReturn.clear();
                for (int i = 0; i < mPic.length; i++) {
                    mListReturn.add(i,mPic[i]);
                }

                Log.i("arqwee", "onNext: " + mListReturn.size() + mListReturn.toString());
            }
        });
    }

    /**
     * 设置星星文字
     */
    private void setStarName(TextView tv_content, float star_num) {
        if (star_num == 5.0f) {
            tv_content.setText("很满意");
            star = "5";
        } else if (star_num == 4.0f) {
            tv_content.setText("满意");
            star = "4";
        } else if (star_num == 3.0f) {
            tv_content.setText("一般");
            star = "3";
        } else if (star_num == 2.0f) {
            tv_content.setText("不满意");
            star = "2";
        } else if (star_num == 1.0f) {
            tv_content.setText("非常差");
            star = "1";
        }

    }
private List<String> mListReturn = new ArrayList<>();
    private void requestOrderEvaluate() {
        mListId.clear();
        mListType.clear();
        mListContent.clear();
        mListStar.clear();
        mListPic.clear();
        mListReturn.clear();
        int num = 0;


        for (int i = 0; i < mPic.length; i++) {
            mListReturn.add(i,mPic[i]);
        }

        for (int i = 0; i < mListEvaluate.size(); i++) {
            mListId.add(mListEvaluate.get(i).productId);
            mListType.add(mListEvaluate.get(i).businessType);
            mListStar.add(mListEvaluate.get(i).getStar());

            if (StringHelper.notEmptyAndNull(mMapEvaluate.get(i))) {
                mListContent.add("\"" + mMapEvaluate.get(i) + "\"");
            } else {
                // mListContent.add("");
                mListContent.add("\"" + "" + "\"");
                num += 1;
            }

            if (StringHelper.notEmptyAndNull(mListReturn.get(i))) {

                mListPic.add("\"" + mListReturn.get(i) + "\"");
            } else {
                mListPic.add("\"" + "" + "\"");
            }

        }
        if (orderDeliveryType == 0) {
            //司机id
            mListId.add(0, driverId);
            //司机类型添加
            mListType.add(0, driverType);
            //司机星级添加
            mListStar.add(0, star);
//图片内容
            mListPic.add(0, "\"" + "" + "\"");

//司机评价内容
            mListContent.add(0, "\"" + et_content.getText().toString() + "\"");
            //如果很多商品,至少要写了其中一个商品的评价才能提交
            boolean canEvaluate = true;
            int evaluateNum = 0;
            for (int i = 0; i < mListContent.size(); i++) {
                if (StringHelper.notEmptyAndNull(mListContent.get(i))) {
                    //本条商品输入的评价不为空

                } else {
                    //本条商品的评价输入了空格
                    evaluateNum++;
                }
            }
            //循环完毕,如果这个订单内所有的商品都未被评价
    /*    if (evaluateNum == mListEvaluate.size()) {
            //所有商品都没有评价
            canEvaluate = false;
        }*/
            if (mListId != null && mListId.size() > 0 && mListType != null && mListType.size() > 0 && mListContent != null && mListContent.size() > 0 && mListStar != null && mListStar.size() > 0) {

                String businessIds = mListId.toString();
                String contents = mListContent.toString();
                String businessTypes = mListType.toString();
                String levels = mListStar.toString();
                String pictures = mListPic.toString();
                Log.i("qrwrwr", "requestOrderEvaluate: " + pictures);
                OrderEvaluateAPI.requestOrderComment(mContext, orderId, businessIds, contents, businessTypes, levels, pictures)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<OrderEvaluateModel>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(OrderEvaluateModel orderEvaluateModel) {
                                logoutAndToHome(mContext, orderEvaluateModel.code);
                                mModelOrderEvaluate = orderEvaluateModel;
                                if (mModelOrderEvaluate.success) {
                                    //评价成功
                                    AppHelper.showMsg(mContext, "评价成功");
                                    //评价成功之后应该退出这个activity,回到订单详情界面,重新请求接口
                                    Intent intent = new Intent();
                                    OrderEvaluateActivity.this.setResult(55, intent);
                                    finish();
                                } else {
                                    AppHelper.showMsg(mContext, mModelOrderEvaluate.message);
                                }
                            }
                        });


            } else {
                AppHelper.showMsg(mContext, "请至少填写一条评价");
            }
        } else {
            //如果很多商品,至少要写了其中一个商品的评价才能提交
            boolean canEvaluate = true;
            int evaluateNum = 0;
            for (int i = 0; i < mListContent.size(); i++) {
                if (StringHelper.notEmptyAndNull(mListContent.get(i))) {
                    //本条商品输入的评价不为空

                } else {
                    //本条商品的评价输入了空格
                    evaluateNum++;
                }
            }
            //循环完毕,如果这个订单内所有的商品都未被评价
    /*    if (evaluateNum == mListEvaluate.size()) {
            //所有商品都没有评价
            canEvaluate = false;
        }*/
            if (mListId != null && mListId.size() > 0 && mListType != null && mListType.size() > 0 && mListContent != null && mListContent.size() > 0 && mListStar != null && mListStar.size() > 0) {

                String businessIds = mListId.toString();
                String contents = mListContent.toString();
                String businessTypes = mListType.toString();
                String levels = mListStar.toString();
                String pictures = mListPic.toString();
                Log.i("qrwrwr", "requestOrderEvaluate: " + pictures);
                OrderEvaluateAPI.requestOrderComment(mContext, orderId, businessIds, contents, businessTypes, levels, pictures)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<OrderEvaluateModel>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(OrderEvaluateModel orderEvaluateModel) {
                                logoutAndToHome(mContext, orderEvaluateModel.code);
                                mModelOrderEvaluate = orderEvaluateModel;
                                if (mModelOrderEvaluate.success) {
                                    //评价成功
                                    AppHelper.showMsg(mContext, "评价成功");
                                    //评价成功之后应该退出这个activity,回到订单详情界面,重新请求接口
                                    Intent intent = new Intent();
                                    OrderEvaluateActivity.this.setResult(55, intent);
                                    finish();
                                } else {
                                    AppHelper.showMsg(mContext, mModelOrderEvaluate.message);
                                }
                            }
                        });


            } else {
                AppHelper.showMsg(mContext, "请至少填写一条评价");
            }
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backEvent();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backEvent() {
        //如果没有输入任何一个评价,直接退出,
        if (mMapEvaluate != null && mMapEvaluate.size() > 0) {
            //有评价
            showBackDialog();
        } else {
            //没有填写任何评价,直接退出即可
            finish();
        }
    }

    private void showBackDialog() {
        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(mContext, R.style.CommonDialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_order_evaluate_back);
        TextView mTvMsg = (TextView) window.findViewById(R.id.tv_dialog_order_evaluate_msg);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.btn_dialog_order_evaluate_confirm);
        TextView mTvCancel = (TextView) window.findViewById(R.id.btn_dialog_order_evaluate_cancel);
        mTvMsg.setText("确定退出评价吗");
        mTvCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
    }



  /*  @Override
    public void saveEdit(int position, String string) {
        mMapEvaluate.put(position, string);
    }*/

    public void upImage(List<MultipartBody.Part> parts, int i) {


        SendImageAPI.requestImgDetail(mContext, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SendImageModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        isPost = false;
                        //  Log.i("errorrrrrr", "onError: " + e.getMessage() + isPost);
                     /*   List<String> list = new ArrayList<>();

                        Log.i("errorrrrrr", "onError: " + mReturnPic.size() + mReturnPic.toString());*/
                        List<String> list = new ArrayList<>();
                        list.add(i, "");
                        mReturnPic.add(i, list);
                    }

                    @Override
                    public void onNext(SendImageModel baseModel) {
                        if (baseModel.success) {
                            isPost = true;
                            returnPic = "";

                            if (baseModel.data != null) {
                                for (int i = 0; i < baseModel.data.length; i++) {
                                    returnPic += baseModel.data[i] + ",";
                                }

                            } else {
                                returnPic = "";
                            }



                            mPic[i] = returnPic;


                            //mListEvaluate.get(addPicPosition).setReturnPicPath(returnPic);

                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });
    }
}
