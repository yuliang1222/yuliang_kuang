/**
 * ClassName:Strategy
 * Author:机械革命
 * Date:2019/9/2221:23
 * Description:TODO
 */
package com.example.demo.itheima.Strategy;

import java.math.BigDecimal;

/**
 * @Author: yuliang
 * @Date: 2019/9/22 21:23
 */
public interface Strategy {
	/**
	 * 共同行为
	 * @return
	 */
	BigDecimal calculatePrice();
}