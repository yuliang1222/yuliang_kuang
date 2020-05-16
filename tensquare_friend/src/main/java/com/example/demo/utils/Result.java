package com.example.demo.utils;

import lombok.Data;

import java.util.Date;

@Data
public class Result<T> {
	
	private int code;
	private String msg;
	private T data;
	private Date time;
	/**
	 *  成功时候的调用
	 * */
	public static  <T> Result<T> success(T data){
		return new Result<T>();
	}
	public static  <T> Result<T> error(T data){
		return new Result<T>(data);
	}
	public static  <T> Result<T> success(){
		return new Result<T>();
	}
	/**
	 *  失败时候的调用
	 * */
	public static  <T> Result<T> error(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
	}

	private Result() {
		this.code = 0;
		this.time = new Date();
		this.msg = "success";
	}

	private Result(T data) {
		this.code = 1;
		this.data = data;
		this.msg = "error";
	}
	
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	private Result(CodeMsg codeMsg) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
			this.time = new Date();
		}
	}


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
