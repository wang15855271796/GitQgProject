package com.puyue.www.qiaoge.model.mine;

import com.puyue.www.qiaoge.base.BaseModel;

/**
 * Created by Administrator on 2018/4/18.
 */

public class AccountCenterModel extends BaseModel {

    /**
     * data : {"name":"name6","phone":"15267022961","sex":0,"sexName":"女","type":1,"invitationCode":"","hasSetPayPwd":true,"mainAccount":false,"customerPhone":"18368496582","version":null}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * name : name6
         * phone : 15267022961
         * sex : 0
         * sexName : 女
         * type : 1
         * invitationCode :
         * hasSetPayPwd : true
         * mainAccount : false
         * customerPhone : 18368496582
         * version : null
         */

        public String name;
        public String phone;
        public int sex;
        public String sexName;
        public int type;
        public String invitationCode;
        public boolean hasSetPayPwd;
        public boolean mainAccount;
        public String customerPhone;
        public String version;
    }
}
