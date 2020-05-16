package com.mmall.concurrency.example.count;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadSafe
public class CountExample2 {

	// 请求总数
	public static int clientTotal = 500;

	// 同时并发执行的线程数
	public static int threadTotal = 1000;

	public static AtomicInteger count = new AtomicInteger(0);

	public  static ArrayList<Future> list = new ArrayList(5000);

	public static void main(String[] args) throws Exception {
		ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("yuliang-pool-%d").build();
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(200,nameThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		Callable myCallable = () -> {
			Thread.sleep(3000);
			log.info("calld方法执行了");
			return "call方法返回值";
		};
		for (int i = 0; i < clientTotal; i++) {
			Future<Object> future = executorService.submit(myCallable);
			list.add(future);
			long count = countDownLatch.getCount();
			log.info("{},获取返回值: {}",count, 1);
			semaphore.acquire();
			semaphore.release();
			countDownLatch.countDown();
		}
//		System.out.println(list);
		for (Future futureTask:  list){
			log.info("返回结果222:{}",futureTask.get());
		}
		countDownLatch.await();
		executorService.shutdown();

	}

	private static String getStringDate() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	private static void add() {
		int i = count.incrementAndGet();
		// count.getAndIncrement();
	}
}
