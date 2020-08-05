/**
 * ClassName:WxReciver
 * Author:Administrator
 * Date:2020/3/30 003019:09
 * Description:TODO
 */
package com.example.demo.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.example.demo.web.hashmap.MenuDO;
import com.example.demo.wx.moban.factory.WxTableOperationFactory;
import com.example.demo.wx.moban.service.WxMobanManageServiceImp;
import com.example.demo.wx.moban.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2020/3/30 0030 19:09
 */

@Component
@Slf4j
@RabbitListener(queues = "wx_temp_msg_queue", containerFactory = "firstFactory")
public class WxReciver implements ChannelAwareMessageListener {
	@Autowired
	private RedissonClient redissonClient;

	@Autowired
	private WxMobanManageServiceImp wxMobanManageServiceImp;

	@Autowired
	private WxTableOperationFactory wxTableOperationFactory;

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {

		long deliveryTag = message.getMessageProperties().getDeliveryTag();
		String sourstring = new String(message.getBody());

		System.out.println("线程名字-22222---" + Thread.currentThread().getName());
		lock();

//		wxTableOperationFactory.SaveSentSuccessMsg(sourstring);
//		String i = wxMobanManageServiceImp.sendWxMsg(sourstring);
		channel.basicAck(deliveryTag, false);
//		String s = i.equals("0") ? "成功" : "失败";
		String s = "0".equals("0") ? "成功" : "失败";

		HashMap<String, Object> source = JsonUtils.nativeRead(sourstring, new TypeReference<HashMap<String, Object>>() {
		});
		HashMap<String, String> sourceData = (HashMap<String, String>) source.get("data");

		log.info("capp的返回结果----:" + s + "capp的下标:" + sourceData.get("remark"));
//			System.out.println(deliveryTag+"消息重入队列"+message.getMessageProperties().isRedelivered());
//			channel.basicAck(deliveryTag, false);

	}

	public void lock() throws InvocationTargetException, IllegalAccessException {

		List<MenuDO> menuList = new ArrayList<MenuDO>() {
			{
				add(new MenuDO(11L, null, "安徽", "www.baidu.ocm"));
				add(new MenuDO(44L, 33L, "小米", "www.xiaomi.ocm"));
				add(new MenuDO(55L, null, "雄安是", "www.xionga.ocm"));
				add(new MenuDO(88L, 55L, "小且", "www.xiaoqie.ocm"));
				add(new MenuDO(22L, 11L, "六安", "www.liuan.ocm"));
				add(new MenuDO(33L, 11L, "合肥", "www.hefei.ocm"));
			}
		};
//		dewightorintersectionList( menuList, "tts","parentId");

		RMapCache<String, String> cmsmap = redissonClient.getMapCache("cms");
		String cms11 = cmsmap.get("cms1");
		cmsmap.fastPut("cms1", JSON.toJSONString(menuList));
//		String cms2 = cmsmap.remove("cms1");
//		cmsmap.fastRemove();



		String cms1 = cmsmap.get("cms1");
		List<MenuDO> menuVOSlist = JSON.parseArray(cms1, MenuDO.class);

//		List<MenuVO> menuVOS = hashmaptest.buildMenuTree(menuVOSlist);
//		log.info("menuVOS={}", JsonUtils.toString(menuVOS));
//		boolean expire = map.expire(100, TimeUnit.SECONDS);


//		RMapCache<Object, Object> cmsmapresult = redissonClient.getMapCache("cms");
//		RSetCache<Object> cmsmapresultset = redissonClient.getSetCache("cms");
//		boolean add = cmsmapresultset.add(menuList, 1, TimeUnit.DAYS);
//		RSetCache<String> setcms = redissonClient.getSetCache("setcms");
//		setcms.add("180126447896", 15, TimeUnit.MINUTES);
//		setcms.add("180126447826", 15, TimeUnit.MINUTES);
//		setcms.add("180126447896", 15, TimeUnit.MINUTES);
//		setcms.remove("180126447826");

	}

}
