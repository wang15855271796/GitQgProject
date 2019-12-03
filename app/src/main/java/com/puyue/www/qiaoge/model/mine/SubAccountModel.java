package com.puyue.www.qiaoge.model.mine;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class SubAccountModel extends BaseModel {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * loginPhone : 15700191311
         * loginUserName : 11
         */

        public int id;
        public String loginPhone;
        public String loginUserName;
        public int enabled;//0是子账号禁用中,1是子账号可用中
    }
}
