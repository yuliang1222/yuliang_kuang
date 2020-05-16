/**
 * ClassName:baseFeignImpl
 * Author:Administrator
 * Date:2020/4/9 000914:34
 * Description:TODO
 */
package com.example.demo.wx.moban.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/4/9 0009 14:34
 */
@Component
@Slf4j
public class BaseFeignImpl implements FallbackFactory<BaseFeign> {


	@Override
	public BaseFeign create(Throwable throwable) {
		return new BaseFeign() {
			@Override
			public String sendMsg(HashMap<String, Object> temp) {
				log.info("熔断服务==={}", throwable);
				return "1";
			}
		};
	}
}