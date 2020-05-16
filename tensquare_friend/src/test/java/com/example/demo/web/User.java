/**
 * ClassName:User
 * Author:Administrator
 * Date:2019/12/4 000410:28
 * Description:TODO
 */
package com.example.demo.web;





public class User {



	@ExcelDesc("姓名")

	private String name;

	@ExcelDesc("年龄")

	private int age;

	@ExcelDesc("备注")

	private String remark;



	public String getName() {

		return name;

	}



	public int getAge() {

		return age;

	}



	public String getRemark() {

		return remark;

	}



	public void setName(String name) {

		this.name = name;

	}



	public void setAge(int age) {

		this.age = age;

	}



	public void setRemark(String remark) {

		this.remark = remark;

	}



	@Override

	public String toString() {

		return "User [name=" + name + ", age=" + age + ", remark=" + remark + "]";

	}



}

