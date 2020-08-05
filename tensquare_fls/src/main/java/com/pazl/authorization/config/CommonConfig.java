/**
 * ClassName:CommonConfig
 * Author:Administrator
 * Date:2020/5/23 002321:38
 * Description:TODO
 */
package com.pazl.authorization.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;

/**
 * @Author: yuliang
 * @Date: 2020/5/23 0023 21:38
 */
@Configuration
public class CommonConfig {

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//单个文件最大
		factory.setMaxFileSize(10);
		//设置总上传数据的总大小
		factory.setMaxRequestSize(10);
		return factory.createMultipartConfig();
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
