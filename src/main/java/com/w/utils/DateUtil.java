package com.w.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 返回时间字符串格式：yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}
	
	/**
	 * 返回时间字符串格式：yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String date2String2(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 返回时间字符串格式：yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String date2String3(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
}
