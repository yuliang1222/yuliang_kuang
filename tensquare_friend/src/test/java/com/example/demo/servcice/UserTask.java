/**
 * ClassName:UserTask
 * Author:机械革命
 * Date:2019/8/3015:07
 * Description:TODO
 */
package com.example.demo.servcice;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * @Author: yuliang
 * @Date: 2019/8/30 15:07
 */
public class UserTask {
	//队列大小
	private final int QUEUE_LENGTH = 10000*10;
	//基于内存的阻塞队列
	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>(QUEUE_LENGTH);
	//创建计划任务执行器
	private ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
	/**
	 * 构造函数，执行execute方法
	 */
	public UserTask() {
		execute();
	}
	/**
	 * 初始化执行
	 */
	public void execute() {
		//每一分钟执行一次
		es.scheduleWithFixedDelay(new Runnable(){
			public void run() {
				try {
					String content = queue.take();
					//处理队列中的信息。。。。。
					System.out.println(content);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, 0, 1, TimeUnit.MINUTES);
	}
	/**
	 * 添加信息至队列中
	 * @param content
	 */
	public void addQueue(String content) {
		queue.add(content);
	}
}
