/**
 * ClassName:StrategyImpl
 * Author:机械革命
 * Date:2019/9/2221:24
 * Description:TODO
 */
package com.example.demo.itheima.Strategy;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * vip用户
 * @author Bowin
 *
 */
@Service("vipMember")
public class VipMember  implements Strategy{

	@Override
	public BigDecimal calculatePrice() {
		return new BigDecimal("80");
	}

}