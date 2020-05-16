package com.example.demo;

import com.example.demo.wx.moban.config.WxAppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class })

@EnableTransactionManagement(order = 2)
@EnableSwagger2
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties(WxAppProperties.class)
@EnableFeignClients
@SpringCloudApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
