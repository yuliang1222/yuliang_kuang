/**
 * ClassName:SpringRetryService
 * Author:Administrator
 * Date:2020/7/29 002911:25
 * Description:TODO
 */
package com.pazl.authorization.SpringRetry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/**
 * @Author: yuliang
 * @Date: 2020/7/29 0029 11:25
 */
@Component
public class SpringRetryService1 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringRetryService1.class);

	@Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay =1000L))
	public void run() throws Exception {
		LOGGER.info("do sth1");
		throw new Exception("error");
	}

	@Recover
	private void recover(Exception e) {
		LOGGER.info("invoke recover1");
	}
}
