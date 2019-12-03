package com.puyue.www.qiaoge.model.mine;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/17.
 */

public class ReturnGoodsIdModel implements Serializable {
    public int num;
    public String name;
    public int numAll;

    public ReturnGoodsIdModel(int num, String name, int numAll) {
        this.num = num;
        this.name = name;
        this.numAll = numAll;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumAll() {
        return numAll;
    }

    public void setNumAll(int numAll) {
        this.numAll = numAll;
    }
}
