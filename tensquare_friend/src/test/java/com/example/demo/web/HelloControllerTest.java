package com.example.demo.web;


import com.alibaba.fastjson.JSON;
import com.example.demo.FriendApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuliang
 * @Date: 2019/9/27 11:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendApplication.class)
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

	@Test
	public void test111() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String item = iterator.next();
			if (1==1) {
				iterator.remove();
			}
		}
	}

	@Test
	public void test1111() {
		String bb  = "{{\"data\":{\"first\":{\"value\":\"黄先生\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"0426\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"06月07日 19时24分\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"消费\",\"color\":\"#173177\"},\"keyword4\":{\"value\":\"人民币260.00元\",\"color\":\"#173177\"},\"keyword5\":{\"value\":\"06月07日19时24分\",\"color\":\"#0055ff\"},\"remark\":{\"value\":\"6504.09\",\"color\":\"#d63500\"}},\"template_id\":\"gxEXs6QpBiZrzKJVNiMhdl9NuNakMShH5W73btPYiao\",\"topcolor\":\"#FF0000\",\"touser\":\"oTnQ3wAw1vSjJ_wnwz1PMaCC25ro\",\"url\":\"https://www.pgyer.com/HPv2\\\\x\"}}";
		Object parse = JSON.parse(bb);
		System.out.println(1);

	}
}
