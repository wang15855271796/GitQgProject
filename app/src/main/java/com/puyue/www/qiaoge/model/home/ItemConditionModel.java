package com.puyue.www.qiaoge.model.home;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/16.
 */

public class ItemConditionModel {
    public String condition;
    public boolean isSelected;
    public int conditionId;

    public ItemConditionModel(String condition, int conditionId, boolean isSelected) {
        this.condition = condition;
        this.conditionId = conditionId;
        this.isSelected = isSelected;
    }
}
