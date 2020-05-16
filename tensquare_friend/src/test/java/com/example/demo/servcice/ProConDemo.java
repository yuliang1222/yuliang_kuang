/**
 * ClassName:ProConDemo
 * Author:机械革命
 * Date:2019/8/3015:29
 * Description:TODO
 */
package com.example.demo.servcice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: yuliang
 * @Date: 2019/8/30 15:29
 */
public class ProConDemo {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue=new LinkedBlockingQueue<>(500);
		BlockingQueue<String> queue1=new LinkedBlockingQueue<>(500);
		ProducerThread p1=new ProducerThread(queue);
		ProducerThread p2=new ProducerThread(queue1);
		ConsumerThread c1=new ConsumerThread(queue);
		ConsumerThread c2=new ConsumerThread(queue1);
		ConsumerThread c3=new ConsumerThread(queue);
		ConsumerThread c4=new ConsumerThread(queue);
		p1.start();
		p2.start();
//		c1.start();
//		c2.start();
//		c3.start();
//		c4.start();
		Thread.sleep(10*1000);
		p1.stopThread();
		p2.stopThread();
	}


}
