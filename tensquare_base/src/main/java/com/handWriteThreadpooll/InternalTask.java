/**
 * ClassName:InternalTask
 * Author:Administrator
 * Date:2020/4/16 001618:06
 * Description:TODO
 */
package com.handWriteThreadpooll;

/**
 * @Author: yuliang
 * @Date: 2020/4/16 0016 18:06
 */
public class InternalTask implements Runnable {

	private final RunnableQueue runnableQueue;

	private volatile boolean running = true;

	public InternalTask(RunnableQueue runnableQueue) {
		this.runnableQueue = runnableQueue;
	}
	//停止当前任务
	public void stop() {
		this.running = false;
	}
	@Override
	public void run() {
//当前任务为running并且没有被中断，则不从队列获取下一个任务，
		while (running && !Thread.currentThread().isInterrupted()) {
			try {
				Runnable task = runnableQueue.task();
				task.run();
			} catch (Exception e) {
				running = false;
				break;
			}
		}
	}
}
