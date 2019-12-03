package com.puyue.www.qiaoge.helper;

import com.puyue.www.qiaoge.safe.Des3;
import com.puyue.www.qiaoge.safe.MD5Signaturer;

import java.util.Date;


public class AppSafeHelper {
	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static String getSTime(){
		String stime=new Date().getTime()+"";
		return stime;
	}
	/**
	 * MD5签名
	 * @param string
	 * @return
	 */
	public static String sign(String string){
		MD5Signaturer md5Signaturer=new MD5Signaturer();
		return md5Signaturer.sign(string);
	}
	/**
	 * Des加密
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public static String encode(String string) throws Exception{
		return Des3.encode(string);
	}
}
