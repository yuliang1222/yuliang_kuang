package com.lambda;

import java.util.function.Supplier;

class Log {

	/**
	 * ����ӡdebug�������־
	 * 
	 * @return
	 */
	public boolean isDebug() {
		return true;
	}

	public void debug(String string) {
		if (this.isDebug()) {
			System.out.println(string);
		}
	}

	/**
	 * 
	 * @param supplier
	 *            ����һ���ṩ�ַ����ĺ���
	 */
	public void debug(Supplier<String> supplier) {
		if (this.isDebug()) {
			// ����Ҫ��ӡ��ʱ�򣬲ŵ���
			System.out.println(supplier.get());
		}
	}

}

public class LazyDemo1 {

	public static void main(String[] args) {
		LazyDemo1 demo = new LazyDemo1();
		Log log = new Log();
		log.debug(() -> "��ӡ��־֮ǰ�����ж���־����: " + demo.toString());
	}

	@Override
	public String toString() {
		System.out.println("toString ��������");
		return super.toString();
	}

}
