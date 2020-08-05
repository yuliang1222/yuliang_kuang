package com.pazl.authorization.lock;

/**
 * @author czd
 * 读写锁分离核心类
 */
public class ReaderWriterLock {
	/**
	 * waitingReaders：正在等待读操作的线程的数量
	 * readingReaders：正在进行读操作的线程的数量
	 * waitingWriters：正在等待写操作的线程的数量
	 * writingWriters：正在进行写操作的线程的数量
	 */

	private int waitingReaders = 0;
	private int readingReaders = 0;
	private int waitingWriters = 0;
	private int writingWriters = 0;


	/**
	 * 获取读操作的锁
	 */
	public synchronized void readLock(){
		waitingReaders++;
		try {
			//如果有线程在进行写操作
			if (writingWriters > 0){
				this.wait();
			}
			//等待完，即拿到锁的权限，就进行读操作
			readingReaders++;
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			waitingReaders--;
		}
	}

	/**
	 * 放弃读操作的锁
	 */
	public synchronized void readUnLock(){
		readingReaders--;
		notifyAll();
	}


	/**
	 * 获取写操作的锁
	 */
	public synchronized void writerLock(){
		waitingWriters++;
		try {
			if (readingReaders >  0 || writingWriters > 0){
				this.wait();
			}
			//睡眠完，即拿到锁的权限，就进行写操作
			writingWriters++;
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			waitingWriters--;
		}
	}

	/**
	 * 放弃写操作的锁
	 */
	public synchronized void writerUnLock(){
		writingWriters--;
		notifyAll();
	}
}

