/**
 * ClassName:Client
 * Author:Administrator
 * Date:2020/5/12 001221:34
 * Description:TODO
 */
package com.design.ShareState;

/**
 * @Author: yuliang
 * @Date: 2020/5/12 0012 21:34
 */
class Client12 {
	public static void main(String args[]) {
		Account acc = new Account("段誉",0.0);
		acc.deposit(1000);
		acc.withdraw(2000);
		acc.deposit(3000);
		acc.withdraw(4000);
		acc.withdraw(1000);
		acc.computeInterest();
	}
}