package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */

public class IndexHomeModel {

    public int code;
    public String message;
    public DataBean data;
    public boolean error;
    public boolean success;

    public static class DataBean {
        public String killTrailDesc;
        public String killTitle;
        public String killDesc;
        public String teamTitle;
        public String teamDesc;
        public String recommendTitle;
        public String recommendDesc;
        public String classicTitle;
        public String classicDesc;
        public String homeBackground;
        public String offerTitle;
        public String offerDesc;


        public String cityName;

        public int shopFlag;
        public String invitationCode;
        public ProdPageBean prodPage;
        public List<BannerListBean> bannerList;
        public List<IconListBean> iconList;
        public List<SpikeListBean> spikeList;
        public List<TeamListBean> teamList;
        public List<ProdListBean> prodList;
        public List<IndexNoticeListBean> indexNoticeList;
        public List<ClassicListBean> classicList;
        public List<OfferListBean> offerList;
        public List<NewSecKillBean> secKillList;



        public static class ProdPageBean {

            public int pageNum;
            public int pageSize;
            public int startRow;
            public int pages;
            public int total;
            public boolean hasPrePage;
            public boolean hasNextPage;
            public List<ListBean> list;

            public static class ListBean {

                public int productId;
                public String productName;
                public String spec;
                public String price;
                public String monthSalesVolume;
                public String inventory;
                public int flag;
                public String imgUrl;
                public String collectionIcon;
                public String showPrice;
                public String type;
                public String productCombinationPriceId;
                public String productUnitName;

            }
        }

        public static class BannerListBean {

            public String bannerUrl;
            public String bannerDetailUrl;
        }

        public static class IconListBean {

            public String configCode;
            public String configDesc;
            public String remark;
            public String url;
        }

        public static class SpikeListBean {

            public int activeId;
            public int productId;
            public String activeTitle;
            public String defaultPic;
            public String oldPrice;
            public String price;
            public String inventory;
            public String monthSalesVolume;
            public Object intrduction;


            public Object originPlace;
            public long currentTime;
            public long startTime;
            public long endTime;
            public String type;
            public String progress;
            public String specification;
            public String origin;
            public String instructions;
            public String combinationPrice;
            public String saleDoneUrl;
            public String saleFinshUrl;
            public boolean flag;
            public boolean available;
            public List<?> picCarousel;
            public List<?> picDetail;
            public List<?> prodList;

        }

        public static class TeamListBean {

            public int activeId;
            public int productId;
            public String activeTitle;
            public String defaultPic;
            public String oldPrice;
            public String price;
            public String inventory;
            public String monthSalesVolume;
            public Object intrduction;
            public Object originPlace;
            public int currentTime;
            public int startTime;
            public int endTime;
            public String type;
            public String progress;
            public String specification;
            public String origin;
            public String instructions;
            public String combinationPrice;
            public String saleDoneUrl;
            public String saleFinshUrl;
            public boolean flag;
            public boolean available;
            public List<?> picCarousel;
            public List<?> picDetail;
            public List<?> prodList;
            public String showPrice;
            public String unitName;
        }

        public static class ProdListBean {

            public int productId;
            public String productName;
            public String spec;
            public String price;
            public String monthSalesVolume;
            public String inventory;
            public int flag;
            public String imgUrl;
            public String collectionIcon;
            public String showPrice;
            public String type;
            public int productCombinationPriceId;
            public String productUnitName;


        }

        public static class IndexNoticeListBean {

            public int id;
            public String noticeTitle;
            public int num;
            public boolean deleteFlag;
            public String gmtCreate;
            public String gmtModify;
            public String createUserId;
            public String updateUserId;
            public String noticeDesc;
        }

        public static class ClassicListBean {
            public String title;
            public String img;
            public int id;

        }

        /**
         * "price": "￥30/盒",
         * "oldPrice": "￥16/盒",
         * "salesVolume": 0,
         * "title": "批发多单位商品",
         * "pic": "https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/3cd5997e6be6481b8f6b1da0bac48183.jpg",
         * "spec": "1箱子=10盒",
         * "activeId": 12,
         * "businessType": 11
         */

        public static class OfferListBean {

            public String price;
            public String oldPrice;

            public String getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(String oldPrice) {
                this.oldPrice = oldPrice;
            }

            public String salesVolume;
            public String title;
            public String pic;
            public String spec;
            public int activeId;
            public String businessType;
            public int soldOut;
            public String flagUrl;
        }


        public static class NewSecKillBean {
            public String currentTime;
            public String startTime;
            public String endTime;
            public int flag;
            public int activeId;
            public String businessType;//2
            public List<SecKill> kills;

            /**
             * "title": "批发商品3",
             * "price": "￥2.32/个",
             * "oldPrice": "",
             * "pic": "https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/54fe0128a3274b19bc46a624b4d5b7ee.jpg",
             * "activeId": 11
             */
            public static class SecKill {
                public String title;
                public String price;
                public String oldPrice;
                public String pic;
                public String sales;
                public int soldOut;
                public int activeId;
                public String flagUrl;
            }
        }


    }
}
