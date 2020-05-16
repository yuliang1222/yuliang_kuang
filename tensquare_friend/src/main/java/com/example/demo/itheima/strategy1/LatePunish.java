/**
 * ClassName:LatePunish
 * Author:机械革命
 * Date:2019/9/279:55
 * Description:TODO
 */
package com.example.demo.itheima.strategy1;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author: yuliang
 * @Date: 2019/9/27 9:55
 */
@Component("LatePunish")
public class LatePunish implements IPunish{

	@Override
	public void exePunish() {
		System.out.println("罚100");
	}



}
