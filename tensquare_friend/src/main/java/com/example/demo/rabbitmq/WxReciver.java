/**
 * ClassName:WxReciver
 * Author:Administrator
 * Date:2020/3/30 003019:09
 * Description:TODO
 */
package com.example.demo.rabbitmq;

import com.example.demo.wx.moban.factory.WxTableOperationFactory;
import com.example.demo.wx.moban.service.WxMobanManageServiceImp;
import com.example.demo.wx.moban.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/30 0030 19:09
 */

@Component
@Slf4j
@RabbitListener(queues = "wx_temp_msg_queue" ,containerFactory="firstFactory")
public class WxReciver implements ChannelAwareMessageListener {

	@Autowired
	private WxMobanManageServiceImp wxMobanManageServiceImp;

	@Autowired
	private WxTableOperationFactory wxTableOperationFactory;

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {

		long deliveryTag = message.getMessageProperties().getDeliveryTag();
		String sourstring = new String(message.getBody());

		System.out.println("线程名字----"+Thread.currentThread().getName());
		wxTableOperationFactory.SaveSentSuccessMsg(sourstring);
		String i = wxMobanManageServiceImp.sendWxMsg(sourstring);
		channel.basicAck(deliveryTag, false);
		String s = i.equals("0") ? "成功" : "失败";

		HashMap<String, Object> source = JsonUtils.nativeRead(sourstring, new TypeReference<HashMap<String, Object>>(){});
		HashMap<String,String> sourceData = (HashMap<String,String>)source.get("data");

		log.info("capp的返回结果----:"+s+"capp的下标:"+sourceData.get("remark")	);
//			System.out.println(deliveryTag+"消息重入队列"+message.getMessageProperties().isRedelivered());
//			channel.basicAck(deliveryTag, false);

	}
}
