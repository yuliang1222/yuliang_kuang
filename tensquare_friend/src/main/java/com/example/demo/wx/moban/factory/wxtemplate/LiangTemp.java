/**
 * ClassName:LiangTemp
 * Author:Administrator
 * Date:2020/3/26 002622:32
 * Description:TODO
 */
package com.example.demo.wx.moban.factory.wxtemplate;

import com.example.demo.wx.moban.templateEnum.liang_template_enum;
import com.example.demo.wx.moban.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 22:32
 */
@Service("LiangTemp")
public class LiangTemp implements wxTemplate {

	@Override
	public HashMap<String, Object> Temp(String template_id) {
		return JsonUtils.nativeRead(liang_template_enum.getValue(template_id), new TypeReference<HashMap<String, Object>>() {
		});
	}
}
