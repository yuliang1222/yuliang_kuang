/**
 * ClassName:RunnableQueue
 * Author:Administrator
 * Date:2020/4/16 001616:50
 * Description:TODO
 */
package com.handWriteThreadpooll;

/**
 * @Author: yuliang
 * @Date: 2020/4/16 0016 16:50
 */
public interface RunnableQueue {

	void offer(Runnable offer);

	Runnable task();

	int size();
}
