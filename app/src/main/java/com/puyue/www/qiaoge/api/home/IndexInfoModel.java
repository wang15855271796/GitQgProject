package com.puyue.www.qiaoge.api.home;

import java.util.List;

/**
 * Created by ${王涛} on 2020/1/4
 */
public class IndexInfoModel {


    /**
     * code : 1
     * message : 成功
     * data : {"address":"杭州市","noticeNum":1,"banners":[{"showType":1,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//14b6db4b97c24089b481a8b511a5460d.jpg","linkSrc":"www.baidu.com","detailPic":null,"prodPage":null,"businessId":null,"businessType":null},{"showType":4,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//af841f2d7faf4f9d870eee55f0f935ec.jpg","linkSrc":null,"detailPic":null,"prodPage":null,"businessId":4694,"businessType":1},{"showType":3,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//a500348e2c2843bd811512c29fca592d.jpg","linkSrc":null,"detailPic":null,"prodPage":"killProd","businessId":null,"businessType":null},{"showType":2,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//12a43a3775cc49a3bdc6b63391c176cc.jpg","linkSrc":null,"detailPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//74175d85bcec47a8ab8a9a885a00eaf3.jpg","prodPage":null,"businessId":null,"businessType":null}],"icons":[{"configCode":"commonBuy","configDesc":"常用清单","remark":null,"url":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/icon_listed%402x.png","type":null},{"configCode":"hotProd","configDesc":"热卖商品","remark":null,"url":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/icon_hot%402x.png","type":null},{"configCode":"reductProd","configDesc":"降价商品","remark":null,"url":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/icon__kill%402x.png","type":null},{"configCode":"messageNews","configDesc":"行业资讯","remark":null,"url":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/icon_NEW%402x.png","type":null}],"spikeNum":4,"teamNum":2,"specialNum":4,"classifyTitle":"精选分类","classifyDesc":"Selection Classification","otherInfo":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/image/otherinfo.png","classifyList":[{"title":"js123","img":"","id":100},{"title":"炸鸡汉堡区","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/bd7e9e4601a54c1487d5b31ddc277955.png","id":1},{"title":"炸鸡汉堡","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/a7e3ca2788f448559d2e3b2b46fa81bf.png","id":4},{"title":"麻辣烫","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/5c73c11646ba452db6ec8ebfca7d837c.png","id":6},{"title":"早餐","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/3ca63bcef18649bf86c608eda2336062.png","id":8},{"title":"便当","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/1abee5c3009e4ff39d84b53266709713.png","id":10}],"addAddress":0,"userIsBuy":0}
     * success : true
     * error : false
     */

    private int code;
    private String message;
    private DataBean data;
    private boolean success;
    private boolean error;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public static class DataBean {
        /**
         * address : 杭州市
         * noticeNum : 1
         * banners : [{"showType":1,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//14b6db4b97c24089b481a8b511a5460d.jpg","linkSrc":"www.baidu.com","detailPic":null,"prodPage":null,"businessId":null,"businessType":null},{"showType":4,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//af841f2d7faf4f9d870eee55f0f935ec.jpg","linkSrc":null,"detailPic":null,"prodPage":null,"businessId":4694,"businessType":1},{"showType":3,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//a500348e2c2843bd811512c29fca592d.jpg","linkSrc":null,"detailPic":null,"prodPage":"killProd","businessId":null,"businessType":null},{"showType":2,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//12a43a3775cc49a3bdc6b63391c176cc.jpg","linkSrc":null,"detailPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//74175d85bcec47a8ab8a9a885a00eaf3.jpg","prodPage":null,"businessId":null,"businessType":null}]
         * icons : [{"configCode":"commonBuy","configDesc":"常用清单","remark":null,"url":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/icon_listed%402x.png","type":null},{"configCode":"hotProd","configDesc":"热卖商品","remark":null,"url":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/icon_hot%402x.png","type":null},{"configCode":"reductProd","configDesc":"降价商品","remark":null,"url":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/icon__kill%402x.png","type":null},{"configCode":"messageNews","configDesc":"行业资讯","remark":null,"url":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/icon_NEW%402x.png","type":null}]
         * spikeNum : 4
         * teamNum : 2
         * specialNum : 4
         * classifyTitle : 精选分类
         * classifyDesc : Selection Classification
         * otherInfo : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/image/otherinfo.png
         * classifyList : [{"title":"js123","img":"","id":100},{"title":"炸鸡汉堡区","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/bd7e9e4601a54c1487d5b31ddc277955.png","id":1},{"title":"炸鸡汉堡","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/a7e3ca2788f448559d2e3b2b46fa81bf.png","id":4},{"title":"麻辣烫","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/5c73c11646ba452db6ec8ebfca7d837c.png","id":6},{"title":"早餐","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/3ca63bcef18649bf86c608eda2336062.png","id":8},{"title":"便当","img":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/scenicspot/1abee5c3009e4ff39d84b53266709713.png","id":10}]
         * addAddress : 0
         * userIsBuy : 0
         */

        private String address;
        private int noticeNum;
        private int spikeNum;
        private int teamNum;
        private int specialNum;
        private String classifyTitle;
        private String classifyDesc;
        private String otherInfo;
        private int addAddress;
        private int userIsBuy;
        private List<BannersBean> banners;
        private List<IconsBean> icons;
        private List<ClassifyListBean> classifyList;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getNoticeNum() {
            return noticeNum;
        }

        public void setNoticeNum(int noticeNum) {
            this.noticeNum = noticeNum;
        }

        public int getSpikeNum() {
            return spikeNum;
        }

        public void setSpikeNum(int spikeNum) {
            this.spikeNum = spikeNum;
        }

        public int getTeamNum() {
            return teamNum;
        }

        public void setTeamNum(int teamNum) {
            this.teamNum = teamNum;
        }

        public int getSpecialNum() {
            return specialNum;
        }

        public void setSpecialNum(int specialNum) {
            this.specialNum = specialNum;
        }

        public String getClassifyTitle() {
            return classifyTitle;
        }

        public void setClassifyTitle(String classifyTitle) {
            this.classifyTitle = classifyTitle;
        }

        public String getClassifyDesc() {
            return classifyDesc;
        }

        public void setClassifyDesc(String classifyDesc) {
            this.classifyDesc = classifyDesc;
        }

        public String getOtherInfo() {
            return otherInfo;
        }

        public void setOtherInfo(String otherInfo) {
            this.otherInfo = otherInfo;
        }

        public int getAddAddress() {
            return addAddress;
        }

        public void setAddAddress(int addAddress) {
            this.addAddress = addAddress;
        }

        public int getUserIsBuy() {
            return userIsBuy;
        }

        public void setUserIsBuy(int userIsBuy) {
            this.userIsBuy = userIsBuy;
        }

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public List<IconsBean> getIcons() {
            return icons;
        }

        public void setIcons(List<IconsBean> icons) {
            this.icons = icons;
        }

        public List<ClassifyListBean> getClassifyList() {
            return classifyList;
        }

        public void setClassifyList(List<ClassifyListBean> classifyList) {
            this.classifyList = classifyList;
        }

        public static class BannersBean {
            /**
             * showType : 1
             * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//14b6db4b97c24089b481a8b511a5460d.jpg
             * linkSrc : www.baidu.com
             * detailPic : null
             * prodPage : null
             * businessId : null
             * businessType : null
             */

            private int showType;
            private String defaultPic;
            private String linkSrc;
            private Object detailPic;
            private Object prodPage;
            private Object businessId;
            private Object businessType;

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

            public String getLinkSrc() {
                return linkSrc;
            }

            public void setLinkSrc(String linkSrc) {
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

            public Object getBusinessId() {
                return businessId;
            }

            public void setBusinessId(Object businessId) {
                this.businessId = businessId;
            }

            public Object getBusinessType() {
                return businessType;
            }

            public void setBusinessType(Object businessType) {
                this.businessType = businessType;
            }
        }

        public static class IconsBean {
            /**
             * configCode : commonBuy
             * configDesc : 常用清单
             * remark : null
             * url : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/icon_listed%402x.png
             * type : null
             */

            private String configCode;
            private String configDesc;
            private Object remark;
            private String url;
            private Object type;

            public String getConfigCode() {
                return configCode;
            }

            public void setConfigCode(String configCode) {
                this.configCode = configCode;
            }

            public String getConfigDesc() {
                return configDesc;
            }

            public void setConfigDesc(String configDesc) {
                this.configDesc = configDesc;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }
        }

        public static class ClassifyListBean {
            /**
             * title : js123
             * img :
             * id : 100
             */

            private String title;
            private String img;
            private int id;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}