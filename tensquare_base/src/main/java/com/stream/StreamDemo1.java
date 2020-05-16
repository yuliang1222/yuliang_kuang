package com.stream;

import java.util.stream.IntStream;

public class StreamDemo1 {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		// �ⲿ����
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		System.out.println("���Ϊ��" + sum);

		// ʹ��stream���ڲ�����
		// map�����м����������stream�Ĳ�����
		// sum������ֹ����
		int sum2 = IntStream.of(nums).map(StreamDemo1::doubleNum).sum();
		System.out.println("���Ϊ��" + sum2);

		System.out.println("������ֵ������ֹû�е��õ�����£��м��������ִ��");
		IntStream.of(nums).map(StreamDemo1::doubleNum);
	}

	public static int doubleNum(int i) {
		System.out.println("ִ���˳���2");
		return i * 2;
	}

}
