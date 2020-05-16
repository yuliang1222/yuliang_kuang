/**
 * ClassName:FangshaInterceptor
 * Author:Administrator
 * Date:2019/12/9 000918:58
 * Description:TODO
 */
package com.example.demo.datasource.annotation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.CodeMsg;
import com.example.demo.utils.JsonUtils;
import com.example.demo.web.Fangsha;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuliang
 * @Date: 2019/12/9 0009 18:58
 */
@Component
@Configuration
@Slf4j
public class FangshaInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		FangshaAno annotation;
		if(handler instanceof HandlerMethod) {
			annotation = ((HandlerMethod) handler).getMethodAnnotation(FangshaAno.class);
		}else{
			return true;
		}
		if(annotation == null){
			return true;
		}
		if (annotation.cout() != null || annotation.key() != null) {
			int cout = Integer.parseInt(annotation.cout());
			String key = annotation.key();
			String fangsha = fangsha(key, cout);
				if (fangsha.equals("suc")) {
					return true;
				}
			}
		returnJson(response);
		return false;

	}

	private void returnJson(HttpServletResponse response) throws Exception{
		PrintWriter writer = null;
		JSONObject res = new JSONObject();
		res.put("success", false);
		res.put("message", "今天次数已经用完了！");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			writer = response.getWriter();
			writer.print(res);
		} catch (IOException e) {
			log.error("response error",e);
		} finally {
			if (writer != null){
				writer.close();
			}
		}
	}

	@Autowired
	private RedissonClient redissonClient;

	private String fangsha(String key,Integer cout) {
		String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		RMapCache<String, String> cmsmap = redissonClient.getMapCache(key);
		String cms1 = cmsmap.get("1000");
		//用户未请求，初始化。
		if (StringUtils.isBlank(cms1)) {
			Fangsha timestart = Fangsha.builder().cout(1).timestart(format).build();
			cmsmap.fastPut("1000",  JSON.toJSONString(timestart),1,TimeUnit.DAYS);
			return "suc";
	}
		Fangsha fangsha = JSON.parseObject(cms1, Fangsha.class);
		//用户第二天请求，次数，设置成0
		if (!format.equals(fangsha.getTimestart())) {
			fangsha.setCout(1);
			fangsha.setTimestart(format);
			boolean cms = cmsmap.fastPut("1000",  JSON.toJSONString(fangsha));
			return "suc";
		}
		if (fangsha.getCout() .equals(cout)) {
			return "fial";
		}
		int id = fangsha.getCout()+1;
		fangsha.setCout(id);
		boolean cms = cmsmap.fastPut("1000",  JSON.toJSONString(fangsha));
		System.out.println(JsonUtils.toString(fangsha));
		return "suc";
	}
}
