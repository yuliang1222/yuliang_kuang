/**
 * ClassName:Singleton
 * Author:Administrator
 * Date:2020/3/28 002815:19
 * Description:TODO
 */
package com.example.demo.wx.moban.config;

/**
 * @Author: yuliang
 * @Date: 2020/3/28 0028 15:19
 */
class Singleton {
	private Singleton() {
	}

	private static class HolderClass {
		private final static Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return HolderClass.instance;
	}

	public static void main(String args[]) {
		Singleton s1, s2;
		s1 = Singleton.getInstance();
		s2 = Singleton.getInstance();
		System.out.println(s1==s2);
	}
}