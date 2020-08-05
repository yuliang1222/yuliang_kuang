package com.pazl.authorization.danliduoli;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author czd
 */
public class ReadWriteLockTest {
	/**
	 * data：模拟数据库
	 */
	private static  List<Long> data = new ArrayList<>();

	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private static Lock readLock = readWriteLock.readLock();

	private static Lock writeLock = readWriteLock.writeLock();

	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run() {
				write();
			}
		}.start();
		new Thread(){
			@Override
			public void run() {
				read();
			}
		}.start();


	}

	/**
	 * 进行写操作
	 */
	public static void write(){
		try {
			writeLock.lock();
			System.out.println(Thread.currentThread().getName()+"线程开始写数据！");
			data.add(System.currentTimeMillis());
			TimeUnit.SECONDS.sleep(2);
			System.out.println("写入数据成功！");
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			writeLock.unlock();
		}
	}

	/**
	 * 进行读操作
	 */
	public static void read(){
		try {
			readLock.lock();
			System.out.println(Thread.currentThread().getName()+"线程开始读数据！");
			for(Long L :data){
				System.out.println("数据：" + L);
			}
			TimeUnit.SECONDS.sleep(2);
			System.out.println("读取数据完成！");
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			readLock.unlock();
		}
	}
}

