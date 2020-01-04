package com.puyue.www.qiaoge.model.mine.order;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/31
 */
public class IntellGencyModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"cityName":"杭州市","licenseNo":"","qualification":"hangzhou.aliyuncs.com/image/49226c8e25084b69b442fca5a964a99f.jpg"},{"cityName":"天津市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/8dfda126ddd04064bb2a678af6b335d8.png","qualification":"hangzhou.aliyuncs.com/image/49226c8e25084b69b442fca5a964a99f.jpg"},{"cityName":"大同市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/3e7d010603e446e9812b850994de188f.png","qualification":"hangzhou.aliyuncs.com/image/49226c8e25084b69b442fca5a964a99f.jpg"},{"cityName":"北京市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/49226c8e25084b69b442fca5a964a99f.jpg","qualification":""},{"cityName":"深圳市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/b3e6d9119bdb49c29cbbf9f23ec71da6.jpg","qualification":""},{"cityName":"宁波市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/763663d38e77443faa401c9a95fb9e78.jpg","qualification":""},{"cityName":"南充市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/9a38b482383f40f78df74e3c3589cb6f.jpg","qualification":""},{"cityName":"唐山市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/f77122609fbc4dbb9f2c6d662a15231d.png","qualification":""},{"cityName":"温州市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/faff3248fe39446aab24cc4ebbf449f3.jpg","qualification":""},{"cityName":"石家庄市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/f2f3d6a009594a47ac4361dcc1ef9995.jpg","qualification":""},{"cityName":"嘉兴市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/992bb70b5cd549f6803940d91fd107af.jpg","qualification":""},{"cityName":"丽水市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/9e159b1826df4efeafb0578de05b824e.jpg","qualification":""},{"cityName":"台州市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//2f7c2a995a8f4f27af6c7afd25afcd48.jpg","qualification":""},{"cityName":"沈阳市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//d53ad97f30244500a4af7cdddad8c1be.jpg","qualification":""},{"cityName":"西安市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//38bb99bc85594530aa04f395219ec79f.jpg","qualification":""},{"cityName":"贵阳市","licenseNo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//a736c2d02243481d8a3583f0ca71786a.jpg","qualification":""}]
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
         * cityName : 杭州市
         * licenseNo :
         * qualification : hangzhou.aliyuncs.com/image/49226c8e25084b69b442fca5a964a99f.jpg
         */

        private String cityName;
        private String licenseNo;
        private String qualification;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getLicenseNo() {
            return licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
        }

        public String getQualification() {
            return qualification;
        }

        public void setQualification(String qualification) {
            this.qualification = qualification;
        }
    }
}
