/**
 * ClassName:EarlyPunish
 * Author:机械革命
 * Date:2019/9/2710:02
 * Description:TODO
 */
package com.example.demo.itheima.strategy1;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author: yuliang
 * @Date: 2019/9/27 10:02
 */
@Component("EarlyPunish")
public class EarlyPunish  implements IPunish {
	@Override
	public void exePunish() {
		System.out.println("罚1001");
	}


}
