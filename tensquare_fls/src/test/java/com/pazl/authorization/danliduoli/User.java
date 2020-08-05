/**
 * ClassName:User
 * Author:Administrator
 * Date:2020/7/22 002218:46
 * Description:TODO
 */
package com.pazl.authorization.danliduoli;

/**
 * @Author: yuliang
 * @Date: 2020/7/22 0022 18:46
 */
public class User extends Thread {
	private Gate gate;
	private String name;
	private String address;

	public User(String name , String address , Gate gate){
		this.name = name;
		this.address = address;
		this.gate = gate;
	}

	@Override
	public void run() {
		System.out.println(name + ">>>>>can into the gate");
		while (true){
			gate.pass(name , address);
		}
	}
}

