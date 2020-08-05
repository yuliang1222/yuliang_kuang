package com.pazl.authorization.danliduoli;

import com.google.common.cache.LoadingCache;
import com.pazl.authorization.FlsApplication;
import com.pazl.authorization.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: yuliang
 * @Date: 2020/7/20 0020 13:44
 */
@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlsApplication.class)
public class TestService2Test {
	@Autowired
	TestService2 testService2;
	@Autowired
	TestService2 testService3;
	@Test
	public void test1() {
		System.out.println(testService2);
		System.out.println(testService2.equals(testService3));
		System.out.println(testService2==(testService3));
		System.out.println(testService2.getHashMap() == testService3.getHashMap());
		System.out.println(testService2.getHashMap().equals(testService3.getHashMap()));
		System.out.println(testService2.getThread().equals(testService3.getThread()));
		System.out.println(testService2.getThread()==(testService3.getThread()));
	}
	private LoadingCache<String, Book> loadingCache;

//	@Bean
//	public RuleCache ruleCache() {
//		return new RuleCache();
//	}
	@Autowired
	protected RuleCache ruleCache;
	@Test
	public void test2() throws InterruptedException {
		ruleCache.put("eee", new Book(11, "ddd"));
		System.out.println(ruleCache.get("eee"));
//		Thread.sleep(10000);
		System.out.println(ruleCache.get("eee"));
		ruleCache.put("eee",  new Book(22, "ddd"));
		System.out.println(ruleCache.get("eee"));

	}
	List<String> eu_list = new ArrayList<>();
	@Test
	public void test3() throws InterruptedException {

		String aa = "http:192.216.245.51:80,http:192.216.245.54:80";
		List<String> eureka_url = Arrays.asList(aa.split(","));
		eureka_url.stream().forEach(e->{GetIInst(e);});

	}

	private void GetIInst(String eu_url) {
		eu_list= Stream.concat(eu_list.stream().filter(e -> {
			return !e.equals(eu_url);
		}), Stream.of(eu_url)).distinct().collect(Collectors.toList());
		System.out.println(eu_list);
	}

	@Test
	public void test4() throws InterruptedException {
		Optional<Book> td = Optional.ofNullable(new Book(32, "td"))
				.map(e->{
					System.out.println(e);
					return Optional.ofNullable(new Book(22, "td")).isPresent()?e:new Book(33, "td");
				});
		ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();

		Book book = td.get();
		String ad = null;
		boolean present = Optional.of(ad).isPresent();
		System.out.println(present);
		System.out.println(
				book
		);

	}

	@Test
	public void test5() throws InterruptedException {
		AtomicReference<List<String>> ret = new AtomicReference(new ArrayList());
		String aa = "http:192.216.245.51:80,http:192.216.245.54:80";
		List<String> eureka_url = Arrays.asList(aa.split(","));
		List<String> andSet = ret.getAndSet(eureka_url);
		System.out.println(andSet);
	}

	@Test
	public void test6() throws InterruptedException {
		Gate gate = new Gate();
		User bj = new User("Bei" , "Beijing" , gate);
		User sh = new User("Shang" , "Shanghai" , gate);
		User gz = new User("Guang" , "GuangZhou" , gate);
		bj.start();
		sh.start();
		gz.start();
	}
	@Test
	public void test7() throws InterruptedException {
		//1、value.get()获取当前value的值，无锁
		AtomicInteger get = new AtomicInteger();
		System.out.println("value：" + get.get());
		get = new AtomicInteger(10);
		System.out.println("value：" + get.get());

	}
	public static void main(String[] args) {
		Gate gate = new Gate();
		User bj = new User("Bei" , "Beijing" , gate);
		User sh = new User("Shang" , "Shanghai" , gate);
		User gz = new User("Guang" , "GuangZhou" , gate);
		bj.start();
		sh.start();
		gz.start();
	}
	@Test
	public void test8() throws InterruptedException {
		//9、先自减再获取当前值
		AtomicInteger decrementAndGet = new AtomicInteger(10);
		int newValue5 = decrementAndGet.decrementAndGet();
		System.out.println("newValue5：" + newValue5);
		System.out.println("value：" + decrementAndGet.get());
	}
	@Test
	public void test9() throws InterruptedException {
		LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<>();
		Boolean addBoolean = linkedBlockingDeque.add(5);
		System.out.println("是否添加成功：" + addBoolean);
		LinkedBlockingDeque<Integer> linkedBlockingDeque1 = new LinkedBlockingDeque<>();
		linkedBlockingDeque1.addFirst(1);
		linkedBlockingDeque1.addFirst(2);
		linkedBlockingDeque1.addFirst(3);
		System.out.println("是否添加成功：" + linkedBlockingDeque1);
		Iterator<Integer> iterator = linkedBlockingDeque1.iterator();
		while (iterator.hasNext()){
			System.out.println("Iterator的addFirst结果：" + iterator.next());
		}
		LinkedBlockingDeque<Integer> linkedBlockingDeque2 = new LinkedBlockingDeque<>();
		linkedBlockingDeque2.addLast(1);
		linkedBlockingDeque2.addLast(2);
		linkedBlockingDeque2.addLast(3);
		Iterator<Integer> iterator1 = linkedBlockingDeque2.iterator();
		while (iterator1.hasNext()){
			System.out.println("Iterator的addLast结果：" + iterator1.next());
		}

		System.out.println("================");
		LinkedBlockingDeque<Integer> linkedBlockingDeque3 = new LinkedBlockingDeque<>();
		linkedBlockingDeque3.add(1);
		linkedBlockingDeque3.add(2);
		linkedBlockingDeque3.add(3);
		System.out.println("add添加成功：" + linkedBlockingDeque3);
		linkedBlockingDeque3.clear();
		System.out.println("================");
		Iterator<Integer> iterator2 = linkedBlockingDeque3.iterator();
		while (iterator2.hasNext()){
			System.out.println("Iterator的clear结果：" + iterator2.next());
		}
		System.out.println("================");

	}

	@Test
	public void test10() throws InterruptedException {
		LinkedBlockingDeque<Integer> linkedBlockingDeque5 = new LinkedBlockingDeque<>();
		linkedBlockingDeque5.add(1);
		linkedBlockingDeque5.add(2);
		linkedBlockingDeque5.add(3);
		System.out.println("队列的头部: " + linkedBlockingDeque5);
		Integer elementResult = linkedBlockingDeque5.element();
		System.out.println("队列的头部: " + elementResult);



	}

}