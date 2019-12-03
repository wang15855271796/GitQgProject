package com.puyue.www.qiaoge.model.cart;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class CartListModel extends BaseModel {

    /**
     * data : {"pageNum":1,"pageSize":100,"startRow":0,"pages":1,"total":5,"list":[{"validList":[{"cartId":61,"businessId":9,"businessType":2,"picUrl":"","name":"鸡肉秒杀1限时2天","spec":null,"productDescVOList":[{"detailDesc":"￥80.00/箱","productNum":1,"productCombinationPriceId":null,"activityId":9,"equipmentId":null,"price":"80.00","highNum":0}],"valid":true},{"cartId":62,"businessId":10,"businessType":2,"picUrl":"","name":"鸡肉秒杀2限时2天","spec":null,"productDescVOList":[{"detailDesc":"￥80.00/箱","productNum":1,"productCombinationPriceId":null,"activityId":10,"equipmentId":null,"price":"80.00","highNum":0}],"valid":true},{"cartId":63,"businessId":12,"businessType":3,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"精选团购","spec":"500g","productDescVOList":[{"detailDesc":"￥80.00/组","productNum":1,"productCombinationPriceId":null,"activityId":12,"equipmentId":null,"price":"80.00","highNum":0}],"valid":true},{"cartId":87,"businessId":48,"businessType":1,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"鸡肉","spec":"200*10","productDescVOList":[{"detailDesc":"￥900.00/2箱","productNum":1,"productCombinationPriceId":41,"activityId":null,"equipmentId":null,"price":"900.00","highNum":0},{"detailDesc":"￥90.00/2包","productNum":1,"productCombinationPriceId":136,"activityId":null,"equipmentId":null,"price":"90.00","highNum":1},{"detailDesc":"￥9.00/2支","productNum":1,"productCombinationPriceId":137,"activityId":null,"equipmentId":null,"price":"9.00","highNum":1}],"valid":true},{"cartId":88,"businessId":46,"businessType":1,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"鸭肉","spec":"200*10","productDescVOList":[{"detailDesc":"￥100.00/1箱","productNum":1,"productCombinationPriceId":124,"activityId":null,"equipmentId":null,"price":"100.00","highNum":0},{"detailDesc":"￥900.00/10箱","productNum":1,"productCombinationPriceId":125,"activityId":null,"equipmentId":null,"price":"900.00","highNum":0},{"detailDesc":"￥50.00/5包","productNum":1,"productCombinationPriceId":135,"activityId":null,"equipmentId":null,"price":"50.00","highNum":1}],"valid":true}],"inValidList":[]}],"hasPrePage":false,"hasNextPage":false}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 100
         * startRow : 0
         * pages : 1
         * total : 5
         * list : [{"validList":[{"cartId":61,"businessId":9,"businessType":2,"picUrl":"","name":"鸡肉秒杀1限时2天","spec":null,"productDescVOList":[{"detailDesc":"￥80.00/箱","productNum":1,"productCombinationPriceId":null,"activityId":9,"equipmentId":null,"price":"80.00","highNum":0}],"valid":true},{"cartId":62,"businessId":10,"businessType":2,"picUrl":"","name":"鸡肉秒杀2限时2天","spec":null,"productDescVOList":[{"detailDesc":"￥80.00/箱","productNum":1,"productCombinationPriceId":null,"activityId":10,"equipmentId":null,"price":"80.00","highNum":0}],"valid":true},{"cartId":63,"businessId":12,"businessType":3,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"精选团购","spec":"500g","productDescVOList":[{"detailDesc":"￥80.00/组","productNum":1,"productCombinationPriceId":null,"activityId":12,"equipmentId":null,"price":"80.00","highNum":0}],"valid":true},{"cartId":87,"businessId":48,"businessType":1,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"鸡肉","spec":"200*10","productDescVOList":[{"detailDesc":"￥900.00/2箱","productNum":1,"productCombinationPriceId":41,"activityId":null,"equipmentId":null,"price":"900.00","highNum":0},{"detailDesc":"￥90.00/2包","productNum":1,"productCombinationPriceId":136,"activityId":null,"equipmentId":null,"price":"90.00","highNum":1},{"detailDesc":"￥9.00/2支","productNum":1,"productCombinationPriceId":137,"activityId":null,"equipmentId":null,"price":"9.00","highNum":1}],"valid":true},{"cartId":88,"businessId":46,"businessType":1,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"鸭肉","spec":"200*10","productDescVOList":[{"detailDesc":"￥100.00/1箱","productNum":1,"productCombinationPriceId":124,"activityId":null,"equipmentId":null,"price":"100.00","highNum":0},{"detailDesc":"￥900.00/10箱","productNum":1,"productCombinationPriceId":125,"activityId":null,"equipmentId":null,"price":"900.00","highNum":0},{"detailDesc":"￥50.00/5包","productNum":1,"productCombinationPriceId":135,"activityId":null,"equipmentId":null,"price":"50.00","highNum":1}],"valid":true}],"inValidList":[]}]
         * hasPrePage : false
         * hasNextPage : false
         */

        public int pageNum;
        public int pageSize;
        public int startRow;
        public int pages;
        public int total;
        public boolean hasPrePage;
        public boolean hasNextPage;
        public List<ListBean> list;

        public static class ListBean {

            public List<ValidListBean> validList;
            public List<ValidListBean> inValidList;

            public double sendAmount;
            public static class ValidListBean {
                /**
                 * cartId : 61
                 * businessId : 9
                 * businessType : 2
                 * picUrl :
                 * name : 鸡肉秒杀1限时2天
                 * spec : null
                 * productDescVOList : [{"detailDesc":"￥80.00/箱","productNum":1,"productCombinationPriceId":null,"activityId":9,"equipmentId":null,"price":"80.00","highNum":0}]
                 * valid : true
                 */
                public String flagUrl;
                public int cartId;
                public int businessId;
                public int businessType;
                public String picUrl;
                public String name;
                public String spec;
                public boolean valid;
                public List<ProductDescVOListBean> productDescVOList;
                public String inventory;
                public List<AdditionProductVOListBean> additionProductVOList;


                public static class AdditionProductVOListBean {
                    public String picUrl;
                    public String name;//赠品名称
                    public String spec;//规格
                    public String inventory;//余量
                    public String additionNum;//赠送数量
                    public int additionFlag;//1，正常赠送，2，已赠完
                    public String flagUrl;
                    public int productId;
                    public String finishUrl;

                }

                public static class ProductDescVOListBean {
                    /**
                     * detailDesc : ￥80.00/箱
                     * productNum : 1
                     * productCombinationPriceId : null
                     * activityId : 9
                     * equipmentId : null
                     * price : 80.00
                     * highNum : 0  为0代表着没有最高限制(第一规格),有的话代表着不是第一规格,需要限制
                     */

                    public String detailDesc;
                    public int productNum;
                    public int productCombinationPriceId;
                    public int activityId;
                    public int equipmentId;
                    public String price;
                    public int highNum;
                    public int code = 1;
                    public boolean isBoom;

                    public boolean isBoom() {
                        return isBoom;
                    }

                    public void setBoom(boolean boom) {
                        isBoom = boom;
                    }

                    public int getCode() {
                        return code;
                    }

                    public void setCode(int code) {
                        this.code = code;
                    }
                }
            }
        }

    }
}
