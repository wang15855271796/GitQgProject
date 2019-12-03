package com.puyue.www.qiaoge.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by GuoGai on 2016/10/25.
 */
public class StringHelper {
    /**
     * 字符串不为空
     *
     * @param string
     * @return
     */
    public static boolean notEmptyAndNull(String string) {
        if (string != null && string.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串编码为UTF-8
     *
     * @param string
     * @return
     */
    public static String encode(String string) {
        String result = string;
        try {
            result = URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encodeGBK(String string) {
        String result = string;
        try {
            result = URLEncoder.encode(string, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 规则2：至少包含大小写字母及数字中的两种
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }
}