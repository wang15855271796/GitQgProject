package com.puyue.www.qiaoge.model.mine;

import com.puyue.www.qiaoge.base.BaseModel;

/**
 * Created by Administrator on 2018/6/13.
 */

public class UpdateModel extends BaseModel {

    /**
     * data : {"version":"2.0.1","url":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/installPackage/android/app-baidu-release_200_jiagu_sign.apk","msg":"1:更新测试","update":true,"forceUpdate":false}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * version : 2.0.1
         * url : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/installPackage/android/app-baidu-release_200_jiagu_sign.apk
         * msg : 1:更新测试
         * update : true
         * forceUpdate : false
         */

        public String version;
        public String url;
        public String msg;
        public boolean update;
        public boolean forceUpdate;
    }
}
