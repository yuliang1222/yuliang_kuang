/**
 * ClassName:Wxsend
 * Author:Administrator
 * Date:2020/3/30 003019:08
 * Description:TODO
 */
package com.example.demo.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.wx.moban.feign.BaseFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: yuliang
 * @Date: 2020/3/30 0030 19:08
 */
@Slf4j
@Component
public class Wxsend {

	@Resource(name="firstRabbitTemplate")
	private RabbitTemplate firstRabbitTemplate;

	@Autowired
	private BaseFeign baseFeign;
	final static   	String rex = "(?<=\\(#).*?(?=#\\))";
	final static   	String pre = "\\(#";
	final static   	String suf = "#\\)";
	final static   	 Pattern pattern = Pattern.compile(rex);
	public static void main(String[] args) {


		//                          2020        )sdf(28)sadsa(06)
		String aa = "'(#  YYYY  #))sdf(#dd#))sadsa(((#dd#))'";
		String s = replaceTime(aa);
		System.out.println("最后的结果==="+s);
	}
	private static String replaceTime(String aa ) {
		Matcher matcher = pattern.matcher(aa);
		if(matcher.find()) {
			System.out.println(matcher.group());
			String s = aa.replaceFirst(pre+rex+suf, LocalDateTime.now().format(DateTimeFormatter.ofPattern(matcher.group())));
			System.out.println(s);
			return replaceTime(s);

		}
		return aa;
	}




	public void sendwxmsg(HashMap<String,Object> msg) {
		Integer count =Integer.valueOf((String)msg.get("count"));
		String sleep =(String)msg.get("sleep");
//		for (int i = 0; i < count; i++) {
//			String msg1 = baseFeign.sendMsg(sleep);
//			String s = msg1.equals("0") ? "成功" : "失败";
//			System.out.println("capp的返回结果----:"+s+"capp的下标:"+i);
//		}
		for (int i = 0; i < count; i++) {
			HashMap<String,String> tempData = (HashMap<String,String>) msg.get("data");
			tempData.put("remark", String.valueOf(i));
			msg.put("data", tempData);
			String source = JSONObject.toJSONString(msg);
//			firstRabbitTemplate.convertAndSend("wx_temp_msg_exchange", "wx_temp_msg_key.test", source);
			firstRabbitTemplate.convertAndSend("deadLetterQueue",  source);
//			firstRabbitTemplate.convertAndSend("wx_temp_msg_exchange", "", source);
			log.info("执行到----:{}",i);
		}
	}
}
