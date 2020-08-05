package com.pazl.authorization.lock;

/**
 * @author czd
 * 共享数据的类，模拟数据
 */
public class SharedData {
	private char[] data;
	private ReaderWriterLock lock = new ReaderWriterLock();

	/**
	 * 初始化数据，都为 '*'
	 * @param size
	 */
	public SharedData(int size){
		data = new char[size];
		for (int i = 0; i < size; i++){
			data[i] = '*';
		}
	}

	/**
	 * 读取数据的方法
	 * @return
	 */
	public char[] read(){
		try {
			lock.readLock();
			return doRead();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			lock.readUnLock();
		}
		return data;
	}

	private char[] doRead(){
		char [] newData = new char[data.length];
		for (int i = 0; i < data.length; i++){
			newData[i] = data[i];
		}
		try {
			Thread.sleep(100);
		}catch (Exception e){
			e.printStackTrace();
		}
		return newData;
	}


	/**
	 * 写数据的方法
	 * @param c
	 */
	public void write(char c){
		try {
			lock.writerLock();
			doWrite(c);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			lock.writerUnLock();
		}
	}

	private void doWrite(char c){
		for (int i = 0; i < data.length; i++){
			data[i] = c;
			try {
				Thread.sleep(10);
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	}

}

