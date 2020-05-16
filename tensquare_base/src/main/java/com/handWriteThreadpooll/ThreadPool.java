/**
 * ClassName:ThreadPool
 * Author:Administrator
 * Date:2020/4/16 001616:31
 * Description:TODO
 */
package com.handWriteThreadpooll;

/**
 * @Author: yuliang
 * @Date: 2020/4/16 0016 16:31
 */
public interface ThreadPool {
	void execute(Runnable runnable);

	void shutdown();

	int getInitSize();

	int getMaxsize();

	int getCoreSize();

	int getQuenSize();

	int getActiveCount();

	boolean isShutdown();

}
