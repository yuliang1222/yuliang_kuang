/**
 * ClassName:TestSpringCloud
 * Author:Administrator
 * Date:2020/7/10 001010:17
 * Description:TODO
 */
package com.example.demo.rabbitmq;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: yuliang
 * @Date: 2020/7/10 0010 10:17
 */

public class TestSpringCloud {
	public static void main(String[] args) {
		count("aaaabbbbcccc", "#H[");

	}
	public static int count(String text, String keyText) {
		if (StringUtils.isEmpty(text) || StringUtils.isEmpty(keyText)) {
			return -1;
		}

		int count = 0;
		while (text.indexOf(keyText) != -1) {
			text = text.substring(text.indexOf(keyText) + 1, text.length());
			count++;
		}

		return count;
	}
}
