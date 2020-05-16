/**
 * ClassName:Inherance
 * Author:Administrator
 * Date:2020/3/28 002815:13
 * Description:TODO
 */
package com.example.demo.wx.moban.config;

import org.apache.ibatis.annotations.Param;

/**
 * @Author: yuliang
 * @Date: 2020/3/28 0028 15:13
 */
public class Inherance {
	public static void main(String[] args) {
		ChClass chClass = new ChClass();
		ChClass.statiMath();
		chClass.me();
	}
}


class Parent{

	public Parent() {
		System.out.println(1);
	}
	public static void statiMath() {
		System.out.println(2);
	}
	public  void me() {
		System.out.println(3);
	}
}
class ChClass extends  Parent{

	public ChClass() {
		System.out.println(4);
	}
	public static void statiMath() {
		System.out.println(5);
	}
	@Override
	public  void me() {
		System.out.println(6);
	}
}