package com.lambda;

import java.util.function.Consumer;
/**
 * lambda�ײ�ʵ��ԭ��
 * 
 * 1. ��������Ϊÿһ��lambda���ʽ����һ������
 * 		��������lambda$0,1,2,3�����������õı��ʽ�������ɷ�����
 * 2. ��lambda�ط������һ��invokeDynamicָ����ָ������
 * 		bootstrap��������������bootstrap������ָ���Զ����ɵ�lambda$0
 * 		�������߷������õķ�����
 * 3. bootstrap����ʹ�����ǵ�����LambdaMetafactory.metafactory��̬����
 * 		�÷���������CallSite(����վ��)�����������MethodHandle�����������
 * 		Ҳ�������յ��õķ�����
 * 4. ��������ֻ�����һ�Ρ�
 * 
 * �Զ����ɵķ�����
 * 1. ����������lambdaһ��
 * 2. ���û��ʹ��this����ô����static������������ǳ�Ա����
 *
 */
public class CoreDemo {

	public static void main(String[] args) {
		Consumer<String> consumer = s-> System.out.println(s);
		consumer.accept("222");
		System.out.println(consumer.getClass());
		
		CoreDemo demo = new CoreDemo();
		
		demo.test2();
		// demo.test2();
	}
	
	public void test2() {
		Consumer<Integer> consumer = s-> {
			System.out.println(this);
			System.out.println(s);			
		};
		//Consumer<String> consumer = System.out::println;
		consumer.accept(2222);
		System.out.println(consumer.getClass());
	}
	
//	public void lambda$0(String string) {
//		
//	}
}
