/**
 * ClassName:StrategyImpl
 * Author:机械革命
 * Date:2019/9/2221:31
 * Description:TODO
 */
package com.example.demo.itheima.Strategy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略管理器
 * @author Bowin
 *
 */
@Component
public class StrategyContext {
	/**
	 *使用线程安全的ConcurrentHashMap存储所有实现Strategy接口的Bean
	 *key:beanName
	 *value：实现Strategy接口Bean
	 */
	private final Map<String, Strategy> strategyMap = new ConcurrentHashMap<>();

	/**
	 * 注入所有实现了Strategy接口的Bean
	 * @param strategyMap
	 */
	@Autowired
	public StrategyContext(Map<String, Strategy> strategyMap) {
		this.strategyMap.clear();
		strategyMap.forEach((k, v)-> this.strategyMap.put(k, v));
	}

	/**
	 * 计算价格
	 * @param memberLevel   会员等级
	 * @return              价格
	 */
	public BigDecimal calculatePrice(String memberLevel) {

		//在这里要捕捉异常，或者是做相应处理，因为如果找不到service名字
		//会报错，也就是找不到对应的策略
		if(!StringUtils.isEmpty(memberLevel)){
			return strategyMap.get(memberLevel).calculatePrice();
		}
		return null;
	}


	/**
	 * 计算价格
	 * @param memberLevel   会员等级
	 * @return              价格
	 */
	public Strategy createMember(String memberLevel) {

		//在这里要捕捉异常，或者是做相应处理，因为如果找不到service名字
		//会报错，也就是找不到对应的策略
		if(!StringUtils.isEmpty(memberLevel)){
			return strategyMap.get(memberLevel);
		}
		return null;
	}
}