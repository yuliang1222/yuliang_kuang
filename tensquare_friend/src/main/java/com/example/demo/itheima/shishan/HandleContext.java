/**
 * ClassName:HandleContext
 * Author:机械革命
 * Date:2019/9/2220:28
 * Description:TODO
 */
package com.example.demo.itheima.shishan;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.springframework.beans.factory.BeanFactory;

import java.util.Map;

/**
 * @Author: yuliang
 * @Date: 2019/9/22 20:28
 */
public class HandleContext {
	private Map<String, Class> handlerMap;

	public HandleContext(Map<String, Class> handlerMap) {
		this.handlerMap = handlerMap;
	}

	public AbstractHandler getInstance(String type) throws IllegalAccessException, InstantiationException {
		Class clazz = handlerMap.get(type);
		if (null == clazz) {
			throw new IllegalArgumentException("not found handle for type: " + type);
		}
		return (AbstractHandler) clazz.newInstance();
	}

}