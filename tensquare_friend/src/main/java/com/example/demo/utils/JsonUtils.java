package com.example.demo.utils;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;


/**
 * @author: li
 * @create: 2018-04-24 17:20
 **/
public class JsonUtils {
	public static String toString(Object object) {
		String string = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteDateUseDateFormat);
		return string;
	}

}
