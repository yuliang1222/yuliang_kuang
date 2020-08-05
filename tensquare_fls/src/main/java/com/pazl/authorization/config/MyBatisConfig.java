/**
 * ClassName:MyBatisConfig
 * Author:Administrator
 * Date:2020/5/24 002419:31
 * Description:TODO
 */
package com.pazl.authorization.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: yuliang
 * @Date: 2020/5/24 0024 19:31
 */
@Slf4j
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

@Autowired
	@Qualifier("druiddataSource")
	private DruidDataSource druidDataSource;

	@Bean(name = "masterSSF")
	public SqlSessionFactory masterSSF(	) throws IOException {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(druidDataSource);
//		bean.setTypeHandlersPackage();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("pageSizeZero", "true");
		properties.setProperty("supportMethodsArguments", "false");
		properties.setProperty("returnPageInfo", "none");
		PageInterceptor pageInterceptor = new PageInterceptor();
		pageInterceptor.setProperties(properties);
		SqlStatementInterceptor sqlStatementInterceptor = new SqlStatementInterceptor();
		ResultSqlInterceptor resultSqlInterceptor = new ResultSqlInterceptor();
		bean.setPlugins(new Interceptor[]{pageInterceptor,sqlStatementInterceptor,resultSqlInterceptor});
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(pathMatchingResourcePatternResolver.getResources("classpath*:mapper/*.xml"));
		try {
			return bean.getObject();
		} catch (Exception e) {
			log.error("init SqlSessionFactory failed",e );
			throw new RuntimeException(e);
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("masterSSF") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(druidDataSource);
	}
}
