package com.puyue.www.qiaoge.model.mine.wallet;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * @author daff
 * @date 2018/9/22.
 * 备注 钱包页面
 */
public class GetWalletInfoModel extends BaseModel{


    /**
     * data : {"amount":"9266.66","rechargeList":[{"amount":500,"descUrl":"送50元优惠券","detailUrl":"http://www.baidu.com","poolNos":[{"poolNo":"20180914095027"},{"poolNo":"20180913165941"}]},{"amount":300,"descUrl":"送20元优惠券","detailUrl":"http://www.baidu.com","poolNos":[{"poolNo":"20180914095027"}]}],"rechargeRole":{"bannerUrl":"充值规则","bannerDetailUrl":"http://www.baidu.com"}}
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
         * amount : 9266.66
         * rechargeList : [{"amount":500,"descUrl":"送50元优惠券","detailUrl":"http://www.baidu.com","poolNos":[{"poolNo":"20180914095027"},{"poolNo":"20180913165941"}]},{"amount":300,"descUrl":"送20元优惠券","detailUrl":"http://www.baidu.com","poolNos":[{"poolNo":"20180914095027"}]}]
         * rechargeRole : {"bannerUrl":"充值规则","bannerDetailUrl":"http://www.baidu.com"}
         */

        private String amount;
        private RechargeRoleBean rechargeRole;
        private List<RechargeListBean> rechargeList;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public RechargeRoleBean getRechargeRole() {
            return rechargeRole;
        }

        public void setRechargeRole(RechargeRoleBean rechargeRole) {
            this.rechargeRole = rechargeRole;
        }

        public List<RechargeListBean> getRechargeList() {
            return rechargeList;
        }

        public void setRechargeList(List<RechargeListBean> rechargeList) {
            this.rechargeList = rechargeList;
        }

        public static class RechargeRoleBean {
            /**
             * bannerUrl : 充值规则
             * bannerDetailUrl : http://www.baidu.com
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

        public static class RechargeListBean {
            /**
             * amount : 500
             * descUrl : 送50元优惠券
             * detailUrl : http://www.baidu.com
             * poolNos : [{"poolNo":"20180914095027"},{"poolNo":"20180913165941"}]
             */

            private float amount;
            private String descUrl;
            private String detailUrl;
            private List<PoolNosBean> poolNos;

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

            private  boolean flag;

            public float getAmount() {
                return amount;
            }

            public void setAmount(float amount) {
                this.amount = amount;
            }

            public String getDescUrl() {
                return descUrl;
            }

            public void setDescUrl(String descUrl) {
                this.descUrl = descUrl;
            }

            public String getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(String detailUrl) {
                this.detailUrl = detailUrl;
            }

            public List<PoolNosBean> getPoolNos() {
                return poolNos;
            }

            public void setPoolNos(List<PoolNosBean> poolNos) {
                this.poolNos = poolNos;
            }

            public static class PoolNosBean {
                /**
                 * poolNo : 20180914095027
                 */

                private String poolNo;

                public String getPoolNo() {
                    return poolNo;
                }

                public void setPoolNo(String poolNo) {
                    this.poolNo = poolNo;
                }
            }
        }
    }
}
