package com.pazl.authorization.baohuxin;

import java.util.LinkedList;

/**
 * @author czd
 */
public class RequestQueue {
	private LinkedList<Request> queue = new LinkedList<Request>();
	public Request getRequest(){
		synchronized (queue){
			while (queue.size() <= 0){
				try {
					queue.wait();
				}catch (Exception e){
					e.printStackTrace();
					return null;
				}
			}
			return queue.removeFirst();
		}
	}

	public void putRequest(Request request){
		synchronized (queue){
			queue.addLast(request);
			queue.notifyAll();
		}
	}
}

