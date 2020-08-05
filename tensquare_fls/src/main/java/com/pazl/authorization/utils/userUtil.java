/**
 * ClassName:userUtil
 * Author:Administrator
 * Date:2020/7/23 002310:13
 * Description:TODO
 */
package com.pazl.authorization.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yuliang
 * @Date: 2020/7/23 0023 10:13
 */
public class userUtil {
	public static String getCurrentToken() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		return request.getHeader("dsf");
	}
}
