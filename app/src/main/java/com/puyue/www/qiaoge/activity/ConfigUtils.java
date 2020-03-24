package com.puyue.www.qiaoge.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chuanglan.shanyan_sdk.tool.ShanYanUIConfig;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.RegisterMessageActivity;


public class ConfigUtils {
    /**
     * 闪验三网运营商授权页配置类
     *
     * @param context
     * @return
     */


    //沉浸式竖屏样式
    public static ShanYanUIConfig getCJSConfig(final Context context) {
        /************************************************自定义控件**************************************************************/
//        Drawable logoImgPath = context.getResources().getDrawable(R.drawable.shanyan_logo);
//        Drawable logBtnImgPath = context.getResources().getDrawable(R.drawable.authentication_button);
//        Drawable uncheckedImgPath = context.getResources().getDrawable(R.drawable.sysdk_dialog_uncheck);
        Drawable shapeOrange = context.getResources().getDrawable(R.drawable.shape_orange);
        Drawable navReturnImgPath = context.getResources().getDrawable(R.drawable.sy_sdk_left);

        //loading自定义加载框
//        LayoutInflater inflater = LayoutInflater.from(context);
//        RelativeLayout view_dialog = (RelativeLayout) inflater.inflate(R.layout.dialog_layout, null);
//        RelativeLayout.LayoutParams mLayoutParams3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//        view_dialog.setLayoutParams(mLayoutParams3);
//        view_dialog.setVisibility(View.GONE);

        LayoutInflater inflater1 = LayoutInflater.from(context);
        LinearLayout linearLayout = (LinearLayout) inflater1.inflate(R.layout.chuanlan_view, null);
        LinearLayout.LayoutParams layoutParamsOther = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsOther.setMargins(0, AbScreenUtils.dp2px(context, 25), 0, 10);
        linearLayout.setLayoutParams(layoutParamsOther);

        LayoutInflater inflater2 = LayoutInflater.from(context);
        RelativeLayout linearLayout2 = (RelativeLayout) inflater2.inflate(R.layout.chuanlan_views, null);
        RelativeLayout.LayoutParams layoutParamsOther2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsOther2.setMargins(0, AbScreenUtils.dp2px(context, 250), 0, 10);
        layoutParamsOther2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        linearLayout2.setLayoutParams(layoutParamsOther2);
        TextView tv = linearLayout2.findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterMessageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity(intent);

            }
        });
//        otherLogin(context, relativeLayout);


        /****************************************************设置授权页*********************************************************/
        ShanYanUIConfig uiConfig = new ShanYanUIConfig.Builder()
                //授权页导航栏：
                .setNavColor(Color.parseColor("#ffffff"))  //设置导航栏颜色
                .setNavText("注册")  //设置导航栏标题文字
                .setNavTextColor(0xff080808) //设置标题栏文字颜色
                .setNavReturnImgPath(navReturnImgPath)
                .setNavTextSize(25)

                .setNavReturnBtnWidth(35)
                .setNavReturnBtnHeight(35)

                //授权页logo（logo的层级在次底层，仅次于自定义控件）
//                .setLogoImgPath(logoImgPath)  //设置logo图片
                // 设置logo高度
                // 设置logo相对于标题栏下边缘y偏移
                .setLogoHidden(true)   //是否隐藏logo

                //授权页号码栏：
                .setNumberColor(0xff397BF9)  //设置手机号码字体颜色
                .setNumFieldOffsetY(110)    //设置号码栏相对于标题栏下边缘y偏移
                .setNumberSize(18)
                .setNumberColor(0xff333333)

                .setSloganHidden(true)
//                sssssss
                //授权页登录按钮：
                .setLogBtnText("本机号码一键注册")  //设置登录按钮文字
                .setLogBtnTextColor(0xffffffff)   //设置登录按钮文字颜色
                .setLogBtnOffsetY(200)   //设置登录按钮相对于标题栏下边缘y偏移
                .setLogBtnTextSize(15)
                .setLogBtnHeight(45)
                .setLogBtnImgPath(shapeOrange)


                //授权页隐私栏：
                .setAppPrivacyOne("闪验用户协议", "https://api.253.com/api_doc/yin-si-zheng-ce/wei-hu-wang-luo-an-quan-sheng-ming.html")  //设置开发者隐私条款1名称和URL(名称，url)
                .setAppPrivacyTwo("闪验隐私政策", "https://api.253.com/api_doc/yin-si-zheng-ce/ge-ren-xin-xi-bao-hu-sheng-ming.html")  //设置开发者隐私条款2名称和URL(名称，url)
                .setAppPrivacyColor(0xff797894, 0xffFD641B)   //	设置隐私条款名称颜色(基础文字颜色，协议文字颜色)
                .setPrivacyOffsetBottomY(20)//设置隐私条款相对于屏幕下边缘y偏
                .setPrivacyState(true)
                .setPrivacyOffsetX(24)

                // 添加自定义控件:
                .addCustomView(linearLayout2, false, false, null)
                .addCustomView(linearLayout, false, false, null)
                //标题栏下划线，可以不写
                .build();
        return uiConfig;

    }

}
