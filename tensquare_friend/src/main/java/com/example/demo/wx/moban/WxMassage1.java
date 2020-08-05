/**
 * ClassName:WxMassage
 * Author:Administrator
 * Date:2020/3/18 001817:28
 * Description:TODO
 */
package com.example.demo.wx.moban;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.wx.moban.templateEnum.liang_template_enum;
import com.example.demo.wx.moban.utils.HttpUtil;
import com.example.demo.wx.moban.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Author: yuliang
 * @Date: 2020/3/18 0018 17:28
 */
@Slf4j
public class WxMassage1 {
	// 发起救援




	//第一车网
	public static void main1(String[] args) throws Exception {
		Date date = new Date();
		String time = String.valueOf(date.getTime());
		String uriBuilder = new URIBuilder("http://rpcapi.ceshi.che300.com").setPath("/api/companyapi/pingan/getInfo").setParameter("order_id", "123").setParameter("app_key", "198988532").setParameter("timestamp", time).setParameter("assesmentFlg", "1").build().toString();
		String s = HttpUtil.doGet(uriBuilder);
		System.out.println(s);
	}
     //侄信
	public static void main(String[] args) throws Exception {
		Date date = new Date();
		String time = String.valueOf(date.getTime());
		String uriBuilder = new URIBuilder("http://appdemo.outsourceax.net:33083").setPath("/api/companyapi/pingan/getInfo").setParameter("order_id", "123").setParameter("app_key", "198988532").setParameter("timestamp", time).setParameter("assesmentFlg", "1").build().toString();
		String s = HttpUtil.doGet(uriBuilder);
		System.out.println(s);
	}

	public static Integer send_template_messageStart(HashMap<String, Object> messageTemplate, String access_token) throws IllegalAccessException {
		// 配置推送连接
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
		String jsonString = JSON.toJSONString(messageTemplate);
		String resultstring = HttpUtil.doPost(url, jsonString);
		JSONObject jsonObject = JSONObject.parseObject(resultstring);
		System.out.println("消息模版："+jsonObject);
		log.info("消息模版："+jsonObject);
		int result = 0;
		if (null != jsonObject) {
			if (0 != jsonObject.getInteger("errcode")) {
				result = jsonObject.getInteger("errcode");
			}
		}
		return result;
	}

	public static void main32(String[] args) throws Exception {
		long startTime=System.currentTimeMillis();
		System.out.println("执行代码块/方法");
		logbugimoger();
//		JSONObject source = JsonUtils.jshuju("mobansource.json", new TypeReference<JSONObject>() {
//		});
		String sourstring = "{\n" +
				"  \"template_id\": \"1\",\n" +
				"  \"mobile\": \"18204586\",\n" +
				"  \"data\": {\n" +
				"    \"first\": \"黄先生\",\n" +
				"    \"keyword1\":\"0426\",\n" +
				"    \"keyword2\": \"06月07日 19时24分\",\n" +
				"    \"keyword3\":\"消费\",\n" +
				"    \"keyword4\":\"人民币260.00元\",\n" +
				"    \"keyword5\":\"06月07日19时24分\",\n" +
				"    \"remark\":\"6504.09\"\n" +
				"  }\n" +
				"}";

		HashMap<String, Object> source = JsonUtils.nativeRead(sourstring, new TypeReference<HashMap<String, Object>>(){});

		String template_id =(String) source.get("template_id");
		HashMap<String, Object> jshuju = JsonUtils.nativeRead(liang_template_enum.getValue(template_id), new TypeReference<HashMap<String, Object>>() {
		});
		HashMap<String,HashMap<String,String>> moban = (HashMap<String, HashMap<String, String>>) jshuju.get("data");
		HashMap<String,String> monbansource = (HashMap<String,String>)source.get("data");
		for(String key1 : monbansource.keySet()){
			moban.get(key1).put("value", monbansource.get(key1));
		}
		jshuju.put("data", moban);
//		String accessToken = wxTokenUtils.wxTokenUtils();
		String accessToken = "dlksfjsldkf";

		jshuju.put("touser", "ogIK3tyDeD7pw7Io6g95yha0ReBE");
		send_template_messageStart(jshuju,accessToken);
		jshuju.put("touser", "ogIK3t8x75xjGMxrO0loBEjnn9Yw");
		send_template_messageStart(jshuju,accessToken);
		long endTime=System.currentTimeMillis();
		System.out.println("程序运行时间： "+(endTime - startTime)+"ms");
	}

	private static void logbugimoger() {
		Set<String> loggers = new HashSet<>(Arrays.asList("org.apache.http"));
		for (String log : loggers) {
			Logger logger = (Logger) LoggerFactory.getLogger(log);
			logger.setLevel(Level.INFO);
			logger.setAdditive(false);
		}
	}

}
