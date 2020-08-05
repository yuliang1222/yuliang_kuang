package com.pazl.authorization.lock;

/**
 * @author czd
 * 运行读写锁分离的类，看结果
 */
public class ReaderWriterClient {
	public static void main(String[] args) {
		SharedData sharedData = new SharedData(10);
		new ReadWorker(sharedData).start();
		new ReadWorker(sharedData).start();
		new ReadWorker(sharedData).start();
		new ReadWorker(sharedData).start();
		new ReadWorker(sharedData).start();
		new WriterWorker(sharedData , "qwertyuiop").start();
		new WriterWorker(sharedData , "QWERTYUIOP").start();
	}
}

