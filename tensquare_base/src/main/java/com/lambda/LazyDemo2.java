package com.lambda;

import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

class MyLazy {
	private final IntSupplier data;

	public MyLazy(IntSupplier data) {
		this.data = data;
	}

	/**
	 * �м���� ���������ղ���value����ôop����������ִ��
	 */
	public MyLazy some(IntUnaryOperator op) {
		// Ϊ�˲�Ӱ��֮ǰ���ݣ�ÿ�η���һ���¶���
		return new MyLazy( () -> op.applyAsInt(this.data.getAsInt()));
	}

	/**
	 * ���ղ���
	 */
	public int value() {
		return this.data.getAsInt();
	}
}

public class LazyDemo2 {

	public static void main(String[] args) {
		MyLazy lazy = new MyLazy(()->10);

		System.out.println("û�е���value���ղ������м��������־��Ӧ�ô�ӡ");
		// ����Ҫʵ�ֲ��������ղ���value�������
		// �м����some����ĺ�������������ִ��
		lazy.some(LazyDemo2::doubleNumber).some(LazyDemo2::doubleNumber);
		
		System.out.println("����value���ղ���");
		int value = lazy.some(LazyDemo2::doubleNumber)
				.some(LazyDemo2::doubleNumber).value();

		System.out.println("value= " + value);

		// map ������������м����
		// sum ��������������ղ���

		// int sum = IntStream.rangeClosed(1, 3).map(LazyDemo2::doubleNumber)
		// .sum();
		// System.out.println("sum=" + sum);

		// ���������治�������ղ�������ô���е��м����������������ִ��
		// ��������Ķ�����ֵ
		// ����Ĵ��벻ִ��sum�������ղ���
		IntStream.rangeClosed(1, 3).map(LazyDemo2::doubleNumber);
	}

	public static int doubleNumber(int i) {
		System.out.println("������������");
		return i * 2;
	}

}
