package com.pazl.authorization.lock;

/**
 * @author czd
 * 进行读操作的工作类
 */

public class ReadWorker extends Thread {
	private SharedData sharedData ;
	public ReadWorker(SharedData sharedData){
		this.sharedData = sharedData;
	}

	@Override
	public void run() {
		try {
			while(true){
				//进行读操作
				char [] chars = sharedData.read();
				System.out.println(Thread.currentThread().getName() + " read " + String.valueOf(chars) );
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

