package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王文博} on 2019/8/16
 */
public class SearchListModel {

    /**
     * code : 1
     * message : 成功
     * data : {"list1":[{"key":"","value":"全部"},{"key":"1","value":"购物"},{"key":"2","value":"充值"},{"key":"3","value":"提现"},{"key":"4","value":"退款"},{"key":"6","value":"退款打回"},{"key":"5","value":"佣金充值"},{"key":"7","value":"会员购买"},{"key":"8","value":"会员续费"}],"list2":[{"key":"13600535837","value":"我的"},{"key":"13600535836","value":"13600535836"}],"list3":[{"key":"1","value":"余额交易明细"},{"key":"2","value":"支付宝交易明细"},{"key":"3","value":"微信交易明细"}]}
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
        private List<List1Bean> list1;
        private List<List2Bean> list2;
        private List<List3Bean> list3;

        public List<List1Bean> getList1() {
            return list1;
        }

        public void setList1(List<List1Bean> list1) {
            this.list1 = list1;
        }

        public List<List2Bean> getList2() {
            return list2;
        }

        public void setList2(List<List2Bean> list2) {
            this.list2 = list2;
        }

        public List<List3Bean> getList3() {
            return list3;
        }

        public void setList3(List<List3Bean> list3) {
            this.list3 = list3;
        }

        public static class List1Bean {
            /**
             * key :
             * value : 全部
             */

            private String key;
            private String value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class List2Bean {
            /**
             * key : 13600535837
             * value : 我的
             */

            private String key;
            private String value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class List3Bean {
            /**
             * key : 1
             * value : 余额交易明细
             */

            private String key;
            private String value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
