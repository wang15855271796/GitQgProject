package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王文博} on 2019/9/27
 */
public class RecommendModel {

    /**
     * code : 1
     * message : 成功
     * data : ["【金锋食品】鱿鱼半串（70g*20支/包）","编辑3","自营3","【典亨记】广式荷香糯米鸡（18包/箱）"]
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<String> data;

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

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
