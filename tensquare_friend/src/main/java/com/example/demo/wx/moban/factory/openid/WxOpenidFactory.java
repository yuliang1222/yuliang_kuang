/**
 * ClassName:wxOfficial
 * Author:Administrator
 * Date:2020/3/26 002615:10
 * Description:TODO
 */
package com.example.demo.wx.moban.factory.openid;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 15:10
 */
@Component
@Slf4j
public class WxOpenidFactory {
	private final Map<String,Openid> openidMap = new ConcurrentHashMap<>();

	//获取
	public HashMap<String, Object> getOpenid(String wxAccounts, HashMap<String, Object> temp) {
		for(String key : openidMap.keySet()) {
			if (StringUtils.upperCase(key).equals(StringUtils.upperCase(wxAccounts+"openid"))) {
				Openid result = openidMap.get(key);
				String openid = result.getOpenid();
				temp.put("touser", openid);
				return temp;
			}
		}
		return null;
	}

	//将处罚对象注册到这里
	@Autowired
	public  void registerPunish(Map<String,Openid> openidMap){
		this.openidMap.clear();
		openidMap.forEach((k, v)-> this.openidMap.put(k, v));
	}


}
