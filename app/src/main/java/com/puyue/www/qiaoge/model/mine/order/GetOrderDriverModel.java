package com.puyue.www.qiaoge.model.mine.order;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/28
 */
public class GetOrderDriverModel{


    /**
     * code : 1
     * message : 成功
     * data : {"orderStatus":14,"payTime":"2019-05-13 15:40:50","receiveTime":"2019-05-22 10:17:28","sendTime":null,"addressDetail":"浙江省杭州市滨江区信诚路99号","driverName":"刘亮","driverPhone":"18365284956","longitude":null,"latitude":null,"locationTime":null,"userLocationVOList":[{"userLocationId":null,"longitude":"120.16965","latitude":"30.302245","locationTime":"2019-06-12 09:00:21","type":"1"},{"userLocationId":null,"longitude":"120.16233","latitude":"30.302235","locationTime":"2019-06-12 09:58:21","type":"1"},{"userLocationId":null,"longitude":"120.16730","latitude":"30.302235","locationTime":"2019-06-12 09:59:21","type":"1"},{"userLocationId":null,"longitude":"120.17245","latitude":"30.302295","locationTime":"2019-06-12 14:01:21","type":"2"},{"userLocationId":null,"longitude":"120.17395","latitude":"30.302395","locationTime":"2019-06-12 14:02:21","type":"2"},{"userLocationId":null,"longitude":"120.17515","latitude":"30.302495","locationTime":"2019-06-12 14:03:21","type":"2"},{"userLocationId":null,"longitude":"120.17655","latitude":"30.302495","locationTime":"2019-06-12 14:04:21","type":"2"},{"userLocationId":null,"longitude":"120.17965","latitude":"30.302495","locationTime":"2019-06-12 14:05:21","type":"2"},{"userLocationId":null,"longitude":"120.18285","latitude":"30.302495","locationTime":"2019-06-12 14:06:21","type":"2"},{"userLocationId":null,"longitude":"120.18485","latitude":"30.302495","locationTime":"2019-06-12 14:07:21","type":"2"},{"userLocationId":null,"longitude":"120.18585","latitude":"30.302495","locationTime":"2019-06-12 14:08:21","type":"2"},{"userLocationId":null,"longitude":"120.18685","latitude":"30.302495","locationTime":"2019-06-12 14:09:21","type":"2"},{"userLocationId":null,"longitude":"120.19625","latitude":"30.302395","locationTime":"2019-06-12 14:10:21","type":"2"}]}
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
         * orderStatus : 14
         * payTime : 2019-05-13 15:40:50
         * receiveTime : 2019-05-22 10:17:28
         * sendTime : null
         * addressDetail : 浙江省杭州市滨江区信诚路99号
         * driverName : 刘亮
         * driverPhone : 18365284956
         * longitude : null
         * latitude : null
         * locationTime : null
         * userLocationVOList : [{"userLocationId":null,"longitude":"120.16965","latitude":"30.302245","locationTime":"2019-06-12 09:00:21","type":"1"},{"userLocationId":null,"longitude":"120.16233","latitude":"30.302235","locationTime":"2019-06-12 09:58:21","type":"1"},{"userLocationId":null,"longitude":"120.16730","latitude":"30.302235","locationTime":"2019-06-12 09:59:21","type":"1"},{"userLocationId":null,"longitude":"120.17245","latitude":"30.302295","locationTime":"2019-06-12 14:01:21","type":"2"},{"userLocationId":null,"longitude":"120.17395","latitude":"30.302395","locationTime":"2019-06-12 14:02:21","type":"2"},{"userLocationId":null,"longitude":"120.17515","latitude":"30.302495","locationTime":"2019-06-12 14:03:21","type":"2"},{"userLocationId":null,"longitude":"120.17655","latitude":"30.302495","locationTime":"2019-06-12 14:04:21","type":"2"},{"userLocationId":null,"longitude":"120.17965","latitude":"30.302495","locationTime":"2019-06-12 14:05:21","type":"2"},{"userLocationId":null,"longitude":"120.18285","latitude":"30.302495","locationTime":"2019-06-12 14:06:21","type":"2"},{"userLocationId":null,"longitude":"120.18485","latitude":"30.302495","locationTime":"2019-06-12 14:07:21","type":"2"},{"userLocationId":null,"longitude":"120.18585","latitude":"30.302495","locationTime":"2019-06-12 14:08:21","type":"2"},{"userLocationId":null,"longitude":"120.18685","latitude":"30.302495","locationTime":"2019-06-12 14:09:21","type":"2"},{"userLocationId":null,"longitude":"120.19625","latitude":"30.302395","locationTime":"2019-06-12 14:10:21","type":"2"}]
         */

        private int orderStatus;
        private String payTime;
        private String receiveTime;
        private String sendTime;
        private String addressDetail;
        private String driverName;
        private String driverPhone;
        private Object longitude;
        private Object latitude;
        private Object locationTime;
        private List<UserLocationVOListBean> userLocationVOList;

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        private String finishTime;
        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverPhone() {
            return driverPhone;
        }

        public void setDriverPhone(String driverPhone) {
            this.driverPhone = driverPhone;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLocationTime() {
            return locationTime;
        }

        public void setLocationTime(Object locationTime) {
            this.locationTime = locationTime;
        }

        public List<UserLocationVOListBean> getUserLocationVOList() {
            return userLocationVOList;
        }

        public void setUserLocationVOList(List<UserLocationVOListBean> userLocationVOList) {
            this.userLocationVOList = userLocationVOList;
        }

        public static class UserLocationVOListBean {
            /**
             * userLocationId : null
             * longitude : 120.16965
             * latitude : 30.302245
             * locationTime : 2019-06-12 09:00:21
             * type : 1
             */

            private Object userLocationId;
            private String longitude;
            private String latitude;
            private String locationTime;
            private String type;

            public Object getUserLocationId() {
                return userLocationId;
            }

            public void setUserLocationId(Object userLocationId) {
                this.userLocationId = userLocationId;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLocationTime() {
                return locationTime;
            }

            public void setLocationTime(String locationTime) {
                this.locationTime = locationTime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
