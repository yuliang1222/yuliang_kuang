/**
 * ClassName:wxTokenUtils
 * Author:Administrator
 * Date:2020/3/18 001817:09
 * Description:TODO
 */
package com.example.demo.wx.moban.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.wx.moban.config.WxAppPropert;
import com.example.demo.wx.moban.utils.HttpUtil;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/18 0018 17:09
 */
public class wxTokenUtils {


	public static  String wxTokenUtils(WxAppPropert wxAppPropert)  {
		//  获取 token
		String token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxAppPropert.getAppid()+ "&secret=" + wxAppPropert.getSecret();
		//  通过工具类获取 access_token
		String result = HttpUtil.doGet(token,new HashMap<String,Object>(),"");
		//  解析返回值
		JSONObject jsonObject = JSON.parseObject(result);
		//  拿到返回值
		String accesstoken = jsonObject.getString("access_token");
		//  抛出返回值
		System.out.println("token:  "+accesstoken);
		return accesstoken;
	}
}
