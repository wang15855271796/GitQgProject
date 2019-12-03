package com.puyue.www.qiaoge.model.mine.collection;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class CollectionListModel extends BaseModel {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 97
         * productMainId : 97
         * productName : 【源动力】原味鸡腿堡（2.5kg/包）
         * spec : 2.5kg*4包/箱
         * monthSalesVolume : 1
         * flag : 0
         * imgUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
         * collectionIcon : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png
         * minMaxPrice : ￥10
         */

        private int id;
        private int productMainId;
        private String productName;
        private String spec;
        private String monthSalesVolume;
        private int flag;
        private String imgUrl;
        private String collectionIcon;
        private String minMaxPrice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProductMainId() {
            return productMainId;
        }

        public void setProductMainId(int productMainId) {
            this.productMainId = productMainId;
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

        public String getMonthSalesVolume() {
            return monthSalesVolume;
        }

        public void setMonthSalesVolume(String monthSalesVolume) {
            this.monthSalesVolume = monthSalesVolume;
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

        public String getMinMaxPrice() {
            return minMaxPrice;
        }

        public void setMinMaxPrice(String minMaxPrice) {
            this.minMaxPrice = minMaxPrice;
        }
    }


//    /**
//     * data : {"pageNum":1,"pageSize":20,"startRow":0,"pages":1,"total":4,"list":[{"id":3,"pirUrl":"https:barbecue-img.oss-cn-hangzhou.aliyuncs.commasterworker0c658325bb0b402ea3c280bd3d931dbe.jpg","name":"师傅","desc":"好师傅","price":"500.00","unitName":"天","status":null,"businessId":1,"businessType":5,"priceDesc":"￥500.00天","salesDolumeOrReserveDesc":"预订量:0","inventory":null},{"id":4,"pirUrl":"https:barbecue-img.oss-cn-hangzhou.aliyuncs.comproduct9a3bb39678504d72bf8ad98035413d3d.jpg","name":"设备1","desc":"规格1规格1说明","price":"100.00","unitName":"天","status":null,"businessId":1,"businessType":6,"priceDesc":"￥100.00天","salesDolumeOrReserveDesc":"剩余:0","inventory":null},{"id":5,"pirUrl":"https:barbecue-img.oss-cn-hangzhou.aliyuncs.commasterworker0c658325bb0b402ea3c280bd3d931dbe.jpg","name":"风景区1场地1","desc":"介绍","price":"100.00","unitName":"天","status":null,"businessId":1,"businessType":4,"priceDesc":"￥100.00天","salesDolumeOrReserveDesc":"预订量:100","inventory":null},{"id":7,"pirUrl":"https:barbecue-img.oss-cn-hangzhou.aliyuncs.comproduct879fdf10a6a247479f4649cdeb65145a.jpg","name":"鸡肉","desc":null,"price":"100.00","unitName":"箱","status":"","businessId":46,"businessType":1,"priceDesc":"￥100.00箱","salesDolumeOrReserveDesc":"月销:50","inventory":"库存:1000"}],"hasPrePage":false,"hasNextPage":false}
//     */
//
//    public DataBean data;
//
//    public static class DataBean {
//        /**
//         * pageNum : 1
//         * pageSize : 20
//         * startRow : 0
//         * pages : 1
//         * total : 4
//         * list : [{"id":3,"pirUrl":"https:barbecue-img.oss-cn-hangzhou.aliyuncs.commasterworker0c658325bb0b402ea3c280bd3d931dbe.jpg","name":"师傅","desc":"好师傅","price":"500.00","unitName":"天","status":null,"businessId":1,"businessType":5,"priceDesc":"￥500.00天","salesDolumeOrReserveDesc":"预订量:0","inventory":null},{"id":4,"pirUrl":"https:barbecue-img.oss-cn-hangzhou.aliyuncs.comproduct9a3bb39678504d72bf8ad98035413d3d.jpg","name":"设备1","desc":"规格1规格1说明","price":"100.00","unitName":"天","status":null,"businessId":1,"businessType":6,"priceDesc":"￥100.00天","salesDolumeOrReserveDesc":"剩余:0","inventory":null},{"id":5,"pirUrl":"https:barbecue-img.oss-cn-hangzhou.aliyuncs.commasterworker0c658325bb0b402ea3c280bd3d931dbe.jpg","name":"风景区1场地1","desc":"介绍","price":"100.00","unitName":"天","status":null,"businessId":1,"businessType":4,"priceDesc":"￥100.00天","salesDolumeOrReserveDesc":"预订量:100","inventory":null},{"id":7,"pirUrl":"https:barbecue-img.oss-cn-hangzhou.aliyuncs.comproduct879fdf10a6a247479f4649cdeb65145a.jpg","name":"鸡肉","desc":null,"price":"100.00","unitName":"箱","status":"","businessId":46,"businessType":1,"priceDesc":"￥100.00箱","salesDolumeOrReserveDesc":"月销:50","inventory":"库存:1000"}]
//         * hasPrePage : false
//         * hasNextPage : false
//         */
//
//        public int pageNum;
//        public int pageSize;
//        public int startRow;
//        public int pages;
//        public int total;
//        public boolean hasPrePage;
//        public boolean hasNextPage;
//        public List<ListBean> list;
//
//        public static class ListBean {
//            @Override
//            public String toString() {
//                return "ListBean{" +
//                        "id=" + id +
//                        ", pirUrl='" + pirUrl + '\'' +
//                        ", name='" + name + '\'' +
//                        ", desc='" + desc + '\'' +
//                        ", price='" + price + '\'' +
//                        ", unitName='" + unitName + '\'' +
//                        ", status='" + status + '\'' +
//                        ", businessId=" + businessId +
//                        ", businessType=" + businessType +
//                        ", priceDesc='" + priceDesc + '\'' +
//                        ", salesDolumeOrReserveDesc='" + salesDolumeOrReserveDesc + '\'' +
//                        ", inventory='" + inventory + '\'' +
//                        '}';
//            }
//
//            /**
//             * id : 3
//             * pirUrl : https:barbecue-img.oss-cn-hangzhou.aliyuncs.commasterworker0c658325bb0b402ea3c280bd3d931dbe.jpg
//             * name : 师傅
//             * desc : 好师傅
//             * price : 500.00
//             * unitName : 天
//             * status : null
//             * businessId : 1
//             * businessType : 5
//             * priceDesc : ￥500.00天
//             * salesDolumeOrReserveDesc : 预订量:0
//             * inventory : null
//             */
//
//            public int id;
//            public String pirUrl;
//            public String name;
//            public String desc;
//            public String price;
//            public String unitName;
//            public String status;
//            public int businessId;
//            public int businessType;
//            public String priceDesc;
//            public String salesDolumeOrReserveDesc;
//            public String inventory;
//        }
//    }
}
