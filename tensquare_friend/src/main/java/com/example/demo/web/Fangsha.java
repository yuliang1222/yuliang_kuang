/**
 * ClassName:fangsha
 * Author:Administrator
 * Date:2019/12/9 000917:34
 * Description:TODO
 */
package com.example.demo.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: yuliang
 * @Date: 2019/12/9 0009 17:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fangsha {
	private String timestart;
	private Integer cout;

	public static void main(String[] args) {

			String str = "127.0.0.1";
			String pattern = "\\d+(.\\d+)*";

			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(str);
			System.out.println(m.matches());
		}

}
