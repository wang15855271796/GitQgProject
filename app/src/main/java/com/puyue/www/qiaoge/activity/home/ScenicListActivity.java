package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.home.SellPlaceListAdapter;
import com.puyue.www.qiaoge.api.home.GetScenicSpotDetailByIdAndDateAPI;
import com.puyue.www.qiaoge.api.home.GetSellPlaceListByIdAndDateAPI;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.banner.listener.OnBannerListener;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.GetScenicSpotDetailByIdAndDateModel;
import com.puyue.www.qiaoge.model.home.GetSellPlaceListByIdAndDateModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class ScenicListActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private Banner banner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvVolume;
    private TextView mTvDesc;
    private RecyclerView mRvPlace;

    private List<String> mListBanner = new ArrayList<>();
    private List<GetSellPlaceListByIdAndDateModel.DataBean.ListBean> mListPlace = new ArrayList<>();
    private SellPlaceListAdapter mAdapterPlace;
    private int spotId;
    private int pageNum = 1;
    private int pageSize = 10;
    private double longitude;//经度
    private double latitlatitude;//纬度
    private String title;
    private String content;
    private boolean canLook = false;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            spotId = bundle.getInt(AppConstant.ACTIVEID);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_scenic_list);
    }

    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
        banner = FVHelper.fv(this, R.id.banner_activity_scenic);
        mTvTitle = FVHelper.fv(this, R.id.tv_activity_scenic_title);
        mTvPrice = FVHelper.fv(this, R.id.tv_activity_scenic_price);
        mTvVolume = FVHelper.fv(this, R.id.tv_activity_scenic_volume);
        mTvDesc = FVHelper.fv(this, R.id.tv_activity_scenic_desc);
        mRvPlace = FVHelper.fv(this, R.id.rv_activity_scenic);
    }

    @Override
    public void setViewData() {
        mRvPlace.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapterPlace = new SellPlaceListAdapter(R.layout.item_search_result, mListPlace);
        mRvPlace.setAdapter(mAdapterPlace);
        mAdapterPlace.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                getSellPlaceList(pageNum, pageSize, spotId);
            }
        }, mRvPlace);
        mAdapterPlace.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, PlaceGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, mAdapterPlace.getData().get(position).id);
                startActivity(intent);
            }
        });
        getScenicSpotDetail(spotId);
        getSellPlaceList(pageNum, pageSize, spotId);
    }

    /**
     * 获取可用场地
     **/
    private void getSellPlaceList(int pageNum, int pageSize, int spotId) {
        GetSellPlaceListByIdAndDateAPI.requestData(mContext, pageNum, pageSize, spotId, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetSellPlaceListByIdAndDateModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetSellPlaceListByIdAndDateModel model) {
                        logoutAndToHome(mContext, model.code);
                        if (model.success) {
                            if (model.data.list != null) {
                                mAdapterPlace.addData(model.data.list);
                            }
                            if (model.data.hasNextPage) {
                                mAdapterPlace.loadMoreComplete();
                            } else {
                                mAdapterPlace.loadMoreEnd(false);
                            }
                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }
                    }
                });
    }

    /**
     * 获取详情
     **/
    private void getScenicSpotDetail(int spotId) {
        GetScenicSpotDetailByIdAndDateAPI.requestData(mContext, spotId, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetScenicSpotDetailByIdAndDateModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetScenicSpotDetailByIdAndDateModel model) {
                        logoutAndToHome(mContext, model.code);
                        if (model.success) {
                            mTvTitle.setText(model.data.name);
                            mTvPrice.setText("预订量:" + model.data.scenicSpotTotalReservation);
                            mTvVolume.setText("剩余:" + model.data.scenicSpotResidualQuantity);
                            mTvDesc.setText(model.data.desc);
                            longitude = Double.parseDouble(model.data.longitude);
                            latitlatitude = Double.parseDouble(model.data.latitude);
                            title = model.data.name;
                            content = model.data.desc;
                            canLook = true;
                            if (model.data.picUrlList != null) {
                                //填充banner
                                //设置banner样式
                                banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                                //设置图片加载器
                                banner.setImageLoader(new GlideImageLoader());
                                //设置图片集合
                                mListBanner.addAll(model.data.picUrlList);
                                banner.setImages(mListBanner);
                                //设置banner动画效果
                                banner.setBannerAnimation(Transformer.DepthPage);
                                //设置自动轮播，默认为true
                                banner.isAutoPlay(true);
                                //设置轮播时间
                                banner.setDelayTime(3000);
                                //设置指示器位置（当banner模式中有指示器时）
                                banner.setIndicatorGravity(BannerConfig.RIGHT);
                                //banner设置方法全部调用完毕时最后调用
                                banner.start();
                            }
                            //banner设置点击监听
                            banner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    AppHelper.showPhotoDetailDialog(mContext, mListBanner, position);
                                }
                            });
                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }

                    }
                });
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mTvTitle.setOnClickListener(noDoubleClickListener);

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == mTvTitle) {
                if (canLook) {
                    Intent intent = new Intent(mContext, MapActivity.class);
                    intent.putExtra(AppConstant.LONGITUDE, longitude);
                    intent.putExtra(AppConstant.LATITLATITUDE, latitlatitude);
                    intent.putExtra(AppConstant.TITLE, title);
                    intent.putExtra(AppConstant.CONTENT, content);
                    startActivity(intent);
                }
            }
        }
    };
}
