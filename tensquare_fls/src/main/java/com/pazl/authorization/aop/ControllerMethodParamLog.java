/**
 * ClassName:ControllerMethodParamLog
 * Author:Administrator
 * Date:2020/5/23 002316:12
 * Description:TODO
 */
package com.pazl.authorization.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yuliang
 * @Date: 2020/5/23 0023 16:12
 */
@Component
@Aspect
@Slf4j
public class ControllerMethodParamLog {
	@Pointcut("execution(* com.pazl.authorization.controllers..*(..))")
	public void aspect() {

	}

	@Around("aspect()")
	public Object before(ProceedingJoinPoint point) throws Throwable {
		//获取方法
		ServletRequestAttributes sra =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		//访问路径
		String requestPath = request.getRequestURI();
		String className = point.getSignature().getDeclaringType().getSimpleName();
		String methodName = point.getSignature().getName();

		MethodSignature signature = (MethodSignature) point.getSignature();
//		String urlDes = signature.getMethod().getAnnotation(ApiOperation.class).value();
		//请求路径,类名,方法名
		log.info("\n----------请求路径:"+requestPath
						+"\n-----------类名:"+className
						+"\n-----------方法名:"+methodName
//						+"\n-----------方法描述:"+urlDes
						+"\n-----------参数:"+getArgs(point));
		Object proceed = point.proceed();
		log.info("\n---------请求结束====返回值: \n" + JSONObject.toJSONString(proceed));
		return proceed;
	}

	private String getArgs(JoinPoint point) {
		String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <parameterNames.length ; i++) {
			sb.append(parameterNames[i] + ":" + point.getArgs()[i].toString() + ";");
		}
		return sb.toString();

	}
}
