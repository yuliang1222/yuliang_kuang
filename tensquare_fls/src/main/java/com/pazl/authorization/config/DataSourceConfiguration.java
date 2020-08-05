package com.pazl.authorization.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.beans.PropertyVetoException;
@Configuration
public class DataSourceConfiguration {
	/**
	 *  数据库驱动
	 */
	@Value("${spring.datasource.druid.driver-class-name}")
	private String jdbcDriver;

	/**
	 *  数据库地址
	 */
	@Value("${spring.datasource.druid.url}")
	private String jdbcUrl;

	/**
	 * 数据库连接名
	 */
	@Value("${spring.datasource.druid.username}")
	private String jdbcUsername;

	/**
	 * 数据库连接密码
	 */
	@Value("${spring.datasource.druid.password}")
	private String jdbcPassword;

	/**
	 * 返回数据库连接池
	 */
	@Primary
	@Bean(name = "druiddataSource")
	public DruidDataSource createDateSource() throws PropertyVetoException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(jdbcDriver);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setInitialSize(5);
		//关闭连接后不自动commit
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}
}