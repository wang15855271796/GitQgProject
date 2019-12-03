package com.puyue.www.qiaoge.helper;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串特殊处理帮助类
 */
public class StringSpecialHelper {

    public static SpannedString getSpanTextSizeString(String text, int size) {
        if (text == null) {
            text = "";
        }
        SpannableString ss = new SpannableString(text);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, false);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return new SpannedString(ss);
    }

    /**
     * 替换其中某些字符为*（从第start开始-end结束）
     *
     * @param text
     * @param start
     * @param end
     * @param replaceChar
     * @return
     */
    public static String replaceAppointChar(String text, int start, int end, String replaceChar) {
        if (text == null || text.length() == 0 || start < 0 || end < 0 || start > end || end > text.length()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(text.subSequence(0, start));
        for (int i = start; i <= end; i++) {
            sb.append(replaceChar);
        }
        sb.append(text.subSequence(end + 1, text.length()));
        return sb.toString();
    }

    /**
     * @param res
     * @param type
     * @param start
     * @param length
     * @return
     * @deprecated （同StringHandlingHelper.replaceAppointChar）
     * <p/>
     * 替换其中某些字符为*
     */
    public static String replaceString(String res, int type, int start, int length) {
        if (TextUtils.isEmpty(res)) {
            return null;
        }
        StringBuffer newString = new StringBuffer();
        switch (type) {
            case 1: // 前
                for (int i = 0; i < length; i++) {
                    newString.append('*');
                }
                for (int i = start + length; i < res.length(); i++) {
                    char temp = res.charAt(i);
                    newString.append(temp);
                }
                break;
            case 2: // 中
                for (int i = 0; i < start; i++) {
                    char temp = res.charAt(i);
                    newString.append(temp);
                }
                for (int i = 0; i < length; i++) {
                    newString.append('*');
                }
                for (int i = start + length; i < res.length(); i++) {
                    char temp = res.charAt(i);
                    newString.append(temp);
                }
                break;
            case 3: // 后
                for (int i = 0; i < res.length() - length; i++) {
                    char temp = res.charAt(i);
                    newString.append(temp);
                }
                for (int i = 0; i < length; i++) {
                    newString.append('*');
                }
                break;
            default:
                break;
        }
        return new String(newString);
    }

    /**
     * 获取子字符串
     *
     * @param res
     * @param start
     * @param end
     * @return
     */
    public static String getSubString(String res, int start, int end) {
        if (StringUtils.isBlank(res))
            return "";
        String result;
        if (end == -1) {
            result = res.substring(start, res.length());
        } else {
            result = res.substring(start, end);
        }
        return result;
    }

    /**
     * 构建某些字符串颜色不同
     *
     * @param text
     * @param start
     * @param length
     * @param color
     * @return
     */
    public static SpannableStringBuilder buildSpanColorStyle(String text, int start, int length, int color) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(text);
        stringBuilder.setSpan(new ForegroundColorSpan(color), start, start + length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

    /**
     * 构建有两种不同颜色字符串
     *
     * @param text
     * @param style1Text
     * @param color1
     * @param style2Text
     * @param color2
     * @return
     */
    public static SpannableStringBuilder buildSpanTwoTextStyle(String text, String style1Text, int color1, String style2Text, int color2) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(text);
        int indexStart = StringUtils.indexOf(text, style1Text);
        if (indexStart != -1) {
            stringBuilder.setSpan(new ForegroundColorSpan(color1), indexStart, indexStart + style1Text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        indexStart = StringUtils.indexOf(text, style2Text);
        if (indexStart != -1) {
            stringBuilder.setSpan(new ForegroundColorSpan(color2), indexStart, indexStart + style2Text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return stringBuilder;
    }

    /**
     * 构建有两种不同颜色、不同字体字符串
     *
     * @param text
     * @param style1Text
     * @param color1
     * @param size1
     * @param style2Text
     * @param color2
     * @param size2
     * @return
     */
    public static SpannableStringBuilder buildSpanTwoTextStyle(String text, String style1Text, int color1, int size1, String style2Text, int color2, int size2) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(text);
        int indexStart = StringUtils.indexOf(text, style1Text);
        if (indexStart != -1) {
            stringBuilder.setSpan(new AbsoluteSizeSpan(size1, false), indexStart, indexStart + style1Text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            stringBuilder.setSpan(new ForegroundColorSpan(color1), indexStart, indexStart + style1Text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        indexStart = StringUtils.indexOf(text, style2Text);
        if (indexStart != -1) {
            stringBuilder.setSpan(new AbsoluteSizeSpan(size2, false), indexStart, indexStart + style2Text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            stringBuilder.setSpan(new ForegroundColorSpan(color2), indexStart, indexStart + style2Text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return stringBuilder;
    }

    /**
     * 针对小数做颜色处理
     *
     * @param text
     * @param color
     * @return
     */
    public static SpannableStringBuilder buildSpanDouble(String text, int color) {
        ArrayList<String> list = extractDoubleValueFromText(text);
        return buildSpanByTag(text, list, color, false);
    }

    public static SpannableStringBuilder buildSpanDouble(String text, int color, int size, boolean dip) {
        ArrayList<String> nums = extractDoubleValueFromText(text);
        return buildSpanByTag(text, nums, color, size, dip);
    }

    /**
     * 针对分数做颜色处理
     *
     * @param text
     * @param color
     * @return
     */
    public static SpannableStringBuilder buildSpanPercent(String text, int color) {
        ArrayList<String> list = extractPercentFromText(text);
        list.add("+");
        return buildSpanByTag(text, list, color);
    }

    public static SpannableStringBuilder buildSpanPercentOrNumber(String text, int color) {
        ArrayList<String> list = extractPercentAndNumberFromText(text);
        list.add("+");
        return buildSpanByTag(text, list, color);
    }

    public static SpannableStringBuilder buildWithDrawString(String text, int color) {
        ArrayList<String> list = extractPercentFromText(text);
        list.add("续投");
        list.add("+");
        return buildSpanByTag(text, list, color);
    }

    public static SpannableStringBuilder buildReInvestChanceString(String text, int color) {
        ArrayList<String> strings = extractDoubleOrIntFromText(text);
        strings.add("次");
        return buildSpanByTag(text, strings, color);
    }

    /**
     * 针对分数做颜色大小处理
     *
     * @param text
     * @param color
     * @param sizeDip
     * @return
     */
    public static SpannableStringBuilder buildSpanPercent(String text, int color, int sizeDip) {
        ArrayList<String> list = extractPercentFromText(text);
        return buildSpanByTag(text, list, color, sizeDip, true);
    }

    /**
     * 针对分数做颜色大小处理
     *
     * @param text
     * @param color
     * @param sizeDip
     * @param dip
     * @return
     */
    public static SpannableStringBuilder buildSpanPercent(String text, int color, int sizeDip, boolean dip) {
        ArrayList<String> list = extractPercentFromText(text);
        return buildSpanByTag(text, list, color, sizeDip, dip);
    }

    /**
     * 针对数字做颜色粗细处理
     *
     * @param text
     * @param color
     * @param isBlod
     * @return
     */
    public static SpannableStringBuilder buildSpanNumber(String text, int color, boolean isBlod) {
        ArrayList<String> nums = extractNumbersFromText(text);
        return buildSpanByTag(text, nums, color, isBlod);
    }

    public static SpannableStringBuilder buildSpanNumber(String text, int color, int size, boolean dip) {
        ArrayList<String> nums = extractNumbersFromText(text);
        return buildSpanByTag(text, nums, color, size, dip);
    }

    /**
     * 针对整型和double做颜色处理
     *
     * @param text
     * @param color
     * @return
     */
    public static SpannableStringBuilder buildSpanIntOrDouble(String text, int color) {
        ArrayList<String> nums = extractDoubleOrIntFromText(text);
        nums.add("+");
        nums.add("%");
        return buildSpanByTag(text, nums, color);
    }

    public static SpannableStringBuilder buildSpanInvestDiscountString(String text, int color) {
        ArrayList<String> nums = extractDoubleOrIntFromText(text);
        nums.add("元");
        return buildSpanByTag(text, nums, color);
    }

    /**
     * 针对数字做颜色处理
     *
     * @param text
     * @param color
     * @return
     */
    public static SpannableStringBuilder buildSpanNumber(String text, int color) {
        ArrayList<String> nums = extractNumbersFromText(text);
        return buildSpanByTag(text, nums, color, false);
    }

    /**
     * 针对数字和标点标点符号做颜色处理
     *
     * @param text
     * @param color
     * @return
     */
    public static SpannableStringBuilder buildSpanNumberAndPunctuation(String text, int color) {
        ArrayList<String> nums = extractNumbersFromTextAndPunctuation(text);
        return buildSpanByTag(text, nums, color);
    }

    public static SpannableStringBuilder buildSpanFirstNumber(String text, int color) {
        ArrayList<String> nums = extractNumbersFromText(text);
        if (nums.size() >= 1) {
            String firstNum = nums.get(0);
            nums.clear();
            nums.add(firstNum);
        }
        return buildSpanByTag(text, nums, color, false);
    }

    /**
     * 针对tags做颜色粗细处理
     *
     * @param text
     * @param tag
     * @param color
     * @param isBlod
     * @return
     */
    public static SpannableStringBuilder buildSpanByTag(String text, ArrayList<String> tag, int color, boolean isBlod) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(text);
        for (String str : tag) {
            int indexStart = StringUtils.indexOf(text, str);
            if (indexStart == -1)
                continue;
            stringBuilder.setSpan(new ForegroundColorSpan(color), indexStart, indexStart + str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (isBlod) {
                stringBuilder.setSpan(new StyleSpan(Typeface.BOLD), indexStart, indexStart + str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return stringBuilder;
    }

    /**
     * 针对spanText做颜色处理
     *
     * @param text
     * @param spanText
     * @param color
     * @return
     */
    public static SpannableStringBuilder buildSpanColor(String text, String spanText, int color) {
        ArrayList<String> tags = new ArrayList<>();
        tags.add(spanText);
        return buildSpanByTag(text, tags, color);
    }

    /**
     * 针对tags做颜色处理
     *
     * @param text
     * @param tag
     * @param color
     * @return
     */
    public static SpannableStringBuilder buildSpanByTag(String text, ArrayList<String> tag, int color) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(text);
        for (String str : tag) {
            int indexStart = StringUtils.indexOf(text, str);
            if (indexStart == -1)
                continue;
            stringBuilder.setSpan(new ForegroundColorSpan(color), indexStart, indexStart + str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return stringBuilder;
    }

    /**
     * 针对tags做颜色大小处理
     *
     * @param text
     * @param tag
     * @param color
     * @param size
     * @return
     */
    public static SpannableStringBuilder buildSpanByTag(String text, ArrayList<String> tag, int color, int size, boolean dip) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(text);
        for (String str : tag) {
            int indexStart = StringUtils.indexOf(text, str);
            if (indexStart == -1)
                continue;
            stringBuilder.setSpan(new ForegroundColorSpan(color), indexStart, indexStart + str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            stringBuilder.setSpan(new AbsoluteSizeSpan(size, dip), indexStart, indexStart + str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return stringBuilder;
    }

    /**
     * 提取第一个分数串
     *
     * @param text
     * @return
     */
    public static ArrayList<String> extractDoubleValueFromText(String text) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            if (matcher.groupCount() >= 1) {
                list.add(matcher.group(0));
            }
        }
        return list;
    }

    public static ArrayList<String> extractDoubleOrIntFromText(String string) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[0-9]+[.]*[0-9]{0,2}");
        String str = string;
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    public static ArrayList<String> extractDoubleOrInt(String string) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)");
        String str = string;
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    /**
     * 提取第一个分数串
     *
     * @param text
     * @return
     */
    public static ArrayList<String> extractPercentFromText(String text) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?%");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            if (matcher.groupCount() >= 1) {
                list.add(matcher.group(0));
            }
        }
        return list;
    }

    public static ArrayList<String> extractPercentAndNumberFromText(String text) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[0-9]+[.]*[0-9]{0,2}[%]*");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            list.add(matcher.group(0));
        }
        return list;
    }

    /**
     * 提取第一个数字串
     *
     * @param text
     * @return nullable
     */
    public static String extractNumberFromText(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    /**
     * 提取全部数字串
     *
     * @param text
     * @return
     */
    public static ArrayList<String> extractNumbersFromText(String text) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = null;
        while (text != null && text.length() > 0) {
            matcher = pattern.matcher(text);
            if (matcher.find()) {
                String str = matcher.group(0);
                list.add(str);
                text = text.substring(text.indexOf(str) + str.length());
            } else {
                text = "";
            }
        }
        return list;
    }

    /**
     * 提取全部数字串和标点符号
     *
     * @param text
     * @return
     */
    public static ArrayList<String> extractNumbersFromTextAndPunctuation(String text) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\d[\\d|,|\\.]*");
        Matcher matcher = null;
        while (text != null && text.length() > 0) {
            matcher = pattern.matcher(text);
            if (matcher.find()) {
                String str = matcher.group(0);
                list.add(str);
                text = text.substring(text.indexOf(str) + str.length());
            } else {
                text = "";
            }
        }
        return list;
    }

    /**
     * 格式化金额逗号分隔显示
     *
     * @param money
     * @return
     */
    public static String formatMoney(String money) {
        double dMoney = NumberUtils.toDouble(money, 0);
        DecimalFormat format = new DecimalFormat("###,###,###,##0.00");
        return format.format(dMoney);
    }

    /**
     * 格式化金额逗号分隔显示（不保留小数）
     *
     * @param money
     * @return
     */
    public static String formatMoneyWithoutDecimal(String money) {
        double dMoney = NumberUtils.toDouble(money, 0);
        DecimalFormat format = new DecimalFormat("###,###,###,##0");
        return format.format(dMoney);
    }

    /**
     * 从字符串中获取数字
     * @param string
     * @return
     */
    public static String getNumberFromString(String string) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(string);
        m.find();
        return m.group();
    }
}
