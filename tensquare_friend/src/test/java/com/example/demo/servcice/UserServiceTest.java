package com.example.demo.servcice;

import com.example.demo.DemoApplication;
import com.example.demo.wx.moban.config.WxAppPropert;
import com.example.demo.wx.moban.config.WxAppProperties;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author: yuliang
 * @Date: 2019/10/18 14:04
 */
@Slf4j
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

}
