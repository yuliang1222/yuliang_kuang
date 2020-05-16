/**
 * ClassName:WeixinLoginProperties
 * Author:Administrator
 * Date:2020/3/23 002310:45
 * Description:TODO
 */
package com.example.demo.wx.login;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: yuliang
 * @Date: 2020/3/23 0023 10:45
 */
@Component
@ConfigurationProperties(prefix="weixinconfig")
public class WeixinLoginProperties {
	private String weixinappID; // 商户appid

	private String weixinappSecret; // 私钥 pkcs8格式的

	public String getWeixinappID() {
		return weixinappID;
	}

	public void setWeixinappID(String weixinappID) {
		this.weixinappID = weixinappID;
	}

	public String getWeixinappSecret() {
		return weixinappSecret;
	}

	public void setWeixinappSecret(String weixinappSecret) {
		this.weixinappSecret = weixinappSecret;
	}

}
