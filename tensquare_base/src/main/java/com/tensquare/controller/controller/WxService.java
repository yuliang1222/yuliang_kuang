/**
 * ClassName:WxService
 * Author:Administrator
 * Date:2020/4/10 001014:24
 * Description:TODO
 */
package com.tensquare.controller.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utils.HttpUtil;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/4/10 0010 14:24
 */
@Service
@Slf4j
public class WxService {
	public String getResult( HashMap<String, Object> temp) {
		log.info("接收到访问!!!-----sourceString");
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + temp.get("token");
		temp.remove("token");
		String jsonString = JSON.toJSONString(temp);
		String resultstring = HttpUtil.doPost(url, jsonString);
		JSONObject jsonObject = JSONObject.parseObject(resultstring);
		HashMap<String,Object> sourceData = (HashMap<String,Object>)temp.get("data");
		HashMap<String,String> remark = (HashMap<String,String>)sourceData.get("remark");
		if (null != jsonObject) {
			if (0 == jsonObject.getInteger("errcode")) {
				log.info("访问成功真的成功!!!-----={}",remark.get("value"));
				return "0";
			}
		}
		log.info("访问假成功!!!-----={}",remark.get("value"));
		log.info("返回结果{}",jsonObject.toJSONString());
		return "1";
	}

}
