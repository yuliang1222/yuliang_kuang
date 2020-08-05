/**
 * ClassName:zixuansuo
 * Author:Administrator
 * Date:2020/7/17 001711:20
 * Description:TODO
 */
package com.example.demo.zixuansuo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Author: yuliang
 * @Date: 2020/7/17 0017 11:20
 */
@Slf4j
public class zixuansuo {
	public static AtomicInteger count = new AtomicInteger(0);

	// 请求总数
	public static int clientTotal = 3;
	public static  ReentrantSpinLock spinLock1 = new ReentrantSpinLock();
	public static  TicketLockV2 spinLock2 = new TicketLockV2();
	public static  MCSLock spinLock = new MCSLock();

	// 同时并发执行的线程数
	public static int threadTotal = 3;
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal ; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					spinLock.lock();
//					spinLock.lock();
//					spinLock.unlock();
					spinLock.unlock();


					semaphore.release();
				} catch (Exception e) {
					log.error("exception", e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		log.info("count:{}", count);
	}

	private static void add() {
		count.incrementAndGet();
	}

	/**
	 * MCS:发明人名字John Mellor-Crummey和Michael Scott
	 * 代码来源：http://ifeve.com/java_lock_see2/
	 */
	public static  class MCSLock {
		/**
		 * 节点，记录当前节点的锁状态以及后驱节点
		 */
		public static class MCSNode {
			volatile MCSNode next;
			volatile boolean isLocked = true;
		}
		private static final ThreadLocal<MCSNode> NODE = new ThreadLocal<MCSNode>();
		// 队列
		@SuppressWarnings("unused")
		private volatile MCSNode queue;
		// queue更新器
		private static final AtomicReferenceFieldUpdater<MCSLock, MCSNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(MCSLock.class, MCSNode.class,
				"queue");
		public void lock() {
			// 创建节点并保存到ThreadLocal中
			MCSNode currentNode = new MCSNode();
			NODE.set(currentNode);
			// 将queue设置为当前节点，并且返回之前的节点
			MCSNode preNode = UPDATER.getAndSet(this, currentNode);
			if (preNode != null) {
				// 如果之前节点不为null，表示锁已经被其他线程持有
				preNode.next = currentNode;
				// 循环判断，直到当前节点的锁标志位为false
				while (currentNode.isLocked) {
					System.out.println(Thread.currentThread().getName() + "没有获取锁不能唱歌");

				}
			}
			System.out.println(Thread.currentThread().getName() + "唱了一首忘情水");
		}
		public void unlock() {
			MCSNode currentNode = NODE.get();
			// next为null表示没有正在等待获取锁的线程
			if (currentNode.next == null) {
				// 更新状态并设置queue为null
				if (UPDATER.compareAndSet(this, currentNode, null)) {
					// 如果成功了，表示queue==currentNode,即当前节点后面没有节点了
					System.out.println(Thread.currentThread().getName() + "据开锁1");
					return;
				} else {
					// 如果不成功，表示queue!=currentNode,即当前节点后面多了一个节点，表示有线程在等待
					// 如果当前节点的后续节点为null，则需要等待其不为null（参考加锁方法）
					while (currentNode.next == null) {
						System.out.println(Thread.currentThread().getName() + "据开锁2");
					}
				}
			} else {
				// 如果不为null，表示有线程在等待获取锁，此时将等待线程对应的节点锁状态更新为false，同时将当前线程的后继节点设为null
				currentNode.next.isLocked = false;
				currentNode.next = null;
				System.out.println(Thread.currentThread().getName() + "据开锁3");

			}
		}
	}
	public static class ReentrantSpinLock {
		private AtomicReference<Thread> cas = new AtomicReference<Thread>();
		private int count=1;
		public void lock() {
			Thread current = Thread.currentThread();
			if (current == cas.get()) { // 如果当前线程已经获取到了锁，线程数增加一，然后返回
				count++;
				System.out.println(current.getName()+"唱完了忘情水我还想唱天意==count="+count);
				return;
			}
			// 如果没获取到锁，则通过CAS自旋
			while (!cas.compareAndSet(null, current)) {
				// DO nothing
				System.out.println(current.getName()+"没有锁的我不能唱歌==count="+count);
			}
			System.out.println(Thread.currentThread().getName()+"获取锁并且唱了首忘情水==count="+count);

		}
		public void unlock() {
			Thread cur = Thread.currentThread();
			if (cur == cas.get()) {
				if (count > 1) {// 如果大于0，表示当前线程多次获取了该锁，释放锁通过count减一来模拟
					count--;
					System.out.println(Thread.currentThread().getName()+"锯开了第"+(count)+"锁==count="+count);
				} else {// 如果count==0，可以将锁释放，这样就能保证获取锁的次数与释放锁的次数是一致的了。
					cas.compareAndSet(cur, null);
					System.out.println(Thread.currentThread().getName()+"锯开锁==count="+count);
				}
			}
		}
	}
	public static class TicketLockV2 {
		/**
		 * 服务号
		 */
			private AtomicInteger serviceNum = new AtomicInteger(1);
		/**
		 * 排队号
		 */
		private AtomicInteger ticketNum = new AtomicInteger(0);
		/**
		 * 新增一个ThreadLocal，用于存储每个线程的排队号
		 */
		private ThreadLocal<Integer> ticketNumHolder = new ThreadLocal<Integer>();
		public void lock() {
			int currentTicketNum = ticketNum.incrementAndGet();
			// 获取锁的时候，将当前线程的排队号保存起来
			ticketNumHolder.set(currentTicketNum);
			while (currentTicketNum != serviceNum.get()) {
				// Do nothing
				System.out.println(Thread.currentThread().getName() + "没有获取锁不能唱歌");

			}
			System.out.println(Thread.currentThread().getName() + "唱了一首忘情水");
		}
		public void unlock() {
			// 释放锁，从ThreadLocal中获取当前线程的排队号
			Integer currentTickNum = ticketNumHolder.get();
			serviceNum.compareAndSet(currentTickNum, currentTickNum + 1);
			System.out.println(Thread.currentThread().getName() + "锯开锁");
		}
	}
}
