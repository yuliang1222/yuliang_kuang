/**
 * ClassName:WxResendFactory
 * Author:Administrator
 * Date:2020/3/26 002623:39
 * Description:TODO
 */
package com.example.demo.wx.moban.service;

import com.example.demo.wx.moban.factory.WxMsgBodyFactory;
import com.example.demo.wx.moban.factory.WxSendmsgFactory;
import com.example.demo.wx.moban.factory.WxTableOperationFactory;
import com.example.demo.wx.moban.factory.WxTokenFactory;
import com.example.demo.wx.moban.factory.openid.WxOpenidFactory;
import com.example.demo.wx.moban.factory.wxtemplate.WxtemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 23:39
 */
@Service
public class WxResendServiceImpl {
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
	@Autowired
	private WxTableOperationFactory wxTableOperationFactory;

	// 定时任务15分钟
	public void reSendWxMsg() {
		List<HashMap<String, Object>> failMsgListist = wxTableOperationFactory.SelectFailMsgList();
		for (HashMap<String, Object> map : failMsgListist) {
			String wxAccounts =(String) map.get("wxAccounts");
			HashMap<String, Object>temp =(HashMap<String, Object>) map.get("temp");
			HashMap<String, Object>source =(HashMap<String, Object>) map.get("source");
			String token = wxTokenFactory.getToken(wxAccounts);
//			JSONObject sendResult = wxSendmsgFactory.sendMsg(token, temp);
//			if ( sendResult.getInteger("errcode") == 0) {
//				wxTableOperationFactory.SaveSentSuccessMsg(JSONObject.toJSONString(source));
//				wxTableOperationFactory.updateFailMsgStatus(source);
//				if ( sendResult.getInteger("errcode") == 1) {
//				wxTableOperationFactory.updateFailMsgCount(temp);
//			}


//		}
	}



	}
}
