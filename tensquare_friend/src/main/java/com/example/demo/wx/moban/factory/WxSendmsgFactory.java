/**
 * ClassName:wxTokenFactory
 * Author:Administrator
 * Date:2020/3/26 002618:06
 * Description:TODO
 */
package com.example.demo.wx.moban.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.wx.moban.config.WxAppProperties;
import com.example.demo.wx.moban.feign.BaseFeign;
import com.example.demo.wx.moban.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 18:06
 */
@Component
@Slf4j
public class WxSendmsgFactory {
	@Autowired
	private WxAppProperties wxAppProperties;

	@Autowired
	private BaseFeign baseFeign;

	public String sendMsg(String token, HashMap<String, Object> temp ) {
//		baseFeign.sendMsg(String token, HashMap<String, Object> temp )
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
		String jsonString = JSON.toJSONString(temp);
		String resultstring = HttpUtil.doPost(url, jsonString);
		JSONObject jsonObject = JSONObject.parseObject(resultstring);
		if (null != jsonObject) {
			if (0 == jsonObject.getInteger("errcode")) {
				log.info("微信成功返回结果:  {}",jsonObject.toJSONString());
				return "0";
			}
		}
		log.info("微信失败返回结果:  {}",jsonObject.toJSONString());
		return "1";

	}





}