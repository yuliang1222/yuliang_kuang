/**
 * ClassName:DateUtil
 * Author:机械革命
 * Date:2019/5/3110:04
 * Description:TODO
 */
package com.example.demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: yuliang
 * @Date: 2019/5/31 10:04
 */
public class DateUtil {
	public static String riqi() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
//		Date date = dateFormat.parse("20190104");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 设置为当前时间}
		if (calendar.get(Calendar.DATE) < 5) {
			calendar.add(Calendar.MONTH, -1); // 设置为上一个月
			date = calendar.getTime();
			System.out.println("上一个月的时间： " + dateFormat.format(date));
			return dateFormat.format(date);
		} else {
			System.out.println("当前时间是：" + dateFormat.format(date));
			return dateFormat.format(date);
		}

	}
	public static String riqi2() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月");
//		Date date = dateFormat.parse("20190104");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 设置为当前时间}
		if (calendar.get(Calendar.DATE) < 5) {
			calendar.add(Calendar.MONTH, -1); // 设置为上一个月
			date = calendar.getTime();
			System.out.println("上一个月的时间： " + dateFormat.format(date));
			return dateFormat.format(date);
		} else {
			System.out.println("当前时间是：" + dateFormat.format(date));
			return dateFormat.format(date);
		}

	}

}