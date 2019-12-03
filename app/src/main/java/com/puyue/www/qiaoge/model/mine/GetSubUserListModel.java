package com.puyue.www.qiaoge.model.mine;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/28.
 */

public class GetSubUserListModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"subUserId":52,"subUserName":"啦啦啦"}]
     * success : true
     * error : false
     */

    public int code;
    public String message;
    public boolean success;
    public boolean error;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * subUserId : 52
         * subUserName : 啦啦啦
         */

        public int subUserId;
        public String subUserName;
    }
}
