package com.puyue.www.qiaoge.api.home;

import java.util.List;

/**
 * Created by ${王涛} on 2020/1/4
 */
public class BannerModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"showType":4,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//67a7e2440d1043a4987dbc3286cf42c6.png","linkSrc":null,"detailPic":null,"prodPage":null,"businessId":4594,"businessType":1}]
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * showType : 4
         * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//67a7e2440d1043a4987dbc3286cf42c6.png
         * linkSrc : null
         * detailPic : null
         * prodPage : null
         * businessId : 4594
         * businessType : 1
         */

        private int showType;
        private String defaultPic;
        private Object linkSrc;
        private Object detailPic;
        private Object prodPage;
        private int businessId;
        private int businessType;

        public int getShowType() {
            return showType;
        }

        public void setShowType(int showType) {
            this.showType = showType;
        }

        public String getDefaultPic() {
            return defaultPic;
        }

        public void setDefaultPic(String defaultPic) {
            this.defaultPic = defaultPic;
        }

        public Object getLinkSrc() {
            return linkSrc;
        }

        public void setLinkSrc(Object linkSrc) {
            this.linkSrc = linkSrc;
        }

        public Object getDetailPic() {
            return detailPic;
        }

        public void setDetailPic(Object detailPic) {
            this.detailPic = detailPic;
        }

        public Object getProdPage() {
            return prodPage;
        }

        public void setProdPage(Object prodPage) {
            this.prodPage = prodPage;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }
    }
}