package com.puyue.www.qiaoge.model.mine.address;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AddressModel extends BaseModel {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 6
         * userId : 6
         * userName : test
         * contactPhone : 15700191302
         * provinceCode : 110100
         * provinceName : 北京市
         * cityCode : 110101
         * cityName : 东城区
         * areaCode : 110101001
         * areaName : 东华门街道
         * address : null
         * isDefault : 0
         */

        public int id;
        public int userId;
        public String userName;
        public String contactPhone;
        public String provinceCode;
        public String provinceName;
        public String cityCode;
        public String cityName;
        public String areaCode;
        public String areaName;
        public String detailAddress;
        public int isDefault;
        public String shopName;
    }
}
