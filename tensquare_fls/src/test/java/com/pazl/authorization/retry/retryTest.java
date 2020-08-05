/**
 * ClassName:retryTest
 * Author:Administrator
 * Date:2020/7/28 002810:31
 * Description:TODO
 */
package com.pazl.authorization.retry;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryState;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

import java.util.stream.IntStream;

/**
 * @Author: yuliang
 * @Date: 2020/7/28 0028 10:31
 */
@Slf4j
public class retryTest {

	@Test
	public void retryFixTimes() throws Exception {
		RetryTemplate retryTemplate = new RetryTemplate();
		SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
		simpleRetryPolicy.setMaxAttempts(6);
		retryTemplate.setRetryPolicy(simpleRetryPolicy);
		System.out.println(run(retryTemplate));
	}
	private Integer run(RetryTemplate retryTemplate) throws Exception {
		Integer result = retryTemplate.execute(new RetryCallback<Integer, Exception>() {
			int i = 0 ;
			@Override
			public Integer doWithRetry(RetryContext retryContext) throws Exception {
				retryContext.setAttribute("value",i );
				System.out.println("准备发送");

				return len(i++);
			}
		}, new RecoveryCallback<Integer>() {
			@Override
			public Integer recover(RetryContext retryContext) throws Exception {
				System.out.println("另一个策略");
				return Integer.MAX_VALUE;
			}
		});
		return -1;
	}
	private int len(int i) throws Exception {
		if (i < 5) throw new Exception(i + " le 5");
		System.out.println("发生完成");
		return i;
	}


	@Test
	public void TimeoutRetryPolicy() throws Exception {
		RetryTemplate retryTemplate = new RetryTemplate();
		TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
		timeoutRetryPolicy.setTimeout(1000);
		retryTemplate.setRetryPolicy(timeoutRetryPolicy);
		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(400);
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
		System.out.println(run(retryTemplate));
	}

	@Test
	public void retryWithResult() throws Exception {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(new AlwaysRetryPolicy() {
		private static final long serialVersionUID = 1213824522266301314L;
		@Override
		public boolean canRetry(RetryContext context) {
//小于1则重试
				return context.getAttribute("value") == null || Integer.parseInt(context.getAttribute("value").toString()) < 1;
			}
		});
		run(retryTemplate);
	}

	@Test
	public void retryCircuitBreakerTest() {
		RetryTemplate retryTemplate = new RetryTemplate();
		CircuitBreakerRetryPolicy retryPolicy =
				new CircuitBreakerRetryPolicy(new SimpleRetryPolicy(5));
		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(300);
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
		retryPolicy.setOpenTimeout(2100);
		retryPolicy.setResetTimeout(2500);
		retryTemplate.setRetryPolicy(retryPolicy);


		IntStream.range(0, 20).forEach(index -> {
			try {
				Thread.sleep(200);
				RetryState state = new DefaultRetryState("circuit", false);
				String result = retryTemplate.execute(new RetryCallback<String, RuntimeException>() {
					long startTime = System.currentTimeMillis();
					@Override
					public String doWithRetry(RetryContext context) throws RuntimeException {
						log.info("retry {} times", context.getRetryCount());

						if (
								(System.currentTimeMillis() - startTime > 1000 && System.currentTimeMillis() - startTime < 1500)
								||
										(System.currentTimeMillis() - startTime > 2100 && System.currentTimeMillis() - startTime < 2500)

								) {
							return "success";
						}
						System.out.println(System.currentTimeMillis() - startTime  );

						throw new RuntimeException("timeout");

					}

				}, new RecoveryCallback<String>() {
					@Override
					public String recover(RetryContext context) throws Exception {
						return "default";
					}
				}, state);

				log.info("result: {}", result);
			} catch (Exception e) {

				log.error("error: {}", e.getMessage());
			}
		});
	}
}