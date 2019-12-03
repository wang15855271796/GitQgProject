package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王涛} on 2019/10/9
 */
public class HomeNewRecommendModel {

    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":12,"startRow":0,"pages":3,"total":34,"list":[{"productMainId":4610,"productName":"多规格ios专测","spec":"500G","monthSalesVolume":"282","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/d185a55e408a444cbac0891315caa949.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4607,"productName":"多规格30","spec":"50G","monthSalesVolume":"13","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4606,"productName":"多规格20","spec":"50","monthSalesVolume":"31","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4603,"productName":"多规格1","spec":"600g","monthSalesVolume":"30","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4594,"productName":"多规格6","spec":"500g","monthSalesVolume":"138","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4561,"productName":"090901","spec":"12","monthSalesVolume":"0","flag":0,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4570,"productName":"0910052","spec":"1232","monthSalesVolume":"51","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":45,"productName":"【创歌】五花肉（20串/包）","spec":"20串*10包/箱","monthSalesVolume":"148","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/b1b515a3b3754ffc8b438c5eb5ba51bb.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":41,"productName":"【意润】冻巴沙鱼片","spec":"300/400","monthSalesVolume":"315","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/17dee6dc94b24aad8c0f9b88fd49afe5.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":40,"productName":"【鑫鹏】干熟肠（10kg/箱）","spec":"1*10kg/箱","monthSalesVolume":"278","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/a3b9ff1e8a5e4b8e8e2c9c4acb6011a6.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":39,"productName":"【锦诺尔】虎皮凤爪（9.5kg）","spec":"1*9.5","monthSalesVolume":"275","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/375903c45c66425cb897207df20b44a6.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":38,"productName":"【宁富】麻辣鸡爪（8包/箱）","spec":"1kg*8包","monthSalesVolume":"504","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/038e8c89947b424c9983ca09495578fa.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"}],"hasPrePage":false,"hasNextPage":true}
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private DataBean data;
    private boolean error;
    private boolean success;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "pageNum=" + pageNum +
                    ", pageSize=" + pageSize +
                    ", startRow=" + startRow +
                    ", pages=" + pages +
                    ", total=" + total +
                    ", hasPrePage=" + hasPrePage +
                    ", hasNextPage=" + hasNextPage +
                    ", list=" + list +
                    '}';
        }

        /**
         * pageNum : 1
         * pageSize : 12
         * startRow : 0
         * pages : 3
         * total : 34
         * list : [{"productMainId":4610,"productName":"多规格ios专测","spec":"500G","monthSalesVolume":"282","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/d185a55e408a444cbac0891315caa949.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4607,"productName":"多规格30","spec":"50G","monthSalesVolume":"13","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4606,"productName":"多规格20","spec":"50","monthSalesVolume":"31","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4603,"productName":"多规格1","spec":"600g","monthSalesVolume":"30","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4594,"productName":"多规格6","spec":"500g","monthSalesVolume":"138","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4561,"productName":"090901","spec":"12","monthSalesVolume":"0","flag":0,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":4570,"productName":"0910052","spec":"1232","monthSalesVolume":"51","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":45,"productName":"【创歌】五花肉（20串/包）","spec":"20串*10包/箱","monthSalesVolume":"148","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/b1b515a3b3754ffc8b438c5eb5ba51bb.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":41,"productName":"【意润】冻巴沙鱼片","spec":"300/400","monthSalesVolume":"315","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/17dee6dc94b24aad8c0f9b88fd49afe5.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":40,"productName":"【鑫鹏】干熟肠（10kg/箱）","spec":"1*10kg/箱","monthSalesVolume":"278","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/a3b9ff1e8a5e4b8e8e2c9c4acb6011a6.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":39,"productName":"【锦诺尔】虎皮凤爪（9.5kg）","spec":"1*9.5","monthSalesVolume":"275","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/375903c45c66425cb897207df20b44a6.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"},{"productMainId":38,"productName":"【宁富】麻辣鸡爪（8包/箱）","spec":"1kg*8包","monthSalesVolume":"504","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/038e8c89947b424c9983ca09495578fa.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","minMaxPrice":"￥***"}]
         * hasPrePage : false
         * hasNextPage : true
         */

        private int pageNum;
        private int pageSize;
        private int startRow;
        private int pages;
        private int total;
        private boolean hasPrePage;
        private boolean hasNextPage;
        private List<ListBean> list;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public boolean isHasPrePage() {
            return hasPrePage;
        }

        public void setHasPrePage(boolean hasPrePage) {
            this.hasPrePage = hasPrePage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            @Override
            public String toString() {
                return "ListBean{" +
                        "productMainId=" + productMainId +
                        ", productName='" + productName + '\'' +
                        ", spec='" + spec + '\'' +
                        ", monthSalesVolume='" + monthSalesVolume + '\'' +
                        ", flag=" + flag +
                        ", imgUrl='" + imgUrl + '\'' +
                        ", collectionIcon='" + collectionIcon + '\'' +
                        ", minMaxPrice='" + minMaxPrice + '\'' +
                        '}';
            }

            /**
             * productMainId : 4610
             * productName : 多规格ios专测
             * spec : 500G
             * monthSalesVolume : 282
             * flag : 1
             * imgUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/d185a55e408a444cbac0891315caa949.png
             * collectionIcon : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png
             * minMaxPrice : ￥***
             */

            private int productMainId;
            private String productName;
            private String spec;
            private String monthSalesVolume;
            private int flag;
            private String imgUrl;
            private String collectionIcon;
            private String minMaxPrice;

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
    }
}
