package com.example.demo.mybaits.jdkproxy;

import com.example.demo.mybaits.jdkproxy.TLClassLoader;
import com.example.demo.mybaits.jdkproxy.TLGuangDongPerson;
import com.example.demo.mybaits.jdkproxy.TLInvocationHandler;
import com.example.demo.mybaits.jdkproxy.TLPerson;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Method;


public class TLTranslation implements TLInvocationHandler {
	private TLPerson target;

	public TLTranslation(TLPerson target) {
		this.target = target;
	}

	public static Object getInstance(TLPerson target) {
		try {
			Class<? extends TLPerson> clazz = target.getClass();
			System.out.println(clazz.getName());
			Object poxy = TLProxy.newProxyInstance(new TLClassLoader(), clazz.getInterfaces(), new TLTranslation(target));
			System.out.println(poxy.getClass().getName());
			return poxy;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		System.out.println("我是翻译，你说话我翻译,请说话！");
		Object invoke = method.invoke(target,args);
		System.out.println("他说的是xxxxxxxxxx");
		return invoke;
	}

	public static void main(String[] args) throws Exception{
		TLPerson translationInstance = (TLPerson)TLTranslation.getInstance(new TLGuangDongPerson());
		translationInstance.speak();
		byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{TLPerson.class});
		FileOutputStream outputStream = new FileOutputStream("E:\\$Proxy0.class");
		outputStream.write(bytes);
		outputStream.close();
	}

}