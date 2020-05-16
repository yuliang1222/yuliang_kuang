/**
 * ClassName:wxtemplateFactory
 * Author:Administrator
 * Date:2020/3/26 002618:18
 * Description:TODO
 */
package com.example.demo.wx.moban.factory.wxtemplate;

import com.example.demo.wx.moban.templateEnum.qiang_template_enum;
import com.example.demo.wx.moban.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 18:18
 */
@Component
public class WxtemplateFactory {
	private final Map<String,wxTemplate> wxTemplateMap = new ConcurrentHashMap<>();

	//获取
	public HashMap<String, Object> getTemp(String templateId, String wxAccounts) {
		for(String key : wxTemplateMap.keySet()){
			if (StringUtils.upperCase(key).startsWith(StringUtils.upperCase(wxAccounts))) {
				wxTemplate result = wxTemplateMap.get(key);
				return result.Temp(templateId);
			}
		}
		return null;
	}

	//将处罚对象注册到这里
	@Autowired
	public  void registerPunish(Map<String,wxTemplate> wxTemplateMap){
		this.wxTemplateMap.clear();
		wxTemplateMap.forEach((k, v)-> this.wxTemplateMap.put(k, v));
	}

	//新模板
	public HashMap<String, Object> getTemp1(String templateId, String wxAccounts) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		Class<?> aClass = Class.forName("com.example.demo.wx.moban.templateEnum."+wxAccounts+"_template_enum");
		Method getValue = aClass.getDeclaredMethod("getValue", String.class);
		Object[] enumConstants = aClass.getEnumConstants();
		String invoke = (String)getValue.invoke(enumConstants[0], templateId);
		return JsonUtils.nativeRead(invoke, new TypeReference<HashMap<String, Object>>() {
		});


	}
}

