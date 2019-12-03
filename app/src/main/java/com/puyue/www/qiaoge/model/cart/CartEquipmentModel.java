package com.puyue.www.qiaoge.model.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/5.
 */

public class CartEquipmentModel {
    public List<Double> amount = new ArrayList<>();
    public List<Integer> equipmentIdList = new ArrayList<>();
    public List<Integer> totalNumList = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("\"amount\":").append(amount);
        sb.append(", \"equipmentIdList\":").append(equipmentIdList);
        sb.append(", \"totalNumList\":").append(totalNumList);
        sb.append('}');
        return sb.toString();
    }
}
