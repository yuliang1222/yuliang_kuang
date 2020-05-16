package com.example.demo.web;


import com.example.demo.DemoApplication;
import com.example.demo.itheima.observer.OrderObservable;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuliang
 * @Date: 2019/9/27 11:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class HelloControllerTest {

	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void test1() {
//		OrderObservable order = new OrderObservable(this, "用户下单成功");
//		applicationContext.publishEvent(order);
//		return ;
		sd();

	}
	@Scheduled(fixedDelay = 1000)
	private static void sd() {
		System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
		ThreadPoolExecutor threadPoolExecutor =
				new ThreadPoolExecutor(10, 20, 100, TimeUnit.SECONDS,
						new LinkedBlockingQueue<>(),
						new ThreadPoolExecutor.AbortPolicy());
		threadPoolExecutor.execute(() -> {
					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
					try {
						Thread.sleep(2000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		);
	}
	@Test
	public void test11() {
		String temp = "1.1.1.1.1";
		String s = temp.replaceAll("(\\.)", "");
		int i = Integer.parseInt(s);
		System.out.println(s);
		String str = String.format("%1$10d",i);
		System.out.println(str);

	}


}
