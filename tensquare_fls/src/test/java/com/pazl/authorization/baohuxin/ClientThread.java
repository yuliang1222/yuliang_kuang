package com.pazl.authorization.baohuxin;

/**
 * @author czd
 */
public class ClientThread extends Thread {
	private RequestQueue queue ;
	private String sendValue;

	public ClientThread(RequestQueue requestQueue , String sendValue){
		this.queue = requestQueue;
		this.sendValue = sendValue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++){
			System.out.println("Client >>>>> request >>>>>" + sendValue);
			queue.putRequest(new Request(sendValue));
			try {
				Thread.sleep(100);
			}catch (Exception e){
				e.printStackTrace();
			}

		}
	}
}

