/**
 * ClassName:PunishFactory
 * Author:机械革命
 * Date:2019/9/2710:05
 * Description:TODO
 */
package com.example.demo.itheima.strategy1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: yuliang
 * @Date: 2019/9/27 10:05
 */
@Component
public class PunishFactory {
	private final Map<String,IPunish> punishMap = new ConcurrentHashMap<>();
	private static final IPunish EMPTY = new EmptyPunish();
	private PunishFactory() {}

	//获取
	public  IPunish getPunish(String state) {
		IPunish result = punishMap.get(state);
		return result == null ? EMPTY : result;
	}

	//将处罚对象注册到这里
	@Autowired
	public  void registerPunish(Map<String,IPunish> punishmap){
		this.punishMap.clear();
		punishmap.forEach((k, v)-> this.punishMap.put(k, v));
	}

	private static class EmptyPunish implements IPunish {
		@Override
		public void exePunish() {
			System.out.println("无");
			// Empty class
		}
	}


}
