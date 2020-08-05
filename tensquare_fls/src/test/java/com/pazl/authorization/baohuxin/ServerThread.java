package com.pazl.authorization.baohuxin;

/**
 * @author czd
 */
public class ServerThread extends Thread {
	private RequestQueue queue;
	private volatile boolean flag = true;
	public ServerThread(RequestQueue queue){
		this.queue = queue;
	}

	@Override
	public void run() {
		while (flag){
			Request request = queue.getRequest();
			if (request == null){
				System.out.println("request 是空的");
				continue;
			}

			System.out.println("Server>>>>>>" + request.getValue());
			try {
				Thread.sleep(100);
			}catch (Exception e){
				e.printStackTrace();
				return;
			}
		}
	}

	public void close(){
		this.flag = false;
		this.interrupt();
	}
}

