/**
 * ClassName:OrderObservable
 * Author:机械革命
 * Date:2019/9/2222:45
 * Description:TODO
 */
package com.example.demo.itheima.observer;

import lombok.Data;
import org.springframework.context.ApplicationEvent;



@SuppressWarnings("serial")
public class OrderObservable extends ApplicationEvent {
	private static final long serialVersionUID = 20L;
	/**
	 * 设置抽象主题内容
	 * @param source
	 */
	private String message;

	public OrderObservable(Object source,String message) {
		super(source);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Object getSource() {
		return super.getSource();
	}
}
