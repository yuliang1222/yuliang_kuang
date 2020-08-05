/**
 * ClassName:wxTokenFactory
 * Author:Administrator
 * Date:2020/3/26 002618:06
 * Description:TODO
 */
package com.example.demo.wx.moban.factory;

import com.example.demo.wx.moban.config.WxAppPropert;
import com.example.demo.wx.moban.config.WxAppProperties;
import com.example.demo.wx.moban.utils.wxTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 18:06
 */
@Component
@EnableConfigurationProperties(WxAppProperties.class)
public class WxTokenFactory {
	@Autowired
	private WxAppProperties wxAppProperties;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String getToken(String wxAccounts) {
		String redisKey = wxAccounts + "_wx_access_token";
//		String token = stringRedisTemplate.opsForValue().get(redisKey);
		String token = stringRedisTemplate.opsForValue().get(redisKey);


		if (null == token) {
			WxAppPropert wxAppPropert = this.wxAppProperties.getData().get(wxAccounts);
			token = wxTokenUtils.wxTokenUtils(wxAppPropert);
			stringRedisTemplate.opsForValue().set(redisKey, token, 118, TimeUnit.MINUTES);
		} else {
			System.out.println("redis里面获取的token");
		}
		return token;
	}
}
