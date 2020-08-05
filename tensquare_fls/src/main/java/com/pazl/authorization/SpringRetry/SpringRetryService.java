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
public class SpringRetryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringRetryService.class);

	@Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay =1000L))
	public void run() throws Exception {
		LOGGER.info("do sth");
		throw new Exception("erro" +
				"r");
	}

	@Recover
	private void recover2(Exception e) {
		LOGGER.info("invoke recover1");
	}
	@Recover
	private void recover1(Exception e) {
		LOGGER.info("invoke recover2");
	}



}
