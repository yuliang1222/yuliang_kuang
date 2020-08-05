package com.pazl.authorization.danliduoli;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author czd
 */
@Slf4j
public class ReentrantLockTest {
	/**
	 * 非公平锁，当ReentrantLock(true)时是公平锁，都不是绝对公平，只是相对公平
	 */
	private static final ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		for(int i = 0; i < 2; i++){
			new Thread(){
				@Override
				public void run() {
					try {
						needTryLock(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

	public static void needLock(){
		try {
			//不可被中断
//			lock.lock();
			lock.lockInterruptibly();
			System.out.println(Thread.currentThread().getName()+">>>needLock()方法的效果与needLockLikeSyn()一样,但是速率比它快一点！");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("任务结束！");
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	public static void needTryLock(){
		if (lock.tryLock()){
			try {
				System.out.println(Thread.currentThread().getName()+">>>needTryLock()方法的效果与needLockLikeSyn()一样,但是速率比它快一点！");
				TimeUnit.SECONDS.sleep(2);
				System.out.println("任务结束！");
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}else{
			System.out.println(Thread.currentThread().getName() + ">>>没有获得锁！");
		}
	}

	public static void needTryLock(long seconds) throws InterruptedException{
		if (lock.tryLock(seconds,TimeUnit.SECONDS)){
			try {
				System.out.println(Thread.currentThread().getName()+">>>needTryLock()方法的效果与needLockLikeSyn()一样,但是速率比它快一点！");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("任务结束！");
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				log.info("释放锁哦" );
				lock.unlock();
			}
		}else{
			System.out.println(Thread.currentThread().getName() + ">>>在"+seconds+"秒内没有获得锁！");
		}
	}

	/**
	 * 这个方法实现的是利用synchronized关键字加锁
	 */
	public static void needLockLikeSyn(){
		synchronized (ReentrantLockTest.class){
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println(Thread.currentThread().getName()+">>>这个方法的效果与needLock()一样,但是速率比它慢一点！");
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
