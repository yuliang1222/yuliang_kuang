/**
 * ClassName:EmailListener
 * Author:机械革命
 * Date:2019/9/2222:47
 * Description:TODO
 */
package com.example.demo.itheima.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author administrator
 */
@Slf4j
@Component
public class EmailListener implements ApplicationListener<OrderObservable> {

	@Override
	@Async
	public void onApplicationEvent(OrderObservable orderObservable) {
		log.info(Thread.currentThread() + "用户下单成功！手机收到邮件!内容为{}", orderObservable.getMessage());
	}
}
