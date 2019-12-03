package com.puyue.www.qiaoge.model.mine.login;

import com.puyue.www.qiaoge.base.BaseModel;

/**
 * Created by Administrator on 2018/4/18.
 */

public class LoginModel extends BaseModel {

    /**
     * data : {"token":"a098a7c303c84fad8e90429f798ce737","userBaseInfoVO":{"name":"cy","phone":"15700191306","sex":0,"sexName":"女","type":1,"invitationCode":"12346","hasSetPayPwd":false}}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * token : a098a7c303c84fad8e90429f798ce737
         * userBaseInfoVO : {"name":"cy","phone":"15700191306","sex":0,"sexName":"女","type":1,"invitationCode":"12346","hasSetPayPwd":false}
         */

        public String token;
        public UserBaseInfoVOBean userBaseInfoVO;

        public static class UserBaseInfoVOBean {
            /**
             * name : cy
             * phone : 15700191306
             * sex : 0
             * sexName : 女
             * type : 1
             * invitationCode : 12346
             * hasSetPayPwd : false
             */

            public String name;
            public String phone;
            public int sex;
            public String sexName;
            public int type;
            public String invitationCode;
            public boolean hasSetPayPwd;
        }
    }
}
