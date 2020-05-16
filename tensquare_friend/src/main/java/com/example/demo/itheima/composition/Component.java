/**
 * ClassName:Component
 * Author:机械革命
 * Date:2019/9/2311:17
 * Description:TODO
 */
package com.example.demo.itheima.composition;

/**
 * @Author: yuliang
 * @Date: 2019/9/23 11:17
 */
public abstract class Component {

	protected String name;
	public Component(String name) {
		this.name = name;
	}

	abstract void add(Component component);

	abstract void remove(Component component);

	abstract void display(int depth);
}