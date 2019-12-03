package com.puyue.www.qiaoge.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.puyue.www.qiaoge.banner.transformer.AccordionTransformer;
import com.puyue.www.qiaoge.banner.transformer.BackgroundToForegroundTransformer;
import com.puyue.www.qiaoge.banner.transformer.CubeInTransformer;
import com.puyue.www.qiaoge.banner.transformer.CubeOutTransformer;
import com.puyue.www.qiaoge.banner.transformer.DefaultTransformer;
import com.puyue.www.qiaoge.banner.transformer.DepthPageTransformer;
import com.puyue.www.qiaoge.banner.transformer.FlipHorizontalTransformer;
import com.puyue.www.qiaoge.banner.transformer.FlipVerticalTransformer;
import com.puyue.www.qiaoge.banner.transformer.ForegroundToBackgroundTransformer;
import com.puyue.www.qiaoge.banner.transformer.RotateDownTransformer;
import com.puyue.www.qiaoge.banner.transformer.RotateUpTransformer;
import com.puyue.www.qiaoge.banner.transformer.ScaleInOutTransformer;
import com.puyue.www.qiaoge.banner.transformer.StackTransformer;
import com.puyue.www.qiaoge.banner.transformer.TabletTransformer;
import com.puyue.www.qiaoge.banner.transformer.ZoomInTransformer;
import com.puyue.www.qiaoge.banner.transformer.ZoomOutSlideTransformer;
import com.puyue.www.qiaoge.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
