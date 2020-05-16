/**
 * ClassName:AsyncConfig
 * Author:机械革命
 * Date:2019/11/2621:47
 * Description:TODO
 */
package com.tensquare.controller.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: yuliang
 * @Date: 2019/11/26 21:47
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {
	private Logger logger = LogManager.getLogger();

	@Value("${thread.pool.corePoolSize:5}")
	private int corePoolSize;

	@Value("${thread.pool.maxPoolSize:8}")
	private int maxPoolSize;

	@Value("${thread.pool.keepAliveSeconds:4}")
	private int keepAliveSeconds;

	@Value("${thread.pool.queueCapacity:512}")
	private int queueCapacity;

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("base-server");
		executor.setBeanName("base-server1");
		executor.setThreadGroupName("base-server2");
//		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

		executor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor exe) -> {
			logger.warn("当前任务线程池队列已满.");
		});
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				logger.error("线程池执行任务发生未知异常.", ex);
			}
		};
	}
}
