package com.puyue.www.qiaoge.helper;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/1/11.
 */

public class DoubleHelper {
    public static Double keepTwoDecimals(Double resource) {
        BigDecimal bigDecimal = new BigDecimal(resource);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
