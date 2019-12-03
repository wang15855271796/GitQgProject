package com.puyue.www.qiaoge.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.puyue.www.qiaoge.R;
import com.squareup.picasso.Picasso;

/**
 * @author daff
 * @date 2018/10/19.
 * 备注
 */
public class RoundSliderView extends BaseSliderView {
    public RoundSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_default, null);
        ImageView target = (ImageView) v.findViewById(R.id.daimajia_slider_image);
        bindEventAndShow(v, target);
        return v;
    }

    @Override
    protected void bindEventAndShow(View v, ImageView targetImageView) {
        super.bindEventAndShow(v, targetImageView);
        targetImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(getContext()).load(getUrl()).transform(new PicassoRoundTransform()).into(targetImageView);
        v.findViewById(R.id.loading_bar).setVisibility(View.INVISIBLE);
    }
}
