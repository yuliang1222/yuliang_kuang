package com.lambda;

import java.util.function.Consumer;
import java.util.function.IntPredicate;

public class FunctionDemo {

	
	public static void main(String[] args) {
		// ���Ժ����ӿ�
		IntPredicate predicate = i -> i > 0;
		System.out.println(predicate.test(-9));
		
		//
		// IntConsumer

		// ���Ѻ����ӿ�
		Consumer<String> consumer = s -> System.out.println(s);
		consumer.accept("���������");
	}

}
