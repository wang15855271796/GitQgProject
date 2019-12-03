package com.puyue.www.qiaoge.model.market;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MarketClassifyModel extends BaseModel {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * firstClassifyId : 4
         * firstClassifyName : 零食
         * secondClassifyList : [{"secondClassifyId":10,"secondClassifyName":"香肠","newProductVoList":null}]
         */

        public int firstClassifyId;
        public String firstClassifyName;
        public List<SecondClassifyListBean> secondClassifyList;

        public static class SecondClassifyListBean {
            /**
             * secondClassifyId : 10
             * secondClassifyName : 香肠
             * newProductVoList : null
             */

            public int secondClassifyId;
            public String secondClassifyName;
            public Object newProductVoList;
        }
    }
}
