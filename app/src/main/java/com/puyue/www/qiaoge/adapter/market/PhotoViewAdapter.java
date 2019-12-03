package com.puyue.www.qiaoge.adapter.market;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.adapter.cart.CartAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by win7 on 2018/7/23.
 */

public class PhotoViewAdapter extends PagerAdapter {
    List<String> imagesUrl;
    Context context;

    private OnPhotoListener photoListener;
    ScaleGestureDetector mScaleGestureDetector;
    GestureDetector mGestureDetector;

    public OnPhotoListener getPhotoListener() {
        return photoListener;
    }

    public void setPhotoListener(OnPhotoListener photoListener) {
        this.photoListener = photoListener;
    }

    public interface OnPhotoListener {
        void onPhotoListenter();
    }

    public PhotoViewAdapter(List<String> imagesUrl, Context context) {
        this.imagesUrl = imagesUrl;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (imagesUrl == null || imagesUrl.size() == 0) ? 0 : imagesUrl.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = imagesUrl.get(position);
        PhotoView photoView = new PhotoView(context);
        Glide.with(context).load(url).into(photoView);
        container.addView(photoView);
        photoView.isEnabled();
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if (photoListener != null) {
                    photoListener.onPhotoListenter();
                }
            }

           /* @Override
            public void onOutsidePhotoTap() {
                if (photoListener != null) {
                    photoListener.onPhotoListenter();
                }
            }*/

        });

        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}