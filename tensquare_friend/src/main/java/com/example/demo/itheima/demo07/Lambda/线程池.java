package com.example.demo.itheima.demo07.Lambda;///**
// * ClassName:线程池
// * Author:机械革命
// * Date:2019/10/2113:35
// * Description:TODO
// */
//package com.example.demo.itheima.demo07.Lambda;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author: yuliang
// * @Date: 2019/10/21 13:35
// */
//@Component
//public class 线程池 {
//
//
//
////	public static void main(String[] args) {
////
////
////		sd();
////
////		threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});	threadPoolExecutor.execute(() -> {
////					System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
////				});
////		threadPoolExecutor.shutdown();
////	}
////	@Scheduled(fixedDelay = 1000)
////	private static void sd() {
////		System.out.println(Thread.currentThread().getName() + "main创建了一个新的线程执行");
////		try {
////			Thread.sleep(10000L);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
////		ThreadPoolExecutor threadPoolExecutor =
////				new ThreadPoolExecutor(10, 20, 100, TimeUnit.SECONDS,
////						new LinkedBlockingQueue<>(),
////						new ThreadPoolExecutor.AbortPolicy());
////		threadPoolExecutor.execute(() -> {
////			System.out.println(Thread.currentThread().getName() + "线程池创建了一个新的线程执行");
////					try {
////						Thread.sleep(10000L);
////					} catch (InterruptedException e) {
////						e.printStackTrace();
////					}
////				}
////		);
//////	}
////		threadPoolExecutor.shutdown();
////}
//}