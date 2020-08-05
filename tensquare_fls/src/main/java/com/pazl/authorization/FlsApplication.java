/**
 * ClassName:management
 * Author:Administrator
 * Date:2020/5/23 002316:11
 * Description:TODO
 */
package com.pazl.authorization;

/**
 * @Author: yuliang
 * @Date: 2020/5/23 0023 16:11
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;





//@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class })

@EnableTransactionManagement(order = 2)
@EnableSwagger2
@EnableAsync
@EnableScheduling
@EnableFeignClients
@SpringCloudApplication
@MapperScan("com.pazl.authorization.mapper")
@EnableRetry(proxyTargetClass = true)
public class FlsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlsApplication.class, args);
	}

}
