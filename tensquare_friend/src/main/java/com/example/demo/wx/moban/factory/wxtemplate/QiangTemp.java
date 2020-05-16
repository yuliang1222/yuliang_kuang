/**
 * ClassName:QiangTemp
 * Author:Administrator
 * Date:2020/3/26 002622:33
 * Description:TODO
 */
package com.example.demo.wx.moban.factory.wxtemplate;

import com.example.demo.wx.moban.templateEnum.qiang_template_enum;
import com.example.demo.wx.moban.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 22:33
 */
@Service("QiangTemp")
public class QiangTemp implements wxTemplate {

	@Override
	public HashMap<String, Object> Temp(String template_id) {
		return JsonUtils.nativeRead(qiang_template_enum.getValue(template_id), new TypeReference<HashMap<String, Object>>() {
		});
	}
}
