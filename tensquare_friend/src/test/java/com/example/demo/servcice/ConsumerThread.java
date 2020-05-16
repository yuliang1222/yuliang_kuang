/**
 * ClassName:ConsumerThread
 * Author:机械革命
 * Date:2019/8/3015:33
 * Description:TODO
 */
package com.example.demo.servcice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yuliang
 * @Date: 2019/8/30 15:33
 */
public class ConsumerThread extends Thread{
	private BlockingQueue queue;
	private  static volatile boolean flag=true;
	private AtomicInteger count=new AtomicInteger();
	public ConsumerThread(BlockingQueue queue) {
		this.queue = queue;
	}
	public void run()  {
		System.out.println("消费者线程启动。。。。。。。");
		try{
			while (flag) {
				//获取完毕后，队列会删除头元素
				String data = (String) queue.poll(2, TimeUnit.SECONDS);
				if(data!=null){
					System.out.println(Thread.currentThread().getName()+"消费者获取data："+data+"成功！");
				} else{
					System.out.println(Thread.currentThread().getName()+"消费者获取data失败！");
					this.flag=false;
				}
				Thread.sleep(1000);
			}
		}  catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("消费者线程停止。。。。。。。。");
		}
	}
	public void stopThread(){
		this.flag=false;
	}

}
