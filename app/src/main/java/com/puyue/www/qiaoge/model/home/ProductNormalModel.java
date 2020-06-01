package com.puyue.www.qiaoge.model.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${王涛} on 2019/11/4
 */
public class ProductNormalModel implements Serializable {


    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":6,"startRow":0,"pages":5,"total":29,"list":[{"type":"批发","productMainId":4561,"productId":345,"productName":"090901","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":0,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/8475dce475a94923a1528fc2da4a8367.png","spec":"12/","salesVolume":"0","minMaxPrice":"￥11","specialOffer":"","prodSpecs":[{"productId":345,"spec":"12"}],"inventory":"0c","prodPrices":[{"price":"￥11/c","oldPrice":"","priceId":1406,"productUnit":649,"unitDesc":""}]},{"type":"批发","productMainId":4570,"productId":313,"productName":"0910052","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"1232/","salesVolume":"18","minMaxPrice":"￥3","specialOffer":"","prodSpecs":[{"productId":313,"spec":"1232"}],"inventory":"83箱","prodPrices":[{"price":"￥3/箱","oldPrice":"","priceId":1469,"productUnit":517,"unitDesc":"16e/箱"}]},{"type":"批发","productMainId":45,"productId":45,"productName":"【创歌】五花肉（20串/包）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/b1b515a3b3754ffc8b438c5eb5ba51bb.jpg","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"20串*10包/箱/","salesVolume":"198","minMaxPrice":"￥29","specialOffer":"","prodSpecs":[{"productId":45,"spec":"20串*10包/箱"}],"inventory":"959箱","prodPrices":[{"price":"￥29/箱","oldPrice":"","priceId":992,"productUnit":53,"unitDesc":""}]},{"type":"批发","productMainId":41,"productId":41,"productName":"【意润】冻巴沙鱼片","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/17dee6dc94b24aad8c0f9b88fd49afe5.jpg","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"300/400/","salesVolume":"252","minMaxPrice":"￥26","specialOffer":"","prodSpecs":[{"productId":41,"spec":"300/400"}],"inventory":"4402箱","prodPrices":[{"price":"￥26/箱","oldPrice":"","priceId":990,"productUnit":49,"unitDesc":""}]},{"type":"批发","productMainId":40,"productId":40,"productName":"【鑫鹏】干熟肠（10kg/箱）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/a3b9ff1e8a5e4b8e8e2c9c4acb6011a6.jpg","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"1*10kg/箱/","salesVolume":"150","minMaxPrice":"￥26","specialOffer":"","prodSpecs":[{"productId":40,"spec":"1*10kg/箱"}],"inventory":"1458箱","prodPrices":[{"price":"￥26/箱","oldPrice":"","priceId":989,"productUnit":48,"unitDesc":""}]},{"type":"批发","productMainId":39,"productId":39,"productName":"【锦诺尔】虎皮凤爪（9.5kg）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/375903c45c66425cb897207df20b44a6.jpg","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"1*9.5/","salesVolume":"133","minMaxPrice":"￥24","specialOffer":"","prodSpecs":[{"productId":39,"spec":"1*9.5"}],"inventory":"975箱","prodPrices":[{"price":"￥24/箱","oldPrice":"","priceId":988,"productUnit":47,"unitDesc":""}]}],"hasPrePage":false,"hasNextPage":true}
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

    @Override
    public String toString() {
        return "ProductNormalModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", error=" + error +
                ", success=" + success +
                '}';
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

    public static class DataBean implements Serializable{
        /**
         * pageNum : 1
         * pageSize : 6
         * startRow : 0
         * pages : 5
         * total : 29
         * list : [{"type":"批发","productMainId":4561,"productId":345,"productName":"090901","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":0,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/8475dce475a94923a1528fc2da4a8367.png","spec":"12/","salesVolume":"0","minMaxPrice":"￥11","specialOffer":"","prodSpecs":[{"productId":345,"spec":"12"}],"inventory":"0c","prodPrices":[{"price":"￥11/c","oldPrice":"","priceId":1406,"productUnit":649,"unitDesc":""}]},{"type":"批发","productMainId":4570,"productId":313,"productName":"0910052","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"1232/","salesVolume":"18","minMaxPrice":"￥3","specialOffer":"","prodSpecs":[{"productId":313,"spec":"1232"}],"inventory":"83箱","prodPrices":[{"price":"￥3/箱","oldPrice":"","priceId":1469,"productUnit":517,"unitDesc":"16e/箱"}]},{"type":"批发","productMainId":45,"productId":45,"productName":"【创歌】五花肉（20串/包）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/b1b515a3b3754ffc8b438c5eb5ba51bb.jpg","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"20串*10包/箱/","salesVolume":"198","minMaxPrice":"￥29","specialOffer":"","prodSpecs":[{"productId":45,"spec":"20串*10包/箱"}],"inventory":"959箱","prodPrices":[{"price":"￥29/箱","oldPrice":"","priceId":992,"productUnit":53,"unitDesc":""}]},{"type":"批发","productMainId":41,"productId":41,"productName":"【意润】冻巴沙鱼片","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/17dee6dc94b24aad8c0f9b88fd49afe5.jpg","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"300/400/","salesVolume":"252","minMaxPrice":"￥26","specialOffer":"","prodSpecs":[{"productId":41,"spec":"300/400"}],"inventory":"4402箱","prodPrices":[{"price":"￥26/箱","oldPrice":"","priceId":990,"productUnit":49,"unitDesc":""}]},{"type":"批发","productMainId":40,"productId":40,"productName":"【鑫鹏】干熟肠（10kg/箱）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/a3b9ff1e8a5e4b8e8e2c9c4acb6011a6.jpg","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"1*10kg/箱/","salesVolume":"150","minMaxPrice":"￥26","specialOffer":"","prodSpecs":[{"productId":40,"spec":"1*10kg/箱"}],"inventory":"1458箱","prodPrices":[{"price":"￥26/箱","oldPrice":"","priceId":989,"productUnit":48,"unitDesc":""}]},{"type":"批发","productMainId":39,"productId":39,"productName":"【锦诺尔】虎皮凤爪（9.5kg）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/375903c45c66425cb897207df20b44a6.jpg","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"1*9.5/","salesVolume":"133","minMaxPrice":"￥24","specialOffer":"","prodSpecs":[{"productId":39,"spec":"1*9.5"}],"inventory":"975箱","prodPrices":[{"price":"￥24/箱","oldPrice":"","priceId":988,"productUnit":47,"unitDesc":""}]}]
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
            /**
             * type : 批发
             * productMainId : 4561
             * productId : 345
             * productName : 090901
             * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
             * flag : 0
             * typeUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/8475dce475a94923a1528fc2da4a8367.png
             * spec : 12/
             * salesVolume : 0
             * minMaxPrice : ￥11
             * specialOffer :
             * prodSpecs : [{"productId":345,"spec":"12"}]
             * inventory : 0c
             * prodPrices : [{"price":"￥11/c","oldPrice":"","priceId":1406,"productUnit":649,"unitDesc":""}]
             */


            String deductAmount;
            private String type;
            private int productMainId;
            private int productId;
            private String productName;
            private String defaultPic;
            private int flag;
            private String typeUrl;
            private String spec;
            private String salesVolume;
            private String minMaxPrice;
            private String specialOffer;
            private String inventory;
            private List<ProdSpecsBean> prodSpecs;
            private List<ProdPricesBean> prodPrices;

            @Override
            public String toString() {
                return "ListBean{" +
                        "deductAmount='" + deductAmount + '\'' +
                        ", type='" + type + '\'' +
                        ", productMainId=" + productMainId +
                        ", productId=" + productId +
                        ", productName='" + productName + '\'' +
                        ", defaultPic='" + defaultPic + '\'' +
                        ", flag=" + flag +
                        ", typeUrl='" + typeUrl + '\'' +
                        ", spec='" + spec + '\'' +
                        ", salesVolume='" + salesVolume + '\'' +
                        ", minMaxPrice='" + minMaxPrice + '\'' +
                        ", specialOffer='" + specialOffer + '\'' +
                        ", inventory='" + inventory + '\'' +
                        ", prodSpecs=" + prodSpecs +
                        ", prodPrices=" + prodPrices +
                        '}';
            }

            public String getDeductAmount() {
                return deductAmount;
            }

            public void setDeductAmount(String deductAmount) {
                this.deductAmount = deductAmount;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

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

            public String getDefaultPic() {
                return defaultPic;
            }

            public void setDefaultPic(String defaultPic) {
                this.defaultPic = defaultPic;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public String getTypeUrl() {
                return typeUrl;
            }

            public void setTypeUrl(String typeUrl) {
                this.typeUrl = typeUrl;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getSalesVolume() {
                return salesVolume;
            }

            public void setSalesVolume(String salesVolume) {
                this.salesVolume = salesVolume;
            }

            public String getMinMaxPrice() {
                return minMaxPrice;
            }

            public void setMinMaxPrice(String minMaxPrice) {
                this.minMaxPrice = minMaxPrice;
            }

            public String getSpecialOffer() {
                return specialOffer;
            }

            public void setSpecialOffer(String specialOffer) {
                this.specialOffer = specialOffer;
            }

            public String getInventory() {
                return inventory;
            }

            public void setInventory(String inventory) {
                this.inventory = inventory;
            }

            public List<ProdSpecsBean> getProdSpecs() {
                return prodSpecs;
            }

            public void setProdSpecs(List<ProdSpecsBean> prodSpecs) {
                this.prodSpecs = prodSpecs;
            }

            public List<ProdPricesBean> getProdPrices() {
                return prodPrices;
            }

            public void setProdPrices(List<ProdPricesBean> prodPrices) {
                this.prodPrices = prodPrices;
            }

            public static class ProdSpecsBean {
                /**
                 * productId : 345
                 * spec : 12
                 */

                private int productId;
                private String spec;
                int prodDeduct;

                public int getProdDeduct() {
                    return prodDeduct;
                }

                public void setProdDeduct(int prodDeduct) {
                    this.prodDeduct = prodDeduct;
                }

                public int getProductId() {
                    return productId;
                }

                public void setProductId(int productId) {
                    this.productId = productId;
                }

                public String getSpec() {
                    return spec;
                }

                public void setSpec(String spec) {
                    this.spec = spec;
                }
            }

            public static class ProdPricesBean {
                /**
                 * price : ￥11/c
                 * oldPrice :
                 * priceId : 1406
                 * productUnit : 649
                 * unitDesc :
                 */

                private String price;
                private String oldPrice;
                private int priceId;
                private int productUnit;
                private String unitDesc;
                private int cartNum;

                public int getCartNum() {
                    return cartNum;
                }

                public void setCartNum(int cartNum) {
                    this.cartNum = cartNum;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getOldPrice() {
                    return oldPrice;
                }

                public void setOldPrice(String oldPrice) {
                    this.oldPrice = oldPrice;
                }

                public int getPriceId() {
                    return priceId;
                }

                public void setPriceId(int priceId) {
                    this.priceId = priceId;
                }

                public int getProductUnit() {
                    return productUnit;
                }

                public void setProductUnit(int productUnit) {
                    this.productUnit = productUnit;
                }

                public String getUnitDesc() {
                    return unitDesc;
                }

                public void setUnitDesc(String unitDesc) {
                    this.unitDesc = unitDesc;
                }
            }
        }
    }
}
