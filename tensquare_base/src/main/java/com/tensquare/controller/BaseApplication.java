package com.tensquare.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;


//@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class })

@EnableAsync
//@EnableScheduling
@EnableFeignClients
@SpringCloudApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

}
