/**
 * ClassName:User
 * Author:Administrator
 * Date:2019/12/4 000410:28
 * Description:TODO
 */
package com.example.demo.web.pageHelper;





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

	public static void main(String[] args) {
		String temp = "1.1.1.1.1";
		String s = temp.replaceAll("(\\.)", "");
		int i = Integer.parseInt(s);
		System.out.println(s);
		String str = String.format("%-10d",i,0);
		System.out.println(str);
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

