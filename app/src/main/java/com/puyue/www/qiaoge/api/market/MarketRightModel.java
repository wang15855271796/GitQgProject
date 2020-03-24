package com.puyue.www.qiaoge.api.market;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

public class MarketRightModel {

    private int code;
    private String message;
    private DataBean data;
    private boolean error;
    private boolean success;

    @Override
    public String toString() {
        return "MarketRightModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", error=" + error +
                ", success=" + success +
                '}';
    }

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

        private ProdClassifyBean prodClassify;
        private BrandProdBean brandProd;

        public ProdClassifyBean getProdClassify() {
            return prodClassify;
        }

        public void setProdClassify(ProdClassifyBean prodClassify) {
            this.prodClassify = prodClassify;
        }

        public BrandProdBean getBrandProd() {
            return brandProd;
        }

        public void setBrandProd(BrandProdBean brandProd) {
            this.brandProd = brandProd;
        }

        public static class BrandProdBean {

            private int pageNum;
            private int pageSize;
            private int startRow;
            private int pages;
            private int total;
            private boolean hasPrePage;
            private boolean hasNextPage;
            private List<ListBeanX> list;


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

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            public static class ListBeanX{
                private String brandName;
                private ProdClassifyBean prodClassify;

                public String getBrandName() {
                    return brandName;
                }

                public void setBrandName(String brandName) {
                    this.brandName = brandName;
                }

                public ProdClassifyBean getProdClassify() {
                    return prodClassify;
                }

                public void setProdClassify(ProdClassifyBean prodClassify) {
                    this.prodClassify = prodClassify;
                }

                public static class ProdClassifyBean {

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
                        /**
                         * type : 批发
                         * activeId : null
                         * productMainId : 4631
                         * productId : 4549
                         * productName : 成本小数5
                         * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
                         * flag : 0
                         * typeUrl : null
                         * spec : null
                         * salesVolume : 销量：424001
                         * minMaxPrice : ￥***
                         * specialOffer :
                         * prodSpecs : null
                         * inventory : null
                         * prodPrices : null
                         */

                        private String type;
                        private int activeId;
                        private int productMainId;
                        private int productId;
                        private String productName;
                        private String defaultPic;
                        private int flag;
                        private Object typeUrl;
                        private Object spec;
                        private String salesVolume;
                        private String minMaxPrice;
                        private String specialOffer;
                        private Object prodSpecs;
                        private Object inventory;
                        private Object prodPrices;

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public int getActiveId() {
                            return activeId;
                        }

                        public void setActiveId(int activeId) {
                            this.activeId = activeId;
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

                        public Object getTypeUrl() {
                            return typeUrl;
                        }

                        public void setTypeUrl(Object typeUrl) {
                            this.typeUrl = typeUrl;
                        }

                        public Object getSpec() {
                            return spec;
                        }

                        public void setSpec(Object spec) {
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

                        public Object getProdSpecs() {
                            return prodSpecs;
                        }

                        public void setProdSpecs(Object prodSpecs) {
                            this.prodSpecs = prodSpecs;
                        }

                        public Object getInventory() {
                            return inventory;
                        }

                        public void setInventory(Object inventory) {
                            this.inventory = inventory;
                        }

                        public Object getProdPrices() {
                            return prodPrices;
                        }

                        public void setProdPrices(Object prodPrices) {
                            this.prodPrices = prodPrices;
                        }
                    }
                }
            }
        }

        public static class ProdClassifyBean {
            @Override
            public String toString() {
                return "ProdClassifyBean{" +
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
             * pageSize : 6
             * startRow : 0
             * pages : 19
             * total : 113
             * list : [{"type":"批发","productMainId":3,"productId":4540,"productName":"四大四大","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"ddd","salesVolume":"17","minMaxPrice":"￥***","specialOffer":"","prodSpecs":[{"productId":4540,"spec":"ddd"}],"inventory":"985包","prodPrices":null},{"type":"批发","productMainId":310,"productId":310,"productName":"【馍小鲜】香辣猪肉肉夹馍（1.1kg*10个/包）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"typeUrl":null,"spec":"1.1kg*10个*6包/箱","salesVolume":"43","minMaxPrice":"￥***","specialOffer":"","prodSpecs":[{"productId":310,"spec":"1.1kg*10个*6包/箱"}],"inventory":"2箱5包","prodPrices":null},{"type":"批发","productMainId":218,"productId":218,"productName":"【哈东金】速冻有机鲜糯白玉米（约40只/箱）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"typeUrl":null,"spec":"约40个/箱-9kg/箱","salesVolume":"605","minMaxPrice":"￥***","specialOffer":"","prodSpecs":[{"productId":218,"spec":"约40个/箱-9kg/箱"}],"inventory":"30箱","prodPrices":null},{"type":"批发","productMainId":279,"productId":279,"productName":"【雄丰】仿龙虾球串（360g/包）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":0,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/8475dce475a94923a1528fc2da4a8367.png","spec":"360g*20包/箱","salesVolume":"605","minMaxPrice":"￥***","specialOffer":"","prodSpecs":[{"productId":279,"spec":"360g*20包/箱"}],"inventory":"0箱","prodPrices":null},{"type":"批发","productMainId":308,"productId":308,"productName":"【林海饼缘】白菜猪肉馅（672g*8个/包）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"typeUrl":null,"spec":"672g*8个*12包/箱","salesVolume":"131","minMaxPrice":"￥***","specialOffer":"","prodSpecs":[{"productId":308,"spec":"672g*8个*12包/箱"}],"inventory":"52箱","prodPrices":null},{"type":"批发","productMainId":313,"productId":313,"productName":"cg","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"123","salesVolume":"17","minMaxPrice":"￥***","specialOffer":"","prodSpecs":[{"productId":313,"spec":"123"}],"inventory":"84箱","prodPrices":null}]
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
                            "type='" + type + '\'' +
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
                            ", prodPrices=" + prodPrices +
                            ", prodSpecs=" + prodSpecs +
                            '}';
                }

                /**
                 * type : 批发
                 * productMainId : 3
                 * productId : 4540
                 * productName : 四大四大
                 * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
                 * flag : 1
                 * typeUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png
                 * spec : ddd
                 * salesVolume : 17
                 * minMaxPrice : ￥***
                 * specialOffer :
                 * prodSpecs : [{"productId":4540,"spec":"ddd"}]
                 * inventory : 985包
                 * prodPrices : null
                 */
                int activeId;
                private int businessType;
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
                private List<ProdPricesBean> prodPrices;
                private List<ProdSpecsBean> prodSpecs;

                public int getActiveId() {
                    return activeId;
                }

                public void setActiveId(int activeId) {
                    this.activeId = activeId;
                }

                public int getBusinessType() {
                    return businessType;
                }

                public void setBusinessType(int businessType) {
                    this.businessType = businessType;
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

                public List<ProdPricesBean> getProdPrices() {
                    return prodPrices;
                }

                public void setProdPrices(List<ProdPricesBean> prodPrices) {
                    this.prodPrices = prodPrices;
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


                public static class ProdPricesBean {
                    /**
                     * price : ￥45/箱
                     * oldPrice :
                     * priceId : 1324
                     * productUnit : 530
                     */
                    private int cartNum;
                    private String price;
                    private String oldPrice;
                    private int priceId;
                    private int productUnit;
                    private String unitDesc;

                    public int getCartNum() {
                        return cartNum;
                    }

                    public void setCartNum(int cartNum) {
                        this.cartNum = cartNum;
                    }

                    public String getUnitDesc() {
                        return unitDesc;
                    }

                    public void setUnitDesc(String unitDesc) {
                        this.unitDesc = unitDesc;
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
                }


                public static class ProdSpecsBean {
                    @Override
                    public String toString() {
                        return "ProdSpecsBean{" +
                                "productId=" + productId +
                                ", spec='" + spec + '\'' +
                                '}';
                    }

                    /**
                     * productId : 4540
                     * spec : ddd
                     */
                    int activeId;
                    private int productId;
                    private String spec;
                    int prodDeduct;

                    public int getProdDeduct() {
                        return prodDeduct;
                    }

                    public void setProdDeduct(int prodDeduct) {
                        this.prodDeduct = prodDeduct;
                    }

                    public int getActiveId() {
                        return activeId;
                    }

                    public void setActiveId(int activeId) {
                        this.activeId = activeId;
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
            }
        }
    }









    //````````````````````````````````````````````````````````````````````````````````````````````````````````````


}
