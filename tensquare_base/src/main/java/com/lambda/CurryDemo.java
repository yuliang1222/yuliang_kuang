package com.lambda;

import java.util.function.Function;

/**
 * �������ʽ�Ϳ��ﻯ 
 * ���ﻯ:�Ѷ�������ĺ���ת��Ϊֻ��һ�������ĺ��� 
 * ���ﻯ��Ŀ�ģ�������׼��
 * �߽׺��������Ƿ��غ����ĺ���
 */
public class CurryDemo {

	public static void main(String[] args) {
		// ʵ����x+y�ļ������ʽ
		Function<Integer,Function<Integer,Integer>>    fun1=x -> y -> x + y;
		System.out.println("fun1:        "+fun1.apply(1).apply(2));


		Function<Integer, Function<Integer, Integer>> fun = x -> y -> x
				+ y;
		System.out.println(fun.apply(2).apply(3));

		Function<Integer, Function<Integer, Function<Integer, Integer>>> fun2 = x -> y -> z -> x
				+ y + z;
		System.out.println(fun2.apply(2).apply(3).apply(4));

		int[] nums = { 2, 3, 4 };
		Function f = fun2;
		
		for (int i = 0; i < nums.length; i++) {
			if (f instanceof Function) {
				Object obj = f.apply(nums[i]);
				if (obj instanceof Function) {
					f = (Function) obj;
				} else {
					System.out.println("���ý��������Ϊ" + obj);
				}
			}
		}
	}
}
