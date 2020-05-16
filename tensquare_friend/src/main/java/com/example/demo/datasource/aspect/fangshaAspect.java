/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.example.demo.datasource.aspect;



import com.alibaba.fastjson.JSON;
import com.example.demo.datasource.annotation.DataSource;
import com.example.demo.datasource.annotation.FangshaAno;
import com.example.demo.datasource.config.DynamicContextHolder;
import com.example.demo.utils.JsonUtils;
import com.example.demo.web.Fangsha;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.omg.PortableInterceptor.INACTIVE;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 多数据源，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class fangshaAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.example.demo.datasource.annotation.FangshaAno) " +
            "|| @within(com.example.demo.datasource.annotation.FangshaAno)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class targetClass = point.getTarget().getClass();
        Method method = signature.getMethod();
        FangshaAno targetDataSource = (FangshaAno) targetClass.getAnnotation(FangshaAno.class);
        FangshaAno methodDataSource = method.getAnnotation(FangshaAno.class);
        if (targetDataSource != null || methodDataSource != null) {
            String value;
            Integer value1;
            if (methodDataSource != null) {
                value = methodDataSource.key();
                value1 = Integer.parseInt(methodDataSource.cout());
                String fangsha = fangsha(value, value1);
                if (fangsha.equals("suc")) {
                    return point.proceed();
                } else {
                    return "fial  ee";
                }
            }
        }
	    return  point.proceed();


















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
            return "suc";
        }
        if (fangsha.getCout().equals(cout)) {
            return "fial";
        }
        int id = fangsha.getCout()+1;
        fangsha.setCout(id);
        boolean cms = cmsmap.fastPut("1000",  JSON.toJSONString(fangsha));
        System.out.println(JsonUtils.toString(fangsha));
        return "suc";
    }

}