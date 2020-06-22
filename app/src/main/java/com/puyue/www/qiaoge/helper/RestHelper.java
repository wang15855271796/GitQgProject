package com.puyue.www.qiaoge.helper;

import android.content.Context;
import android.util.Log;

import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.internal.util.ScalarSynchronousObservable;

/**
 * Created by GuoGai on 2016/7/18.
 */
public class RestHelper {
    /**
     * 获取BaseRetrofit
     *
     * @return
     */
    public static Retrofit getBaseRetrofit(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getRestClient(context))
                .baseUrl(AppInterfaceAddress.BASE_URL)
                .build();
        return retrofit;
    }

    /**
     * 地图请求
     *
     * @param context
     * @return
     */
    public static Retrofit getBaiDuRetrofit(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getRestClient(context))
                .baseUrl("http://api.map.baidu.com/")
                .build();
        return retrofit;
    }

    /**
     * 返回Json数据String的时候
     * String 返回的参数
     *
     * @param context
     * @return
     */
    public static Retrofit getStringBaseRetrofit(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getRestClient(context))
                .baseUrl(AppInterfaceAddress.BASE_URL)
                .build();
        return retrofit;
    }

    /**
     * webView 请求
     */
    public static Retrofit getBaseRetrofit(Context context, String Url) {
        Retrofit retrofit = new Retrofit.Builder()
                // .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getRestClient(context))
                .baseUrl(Url)
                .build();
        return retrofit;
    }


    /**
     * 返回OkHttpClient
     *
     * @return
     */
    public static OkHttpClient getRestClient(final Context context) {
        //公共参数拦截器(区分在哪里接收)
        Interceptor commonParamsInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
//                //后期修改
//                UserModel userModel = (UserModel) CommonMethod.getObject(context, UserModel.class, Const.USERMODEL);
//                String userId = null;
//                if (userModel != null) {
//                    userId = userModel.getUserId();
//                }
                Request originalRequest = chain.request();
                String stime = AppSafeHelper.getSTime();
                HttpUrl commonUrl = originalRequest.url()
                        .newBuilder()
                        .addQueryParameter(AppConstant.APP_TYPE, "1")
                        .addQueryParameter(AppConstant.VERSION, AppHelper.getVersion(context))
                        .addQueryParameter(AppConstant.STIME, stime)
                        .addQueryParameter(AppConstant.SIGN, AppSafeHelper.sign(stime))
//                        .addQueryParameter(AppConstant.PLATFORMCODE, "DONG_FANG_PLATFORM")//要修改
//                        .addQueryParameter(AppConstant.TOKEN, DeviceHelper.getDeviceId(context))
                        .addQueryParameter(AppConstant.TOKEN, UserInfoHelper.getUserId(context))
                        .addQueryParameter(AppConstant.CITYNAME, UserInfoHelper.getCity(context))
                        .addQueryParameter(AppConstant.PHONEMODEL, AppHelper.getSystemModel())
                        .addQueryParameter(AppConstant.SYSETEMMODEL, AppHelper.getSystemModel())
                        .addQueryParameter(AppConstant.ANDROIDMODEL, AppHelper.getSystemVersion())
                        .addQueryParameter(AppConstant.PHONEIP, UserInfoHelper.getPhoneip(context))
                        .addQueryParameter(AppConstant.MACIP, UserInfoHelper.getMac(context))
                        .addQueryParameter(AppConstant.changeFlag, UserInfoHelper.getChangeFlag(context))
                        .addQueryParameter(AppConstant.AreaName, UserInfoHelper.getAreaName(context))
                        .addQueryParameter(AppConstant.LOCATIONADDRESS, UserInfoHelper.getLocationadress(context))

                        .build();
                Request commonRequest = originalRequest.newBuilder().url(commonUrl).build();
                Log.d("----->", commonRequest + AppConstant.TOKEN);
                return chain.proceed(commonRequest);
            }
        };
        //Log拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("RestLogging", message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //cache拦截器
        File cacheFile = new File(context.getExternalCacheDir(), "QiaoGeCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkHelper.isNetworkAvailable(context)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetWorkHelper.isNetworkAvailable(context)) {
                    int maxAge = 0 * 60;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //client
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(cacheInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(commonParamsInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        return client;
    }
}
