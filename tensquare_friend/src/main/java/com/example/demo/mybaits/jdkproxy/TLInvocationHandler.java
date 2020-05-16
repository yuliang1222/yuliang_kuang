/**
 * ClassName:TLInvocationHandler
 * Author:机械革命
 * Date:2019/11/2010:56
 * Description:TODO
 */
package com.example.demo.mybaits.jdkproxy;

import java.lang.reflect.Method;

/**
 * @Author: yuliang
 * @Date: 2019/11/20 10:56
 */
public interface  TLInvocationHandler {
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
