/**
 * ClassName:Wxsend
 * Author:Administrator
 * Date:2020/3/30 003019:08
 * Description:TODO
 */
package com.example.demo.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.wx.moban.feign.BaseFeign;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/30 0030 19:08
 */
@Component
public class Wxsend {

	@Resource(name="firstRabbitTemplate")
	private RabbitTemplate firstRabbitTemplate;

	@Autowired
	private BaseFeign baseFeign;


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
			firstRabbitTemplate.convertAndSend("wx_temp_msg_exchange", "wx_temp_msg_key.test", source);
			System.out.println("执行到----:"+i);
		}
	}
}
