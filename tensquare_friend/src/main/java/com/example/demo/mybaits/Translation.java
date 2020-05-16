/**
 * ClassName:Translation
 * Author:机械革命
 * Date:2019/11/2010:29
 * Description:TODO
 */
package com.example.demo.mybaits;


import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: yuliang
 * @Date: 2019/11/20 10:29
 */
public class Translation implements InvocationHandler {
	private Person target;
	public Translation(Person target) {
		this.target = target;
	}
	public static Object getInstance(Person target) {
		Class<? extends Person> clazz = target.getClass();
		System.out.println(clazz.getName());
		Object poxy = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new Translation(target));
		System.out.println(poxy.getClass().getName());
		return poxy;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我是翻译，你说话我翻译,请说话！");
		Object invoke = method.invoke(target, args);
		System.out.println("他说的是xxxxxxxxxx");
		return invoke;
	}
	public static void main(String[] args) throws Exception {
		Person translationInstance = (Person) Translation.getInstance(new GuangDongPerson());
		translationInstance.speak();
//		byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
//		FileOutputStream outputStream = new FileOutputStream("E:\\$Proxy0.class");
//		outputStream.write(bytes);
//		outputStream.close();
	}
}
