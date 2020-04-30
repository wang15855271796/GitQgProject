package com.puyue.www.qiaoge.model.mine.address;

import android.util.Log;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.puyue.www.qiaoge.adapter.ChooseAddressAdapter;
import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AddressModel extends BaseModel {

    public List<DataBean> data;

    public static class DataBean implements MultiItemEntity {
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
//        0 地址不可用， 1可用
        public int sendType;
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

        @Override
        public int getItemType() {
            if(sendType==1) {
                //可配送
                return ChooseAddressAdapter.CONTENT;
            }else if(sendType==0){
                //不可配送
                return ChooseAddressAdapter.HEAD;
            }else {
                return -1;
            }

        }
    }
}
