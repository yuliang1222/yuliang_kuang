/**
 * ClassName:AppendString
 * Author:机械革命
 * Date:2019/9/2311:51
 * Description:TODO
 */
package com.example.demo.itheima.composition;

/**
 * @Author: yuliang
 * @Date: 2019/9/23 11:51
 */
public class AppendString {
	public static String appendChar(String chars,int depth) {
		if (depth < 1) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			stringBuffer.append(chars);
		}
		return stringBuffer.toString();

	}
}
