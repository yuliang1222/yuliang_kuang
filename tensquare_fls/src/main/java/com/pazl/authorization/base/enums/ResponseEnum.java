package com.pazl.authorization.base.enums;

/**
 * @Author: yuliang
 * @Date: 2020/5/23 0023 21:25
 */
public enum ResponseEnum {
	success("0", "成功");
	private String code;
	private String msg;
	ResponseEnum(String code ,String message) {
		this.code = code;
		this.msg = message;
	}

	public String getCode() {
		return code;
	}

	public String getMsg(){
		return msg;
	}
}
