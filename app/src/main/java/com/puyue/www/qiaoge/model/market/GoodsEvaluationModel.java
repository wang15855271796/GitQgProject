package com.puyue.www.qiaoge.model.market;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/13.
 */

public class GoodsEvaluationModel {
    public String header;
    public String number;
    public String time;
    public String content;
    public String reply;

    public GoodsEvaluationModel(String header, String number, String time, String content, String reply) {
        this.header = header;
        this.number = number;
        this.time = time;
        this.content = content;
        this.reply = reply;
    }
}
