package com.example.demo.mybaits.jdkproxy;

import com.example.demo.mybaits.jdkproxy.TLClassLoader;
import com.example.demo.mybaits.jdkproxy.TLInvocationHandler;


import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TLProxy {
	private static final String LN = "\r\n";
	public static Object newProxyInstance(TLClassLoader loader, Class<?>[] interfaces, TLInvocationHandler h) throws Exception {
		// 动态生成源代码
		String srcClass = generateSrc(interfaces);
		// 输出Java文件
		String filePath = TLProxy.class.getResource("").getPath()  + "$ProxyO.java";
		System.out.println(filePath);
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(srcClass);
		fileWriter.flush();
		fileWriter.close();
		// 编译Java文件为class文件
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		Iterable iterable = fileManager.getJavaFileObjects(filePath);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, iterable);
		task.call();
		fileManager.close();
		// 加载编译生成的class文件到JVM
		Class<?> proxyClass = loader.findClass("$ProxyO");
		Constructor<?> constructor = proxyClass.getConstructor(TLInvocationHandler.class);
		// 删掉虚拟代理类
		File file = new File(filePath);
		file.delete();
		// 返回字节码重组以后的代理对象
		return constructor.newInstance(h);
	}
	private static String generateSrc(Class<?>[] interfaces) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("package dynamicProxy.jdkDynamic.custom;" + LN );
		stringBuilder.append("import dynamicProxy.jdkDynamic.custom.TLPerson;" + LN);
		stringBuilder.append("import java.lang.reflect.Method;" + LN);
		stringBuilder.append("public class $ProxyO implements " + interfaces[0].getName() + "{" + LN);
		stringBuilder.append("TLInvocationHandler h;" + LN);
		stringBuilder.append("public $ProxyO(TLInvocationHandler h) {" + LN);
		stringBuilder.append("this.h = h;" + LN);
		stringBuilder.append("}" + LN);

		for (Method method : interfaces[0].getMethods()) {
			stringBuilder.append("public " + method.getReturnType().getName() + " " + method.getName() + "() {" + LN);
			stringBuilder.append("try {" + LN);
			stringBuilder.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\", new Class[]{});" + LN);
			stringBuilder.append("this.h.invoke(this, m, null);" + LN);
			stringBuilder.append("} catch(Throwable able) {" + LN);
			stringBuilder.append("able.getMessage();" + LN);
			stringBuilder.append("}" + LN);
			stringBuilder.append("}" + LN );
		}
		stringBuilder.append("}" + LN);
		return stringBuilder.toString();
	}
}