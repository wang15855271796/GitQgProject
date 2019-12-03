package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class GetEquipmentDetailPicByEquipmentIdModel {

    /**
     * code : 1
     * message : 成功
     * data : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/9a3bb39678504d72bf8ad98035413d3d.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/9a3bb39678504d72bf8ad98035413d3d.jpg"]
     * error : false
     * success : true
     */

    public int code;
    public String message;
    public boolean error;
    public boolean success;
    public List<String> data;
}
