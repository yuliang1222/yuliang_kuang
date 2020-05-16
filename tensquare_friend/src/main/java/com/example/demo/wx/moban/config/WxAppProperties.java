/**
 * ClassName:WxConfig
 * Author:Administrator
 * Date:2020/3/26 002614:21
 * Description:TODO
 */
package com.example.demo.wx.moban.config;

import com.example.demo.datasource.properties.DataSourceProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 14:21
 */

@ConfigurationProperties(prefix = "weixin")
public class WxAppProperties {
	private Map<String, WxAppPropert> data = new LinkedHashMap<>();
	public Map<String, WxAppPropert> getData() {
		return data;
	}
	public void setData(Map<String, WxAppPropert> datasource) {
		this.data = datasource;
	}
}
