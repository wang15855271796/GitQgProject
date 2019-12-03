package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class GetSellPlaceDetailPicBySellPlaceIdModel {

    /**
     * code : 1
     * message : 成功
     * data : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/c72bb47679b94dfea9bc4ff834467dfa.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/6f97cfd6444e4b4c9755f11f8d32bd8a.jpg"]
     * success : true
     * error : false
     */

    public int code;
    public String message;
    public boolean success;
    public boolean error;
    public List<String> data;
}
