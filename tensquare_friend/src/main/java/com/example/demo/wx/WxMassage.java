package com.example.demo.wx;///**
// * ClassName:WxMassage
// * Author:Administrator
// * Date:2020/3/18 001817:28
// * Description:TODO
// */
//package com.example.demo.wx;
//
//import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.Logger;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.example.demo.wx.moban.utils.HttpUtil;
//import com.example.demo.wx.moban.utils.wxTokenUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.Field;
//import java.util.*;
//
///**
// * @Author: yuliang
// * @Date: 2020/3/18 0018 17:28
// */
//@Slf4j
//public class WxMassage {
//	// 发起救援
//	public static Integer send_template_messageStart(String openId, MessageTemplate messageTemplate, String access_token) throws IllegalAccessException {
//		// 配置推送连接
//		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
//		WechatTemplate wechatTemplate = new WechatTemplate();
//		//设置模板id
//		wechatTemplate.setTemplate_id("H6qtCQu7XClgQPQtOhDm1QSNFN-f_8Bdn9HUZb4iTN8");  //需要替换成自己的
//		wechatTemplate.setTouser(openId);
//		wechatTemplate.setTopcolor("#FF0000");
//		//模板消息点击跳转路径, 如果不配置跳转小程序,可以设置跳转该路径  生产
////		wechatTemplate.setUrl("https://cheguanjia.pingan.com.cn/pazl-wechat/fls/index.html?SK_channelid=qzgx");
//		// uat
//		wechatTemplate.setUrl("https://www.pgyer.com/HPv2");
//
//		// 配置模版消息
//		Map<String, TemplateData> m = new HashMap<String,TemplateData>();
//		Class cls = messageTemplate.getClass();
//		Field[] fields = cls.getDeclaredFields();
//		for(int i=0; i<fields.length; i++){
//			Field f = fields[i];
//			f.setAccessible(true);
//			System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(messageTemplate));
//			if (null != f.get(messageTemplate)) {
//				TemplateData   templateData= new TemplateData();
//				templateData.setColor("#173177");
//				templateData.setValue( f.get(messageTemplate).toString());
//				m.put(f.getName() , templateData);
//			}
//		}
//		wechatTemplate.setData(m);
//
//		String jsonString = JSON.toJSONString(wechatTemplate);
//		String resultstring = HttpUtil.doPost(url, jsonString);
//		JSONObject jsonObject = JSONObject.parseObject(resultstring);
//		System.out.println("消息模版："+jsonObject);
//		log.info("消息模版："+jsonObject);
//		int result = 0;
//		if (null != jsonObject) {
//			if (0 != jsonObject.getInteger("errcode")) {
//				result = jsonObject.getInteger("errcode");
//			}
//		}
//		return result;
//	}
//
//
//
//	public static void main(String[] args) throws Exception {
//
//		Set<String> loggers = new HashSet<>(Arrays.asList("org.apache.http"));
//		for (String log : loggers) {
//			Logger logger = (Logger) LoggerFactory.getLogger(log);
//			logger.setLevel(Level.INFO);
//			logger.setAdditive(false);
//		}
//
//		String accessToken = wxTokenUtils.wxTokenUtils();
//		MessageTemplate messageTemplate = new MessageTemplate();
//		messageTemplate.setFirst("尊敬的用户");
//		messageTemplate.setKeyword1("Keyword1111");
//		messageTemplate.setKeyword2("Keyword22222");
//		messageTemplate.setKeyword3("Keyword33333");
//		messageTemplate.setKeyword4("Keyword444444");
//		messageTemplate.setKeyword5("Keyword55555");
//		messageTemplate.setKeyword6("Keyword55555");
//		messageTemplate.setRemark("2012355959");
//
////		getField(messageTemplate);
////		send_template_messageStart("oTnQ3wD3Ox7WG4Kj01n0lXYNPU6Q",messageTemplate,accessToken);
//		send_template_messageStart("oTnQ3wAw1vSjJ_wnwz1PMaCC25ro",messageTemplate,accessToken);
//	}
//
//}
