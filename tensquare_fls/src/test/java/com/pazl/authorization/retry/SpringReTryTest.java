package com.pazl.authorization.retry;

import com.pazl.authorization.FlsApplication;
import com.pazl.authorization.SpringRetry.SpringRetryService;
import com.pazl.authorization.danliduoli.TestService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: yuliang
 * @Date: 2020/7/20 0020 13:44
 */
@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlsApplication.class)
public class SpringReTryTest {
	@Autowired
	TestService2 testService2;
	@Autowired
	TestService2 testService3;
	@Autowired
	private SpringRetryService springRetryService;

	@Test
	public void retryWithAnnotation() throws Exception {
		springRetryService.run();
	}


}