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

    public static class DataBean{
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSendType() {
            return sendType;
        }

        public void setSendType(int sendType) {
            this.sendType = sendType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", sendType=" + sendType +
                    ", userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", contactPhone='" + contactPhone + '\'' +
                    ", provinceCode='" + provinceCode + '\'' +
                    ", provinceName='" + provinceName + '\'' +
                    ", cityCode='" + cityCode + '\'' +
                    ", cityName='" + cityName + '\'' +
                    ", areaCode='" + areaCode + '\'' +
                    ", areaName='" + areaName + '\'' +
                    ", detailAddress='" + detailAddress + '\'' +
                    ", isDefault=" + isDefault +
                    ", shopName='" + shopName + '\'' +
                    '}';
        }

//        @Override
//        public int getItemType() {
//            if(sendType==1) {
//                //可配送
//                return ChooseAddressAdapter.CONTENT;
//            }else if(sendType==0){
//                //不可配送
//                return ChooseAddressAdapter.HEAD;
//            }else {
//                return -1;
//            }
//
//        }
    }
}
