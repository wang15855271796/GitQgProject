package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王涛} on 2020/1/10
 */
public class GuessModel {


    /**
     * code : 1
     * message : 成功
     * data : [{"productMainId":5,"productId":5,"productName":"【峰仔】白糖桂花糕（免切）（24包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/e71b632f9d4b45b694f644eeb7d1a04d.jpg"},{"productMainId":8,"productId":8,"productName":"【峰仔】红糖方糕（15包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/227abee25ad24bd1ad9751da5234dcb0.jpg"},{"productMainId":9,"productId":9,"productName":"【峰仔】红糖手撕馒头（12包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/07f9ac2e671e41788ffae5a72c84ed79.jpg"},{"productMainId":10,"productId":10,"productName":"【峰仔】量贩紫薯包（5包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/81b81bc4cce147b1b94603361072de8d.jpg"},{"productMainId":11,"productId":11,"productName":"【峰仔】白糖方糕（15包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/0252b2ba800c45979ef2d1a3979b3873.jpg"},{"productMainId":16,"productId":16,"productName":"【升隆】一口肠（4包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/1e1c2d0548b24b928ddd758678c7350f.jpg"},{"productMainId":19,"productId":19,"productName":"【昀源】新调理翅中串（双签）（10包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/bc22781b88fc4158868cd845fa777b08.jpg"},{"productMainId":28,"productId":28,"productName":"【莎公主】带托白毛肚（10包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/3fb1769c6b7049bcb1e67a6432a8f29d.jpg"},{"productMainId":29,"productId":29,"productName":"【太合】鸡肠（20包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/ec0b88925cf441439da951b657ce336c.jpg"},{"productMainId":30,"productId":30,"productName":"【翘歌】把虾串（8包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/a60f8621b5a54d2585c561cc80ba5648.jpg"},{"productMainId":35,"productId":35,"productName":"【皇焙】蛋挞液（24盒/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/b41f33aedac74640a04e92c78c6d2b7b.jpg"},{"productMainId":36,"productId":36,"productName":"【立大食品】去骨鸭爪（20包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/8358fef45dcf4cec8bbf933abd5abf16.jpg"},{"productMainId":37,"productId":37,"productName":"【春涵】去骨凤爪（10包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/4bf5f45db7244df3b50cfb1b8183ece6.jpg"},{"productMainId":38,"productId":38,"productName":"【宁富】麻辣鸡爪（8包/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/038e8c89947b424c9983ca09495578fa.jpg"},{"productMainId":39,"productId":39,"productName":"【锦诺尔】虎皮凤爪（9.5kg）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/375903c45c66425cb897207df20b44a6.jpg"},{"productMainId":40,"productId":40,"productName":"【鑫鹏】干熟肠（10kg/箱）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/a3b9ff1e8a5e4b8e8e2c9c4acb6011a6.jpg"},{"productMainId":41,"productId":41,"productName":"【意润】冻巴沙鱼片","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/17dee6dc94b24aad8c0f9b88fd49afe5.jpg"},{"productMainId":329,"productId":329,"productName":"供应商4","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png"},{"productMainId":4594,"productId":379,"productName":"多规格6","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png"},{"productMainId":4715,"productId":4650,"productName":"脚本测试商品（无动）","imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png"}]
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
         * productMainId : 5
         * productId : 5
         * productName : 【峰仔】白糖桂花糕（免切）（24包/箱）
         * imgUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/e71b632f9d4b45b694f644eeb7d1a04d.jpg
         */

        private int productMainId;
        private int productId;
        private String productName;
        private String imgUrl;

        public int getProductMainId() {
            return productMainId;
        }

        public void setProductMainId(int productMainId) {
            this.productMainId = productMainId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
