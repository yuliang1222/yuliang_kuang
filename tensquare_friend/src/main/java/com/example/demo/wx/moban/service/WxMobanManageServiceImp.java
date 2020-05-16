/**
 * ClassName:wxMobanServiceImp
 * Author:Administrator
 * Date:2020/3/26 002615:02
 * Description:TODO
 */
package com.example.demo.wx.moban.service;

import com.example.demo.wx.moban.factory.WxMsgBodyFactory;
import com.example.demo.wx.moban.factory.WxSendmsgFactory;
import com.example.demo.wx.moban.factory.WxTokenFactory;
import com.example.demo.wx.moban.factory.openid.WxOpenidFactory;
import com.example.demo.wx.moban.factory.wxtemplate.WxtemplateFactory;
import com.example.demo.wx.moban.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 15:02
 */
@Service
@Slf4j
public class WxMobanManageServiceImp {

	@Autowired
	private WxMsgBodyFactory wxMsgBodyFactory;
	@Autowired
	private WxOpenidFactory wxOpenidFactory;
	@Autowired
	private WxtemplateFactory wxtemplateFactory;
	@Autowired
	private WxTokenFactory wxTokenFactory;
	@Autowired
	private WxSendmsgFactory wxSendmsgFactory;



	public String sendWxMsg(String sourstring) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		HashMap<String, Object> source = JsonUtils.nativeRead(sourstring, new TypeReference<HashMap<String, Object>>(){});
		String templateId =(String) source.get("template_id");
		String wxAccounts =(String) source.get("wxAccounts");
		// 1 获取模板
		// 2. 修改休息体
		// 3. 拿到openid
		// 4. 拿到token
		// 5. 调用微信接口
		StopWatch sw = new StopWatch();
		sw.start();
		HashMap<String, Object> temp = wxtemplateFactory.getTemp1(templateId, wxAccounts);
		sw.stop();
		System.out.println("反射运行时间"+sw.getTime());
		 wxMsgBodyFactory.getBody(temp, source);
		wxOpenidFactory.getOpenid(wxAccounts, temp);
		String token = wxTokenFactory.getToken(wxAccounts);
		String sendResult = wxSendmsgFactory.sendMsg(token, temp);

		return sendResult;
	}


}
