package com.puyue.www.qiaoge.model.market;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/21
 */
public class MarketAlreadyGoodModel {


    /**
     * code : 1
     * message : 成功
     * data : [{"productId":81,"productName":"【海欣】大福包（2.5kg/包）","spec":"规格：2.5kg*4包/箱","showPrice":"￥250/箱","monthSalesVolume":"销量:163","inventory":"余量:90箱1包","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","type":null,"price":"￥250/箱","productUnitName":"箱","productUnit":102,"productCombinationPriceId":258,"num":1,"oldPrice":"","prodTypeUrl":"","minMaxPrice":null,"minMaxPriceList":null},{"productId":101,"productName":"猜你喜欢10","spec":"规格：123456/箱","showPrice":"￥20/箱","monthSalesVolume":"销量:5036","inventory":"余量:3箱1包1支","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","type":null,"price":"￥20/箱","productUnitName":"箱","productUnit":141,"productCombinationPriceId":318,"num":1,"oldPrice":"","prodTypeUrl":"","minMaxPrice":null,"minMaxPriceList":null},{"productId":84,"productName":"采购入库\u2014供应商记忆单价","spec":"规格：123456/箱","showPrice":"￥0.91/箱","monthSalesVolume":"销量:131","inventory":"余量:54箱","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","type":null,"price":"￥0.91/箱","productUnitName":"箱","productUnit":107,"productCombinationPriceId":272,"num":1,"oldPrice":"","prodTypeUrl":"","minMaxPrice":null,"minMaxPriceList":null},{"productId":57,"productName":"【海欣】撒尿牛肉丸（500g/包）","spec":"规格：1556/箱","showPrice":"￥2.23/箱","monthSalesVolume":"销量:195","inventory":"余量:939箱","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/b1aa57ce750b4f8fa9ea1113158cd69f.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","type":null,"price":"￥2.23/箱","productUnitName":"箱","productUnit":65,"productCombinationPriceId":92,"num":1,"oldPrice":"","prodTypeUrl":"","minMaxPrice":null,"minMaxPriceList":null}]
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
         * productId : 81
         * productName : 【海欣】大福包（2.5kg/包）
         * spec : 规格：2.5kg*4包/箱
         * showPrice : ￥250/箱
         * monthSalesVolume : 销量:163
         * inventory : 余量:90箱1包
         * flag : 1
         * imgUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
         * collectionIcon : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png
         * type : null
         * price : ￥250/箱
         * productUnitName : 箱
         * productUnit : 102
         * productCombinationPriceId : 258
         * num : 1
         * oldPrice :
         * prodTypeUrl :
         * minMaxPrice : null
         * minMaxPriceList : null
         */

        private int productId;
        private String productName;
        private String spec;
        private String showPrice;
        private String monthSalesVolume;
        private String inventory;
        private int flag;
        private String imgUrl;
        private String collectionIcon;
        private Object type;
        private String price;
        private String productUnitName;
        private int productUnit;
        private int productCombinationPriceId;
        private int num;
        private String oldPrice;
        private String prodTypeUrl;
        private Object minMaxPrice;
        private Object minMaxPriceList;

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

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getShowPrice() {
            return showPrice;
        }

        public void setShowPrice(String showPrice) {
            this.showPrice = showPrice;
        }

        public String getMonthSalesVolume() {
            return monthSalesVolume;
        }

        public void setMonthSalesVolume(String monthSalesVolume) {
            this.monthSalesVolume = monthSalesVolume;
        }

        public String getInventory() {
            return inventory;
        }

        public void setInventory(String inventory) {
            this.inventory = inventory;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getCollectionIcon() {
            return collectionIcon;
        }

        public void setCollectionIcon(String collectionIcon) {
            this.collectionIcon = collectionIcon;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getProductUnitName() {
            return productUnitName;
        }

        public void setProductUnitName(String productUnitName) {
            this.productUnitName = productUnitName;
        }

        public int getProductUnit() {
            return productUnit;
        }

        public void setProductUnit(int productUnit) {
            this.productUnit = productUnit;
        }

        public int getProductCombinationPriceId() {
            return productCombinationPriceId;
        }

        public void setProductCombinationPriceId(int productCombinationPriceId) {
            this.productCombinationPriceId = productCombinationPriceId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getProdTypeUrl() {
            return prodTypeUrl;
        }

        public void setProdTypeUrl(String prodTypeUrl) {
            this.prodTypeUrl = prodTypeUrl;
        }

        public Object getMinMaxPrice() {
            return minMaxPrice;
        }

        public void setMinMaxPrice(Object minMaxPrice) {
            this.minMaxPrice = minMaxPrice;
        }

        public Object getMinMaxPriceList() {
            return minMaxPriceList;
        }

        public void setMinMaxPriceList(Object minMaxPriceList) {
            this.minMaxPriceList = minMaxPriceList;
        }
    }
}
