/**
 * ClassName:Gate
 * Author:Administrator
 * Date:2020/7/22 002218:45
 * Description:TODO
 */
package com.pazl.authorization.danliduoli;

/**
 * @Author: yuliang
 * @Date: 2020/7/22 0022 18:45
 */
public class Gate {
	private int counter = 0;
	private String name = " ";
	private String address = " ";

	/**
	 * 临界值也可以说是门
	 * @param name
	 * @param address
	 */
	public synchronized void  pass(String name , String address){
		counter++;
		this.name = name;
		this.address = address;
		verify();
	}

	@Override
	public  String toString() {
		return "Gate{" +
				"counter=" + counter +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				'}';
	}

	/**
	 * 判断符合条件的名字和地址
	 * if语句可以按照需求改
	 */
	private void verify(){
		if (this.name.charAt(0) != this.address.charAt(0)){
			System.out.println("Error:" + toString());
		}
	}
}
