package com.stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo4 {

	public static void main(String[] args) {
		String str = "my name is 007";

		// ʹ�ò�����
		str.chars().parallel().forEach(i -> System.out.print((char) i));
		System.out.println();
		// ʹ�� forEachOrdered ��֤˳��
		str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));

		// �ռ���list
		List<String> list = Stream.of(str.split(" "))
				.collect(Collectors.toList());
		System.out.println(list);

		// ʹ�� reduce ƴ���ַ���
		Optional<String> letters = Stream.of(str.split(" "))
				.reduce((s1, s2) -> s1 + "|" + s2);
		System.out.println(letters.orElse(""));

		// ����ʼ��ֵ��reduce
		String reduce = Stream.of(str.split(" ")).reduce("",
				(s1, s2) -> s1 + "|" + s2);
		System.out.println(reduce);

		// �������е����ܳ���
		Integer length = Stream.of(str.split(" ")).map(s -> s.length())
				.reduce(0, (s1, s2) -> s1 + s2);
		System.out.println(length);

		// max ��ʹ��
		Optional<String> max = Stream.of(str.split(" "))
				.max((s1, s2) -> s1.length() - s2.length());
		System.out.println(max.get());

		// ʹ�� findFirst ��·����
		OptionalInt findFirst = new Random().ints().findFirst();
		System.out.println(findFirst.getAsInt());
	}

}
