/**
 * ClassName:wxMsgBodyFactory
 * Author:Administrator
 * Date:2020/3/26 002617:50
 * Description:TODO
 */
package com.example.demo.wx.moban.factory;

import com.example.demo.exceptions.GlobalException;
import com.example.demo.utils.CodeMsg;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 17:50
 */
@Component
public class WxMsgBodyFactory {

	//将mq消费的shuju封装到 模板里面 (内容方法是唯一的  ,微信的格式是定的....)
	public HashMap<String, Object> getBody(HashMap<String, Object> temp,HashMap<String, Object> source ) {
		try {
			HashMap<String,HashMap<String,String>> tempData = (HashMap<String, HashMap<String, String>>) temp.get("data");
			HashMap<String,String> sourceData = (HashMap<String,String>)source.get("data");
			for(String key : sourceData.keySet()){
				tempData.get(key).put("value", sourceData.get(key));
			}
			temp.put("data", tempData);
		} catch (Exception e) {
			new GlobalException(new CodeMsg(10000, e.getMessage()));
		}

		return temp;
	}


}
