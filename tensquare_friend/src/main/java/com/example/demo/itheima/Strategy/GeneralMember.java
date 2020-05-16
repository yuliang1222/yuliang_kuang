/**
 * ClassName:StrategyImpl
 * Author:机械革命
 * Date:2019/9/2221:23
 * Description:TODO
 */
package com.example.demo.itheima.Strategy;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * 普通用户
 * @author Bowin
 *
 */
@Service("generalMember")
public class GeneralMember implements Strategy {

	@Override
	public BigDecimal calculatePrice() {
		return new BigDecimal("100");
	}
}

