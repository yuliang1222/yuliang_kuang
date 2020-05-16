/**
 * ClassName:LinkendRunnableQueue
 * Author:Administrator
 * Date:2020/4/16 001618:14
 * Description:TODO
 */
package com.handWriteThreadpooll;

import java.util.LinkedList;

/**
 * @Author: yuliang
 * @Date: 2020/4/16 0016 18:14
 */
public class LinkendRunnableQueue implements RunnableQueue  {
	private final int limit;
	private final DenyPolicy denyPolicy;
	private final LinkedList<Runnable> runnableList = new LinkedList<>();

	private final ThreadPool threadPool;

	public LinkendRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
		this.limit = limit;
		this.denyPolicy = denyPolicy;
		this.threadPool = threadPool;
	}

	@Override
	public void offer(Runnable runnable) {
		synchronized (runnableList) {
			if (runnableList.size() >= limit){
				this.denyPolicy.reject(runnable, threadPool);
			}else {
				//将入到队列
				runnableList.addLast(runnable);
				runnableList.notifyAll();
			}
		}
	}

	@Override
	public Runnable task() {
		synchronized (runnableList) {
			while (runnableList.isEmpty()) {
				try {
					runnableList.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//从任务头移除一个任务
				return runnableList.removeFirst();
			}
		}
		return null;
	}

	@Override
	public int size() {
		return 0;
	}
}
