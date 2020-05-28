package com.puyue.www.qiaoge.model.mine.order;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/5/8.
 */

public class MyOrderNumModel extends BaseModel {


    /**
     * data : {"waitPayment":0,"waitShipments":2,"waitReceiving":0,"waitEvaluate":4,"returnSale":1,"returnEquipment":0,"collect":3,"notice":3,"deductNum":0,"overSoonNum":"2张即将过期","recSendNum":"送3张优惠券","myBanner":[{"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ca32449044e4478b8eb318c83496e62e.png","bannerDetailUrl":"http://shaokao.qiaogeshipin.cn/h5/activity/20180616/19b0284b07de4b9eb57f11f03260f497.html"}],"isVip":false,"vipDesc":"立即满减","activeBanner":{"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/b315b1da81a6417e8479017cbfe4a56e.png","bannerDetailUrl":"http://116.62.67.230:8082/apph5/html/purchaseRecord.html"},"vipCenter":"http://116.62.67.230:8082/apph5/html/purchaseRecord.html"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * waitPayment : 0
         * waitShipments : 2
         * waitReceiving : 0
         * waitEvaluate : 4
         * returnSale : 1
         * returnEquipment : 0
         * collect : 3
         * notice : 3
         * deductNum : 0
         * overSoonNum : 2张即将过期
         * recSendNum : 送3张优惠券
         * myBanner : [{"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ca32449044e4478b8eb318c83496e62e.png","bannerDetailUrl":"http://shaokao.qiaogeshipin.cn/h5/activity/20180616/19b0284b07de4b9eb57f11f03260f497.html"}]
         * isVip : false
         * vipDesc : 立即满减
         * activeBanner : {"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/b315b1da81a6417e8479017cbfe4a56e.png","bannerDetailUrl":"http://116.62.67.230:8082/apph5/html/purchaseRecord.html"}
         * vipCenter : http://116.62.67.230:8082/apph5/html/purchaseRecord.html

         * point : 22487
         * balance : 9836.75
         * commission : 0.00
         * inviteAward : 20元红包
         * commissionUrl : http://116.62.67.230:8082/apph5/html/commissIncome.html?v=2018
         * vipUser : false
         * phone : Hello,176****9519
         * expiredInfo : 您有1张优惠券快过期了，还没有使用哦
         *          * day : 1
         *          * giftNo : 112019061100000848
         */
        private int point;
        private boolean vipUser;
        private String balance;
        private String commission;
        private String inviteAward;
        private String commissionUrl;
        private String expiredInfo;
        private int day;
        private String giftNo;
        private String phone;
        int inviteOpen;
        int subMessage;

        @Override
        public String toString() {
            return "DataBean{" +
                    "point=" + point +
                    ", vipUser=" + vipUser +
                    ", balance='" + balance + '\'' +
                    ", commission='" + commission + '\'' +
                    ", inviteAward='" + inviteAward + '\'' +
                    ", commissionUrl='" + commissionUrl + '\'' +
                    ", expiredInfo='" + expiredInfo + '\'' +
                    ", day=" + day +
                    ", giftNo='" + giftNo + '\'' +
                    ", phone='" + phone + '\'' +
                    ", inviteOpen=" + inviteOpen +
                    ", subMessage=" + subMessage +
                    ", waitPayment=" + waitPayment +
                    ", waitShipments=" + waitShipments +
                    ", waitReceiving=" + waitReceiving +
                    ", waitEvaluate=" + waitEvaluate +
                    ", returnSale=" + returnSale +
                    ", returnEquipment=" + returnEquipment +
                    ", collectNum=" + collectNum +
                    ", notice=" + notice +
                    ", deductNum=" + deductNum +
                    ", overSoonNum='" + overSoonNum + '\'' +
                    ", recSendNum='" + recSendNum + '\'' +
                    ", isVip=" + isVip +
                    ", vipDesc='" + vipDesc + '\'' +
                    ", activeBanner=" + activeBanner +
                    ", vipCenter='" + vipCenter + '\'' +
                    ", myBanner=" + myBanner +
                    '}';
        }

        public int getSubMessage() {
            return subMessage;
        }

        public void setSubMessage(int subMessage) {
            this.subMessage = subMessage;
        }

        public int getInviteOpen() {
            return inviteOpen;
        }

        public void setInviteOpen(int inviteOpen) {
            this.inviteOpen = inviteOpen;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public boolean isVipUser() {
            return vipUser;
        }

        public void setVipUser(boolean vipUser) {
            this.vipUser = vipUser;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getInviteAward() {
            return inviteAward;
        }

        public void setInviteAward(String inviteAward) {
            this.inviteAward = inviteAward;
        }

        public String getCommissionUrl() {
            return commissionUrl;
        }

        public void setCommissionUrl(String commissionUrl) {
            this.commissionUrl = commissionUrl;
        }

        public String getExpiredInfo() {
            return expiredInfo;
        }

        public void setExpiredInfo(String expiredInfo) {
            this.expiredInfo = expiredInfo;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getGiftNo() {
            return giftNo;
        }

        public void setGiftNo(String giftNo) {
            this.giftNo = giftNo;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isVip() {
            return isVip;
        }

        public void setVip(boolean vip) {
            isVip = vip;
        }

        private int waitPayment;
        private int waitShipments;
        private int waitReceiving;
        private int waitEvaluate;
        private int returnSale;
        private int returnEquipment;
        private int collectNum;
        private int notice;
        private int deductNum;
        private String overSoonNum;
        private String recSendNum;
        private boolean isVip;
        private String vipDesc;
        private ActiveBannerBean activeBanner;
        private String vipCenter;
        private List<MyBannerBean> myBanner;

        public int getWaitPayment() {
            return waitPayment;
        }

        public void setWaitPayment(int waitPayment) {
            this.waitPayment = waitPayment;
        }

        public int getWaitShipments() {
            return waitShipments;
        }

        public void setWaitShipments(int waitShipments) {
            this.waitShipments = waitShipments;
        }

        public int getWaitReceiving() {
            return waitReceiving;
        }

        public void setWaitReceiving(int waitReceiving) {
            this.waitReceiving = waitReceiving;
        }

        public int getWaitEvaluate() {
            return waitEvaluate;
        }

        public void setWaitEvaluate(int waitEvaluate) {
            this.waitEvaluate = waitEvaluate;
        }

        public int getReturnSale() {
            return returnSale;
        }

        public void setReturnSale(int returnSale) {
            this.returnSale = returnSale;
        }

        public int getReturnEquipment() {
            return returnEquipment;
        }

        public void setReturnEquipment(int returnEquipment) {
            this.returnEquipment = returnEquipment;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getNotice() {
            return notice;
        }

        public void setNotice(int notice) {
            this.notice = notice;
        }

        public int getDeductNum() {
            return deductNum;
        }

        public void setDeductNum(int deductNum) {
            this.deductNum = deductNum;
        }

        public String getOverSoonNum() {
            return overSoonNum;
        }

        public void setOverSoonNum(String overSoonNum) {
            this.overSoonNum = overSoonNum;
        }

        public String getRecSendNum() {
            return recSendNum;
        }

        public void setRecSendNum(String recSendNum) {
            this.recSendNum = recSendNum;
        }

        public boolean isIsVip() {
            return isVip;
        }

        public void setIsVip(boolean isVip) {
            this.isVip = isVip;
        }

        public String getVipDesc() {
            return vipDesc;
        }

        public void setVipDesc(String vipDesc) {
            this.vipDesc = vipDesc;
        }

        public ActiveBannerBean getActiveBanner() {
            return activeBanner;
        }

        public void setActiveBanner(ActiveBannerBean activeBanner) {
            this.activeBanner = activeBanner;
        }

        public String getVipCenter() {
            return vipCenter;
        }

        public void setVipCenter(String vipCenter) {
            this.vipCenter = vipCenter;
        }

        public List<MyBannerBean> getMyBanner() {
            return myBanner;
        }

        public void setMyBanner(List<MyBannerBean> myBanner) {
            this.myBanner = myBanner;
        }

        public static class ActiveBannerBean {
            /**
             * bannerUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/b315b1da81a6417e8479017cbfe4a56e.png
             * bannerDetailUrl : http://116.62.67.230:8082/apph5/html/purchaseRecord.html
             */

            private String bannerUrl;
            private String bannerDetailUrl;

            public String getBannerUrl() {
                return bannerUrl;
            }

            public void setBannerUrl(String bannerUrl) {
                this.bannerUrl = bannerUrl;
            }

            public String getBannerDetailUrl() {
                return bannerDetailUrl;
            }

            public void setBannerDetailUrl(String bannerDetailUrl) {
                this.bannerDetailUrl = bannerDetailUrl;
            }
        }

        public static class MyBannerBean {
            /**
             * bannerUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ca32449044e4478b8eb318c83496e62e.png
             * bannerDetailUrl : http://shaokao.qiaogeshipin.cn/h5/activity/20180616/19b0284b07de4b9eb57f11f03260f497.html
             */

            private String bannerUrl;
            private String bannerDetailUrl;

            public String getBannerUrl() {
                return bannerUrl;
            }

            public void setBannerUrl(String bannerUrl) {
                this.bannerUrl = bannerUrl;
            }

            public String getBannerDetailUrl() {
                return bannerDetailUrl;
            }

            public void setBannerDetailUrl(String bannerDetailUrl) {
                this.bannerDetailUrl = bannerDetailUrl;
            }
        }
    }
}
