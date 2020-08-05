package com.pazl.authorization.lock;

/**
 * @author czd
 * 进行写操作的工作类
 */
public class WriterWorker extends Thread {
	/**
	 * filler：模拟数据，可以是字符串，字符等虚拟数据
	 */
	private SharedData sharedData ;
	private String filler;
	private int index = 0;

	public WriterWorker(SharedData sharedData , String filler){
		this.sharedData = sharedData;
		this.filler = filler;
	}

	public char nextChar(){
		char c = filler.charAt(index);
		index++;
		if(index >= filler.length()){
			index = 0;
		}
		return c;
	}
	@Override
	public void run() {
		try {
			while (true){
				//进行写操作
				char c =nextChar();
				sharedData.write(c);
				Thread.sleep(100);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}

