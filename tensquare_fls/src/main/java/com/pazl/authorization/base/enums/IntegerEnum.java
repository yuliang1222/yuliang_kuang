/**
 * ClassName:IntegerEnum
 * Author:Administrator
 * Date:2020/5/23 002321:17
 * Description:TODO
 */
package com.pazl.authorization.base.enums;

/**
 * @Author: yuliang
 * @Date: 2020/5/23 0023 21:17
 */
public enum  IntegerEnum {
	Menus("0", "菜单"),
	Button("1", "按钮");
	private String code;
	private String msg;
	IntegerEnum(String code ,String message) {
		this.code = code;
		this.msg = message;
	}

	public String getCode() {
		return code;
	}

	public String getMsg(){
		return msg;
	}

	public static String getMsgByCode(String code) {
		for (IntegerEnum responseEnum : IntegerEnum.values()) {
			if (code.equals(responseEnum.getCode())) {
				return responseEnum.getMsg();
			}
		}
		return "";
	}
}
