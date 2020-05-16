package com.example.demo.servcice;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.entity.Order;
import com.example.demo.exceptions.DataTooLongException;
import com.example.demo.exceptions.GlobalException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.OrientationRequested;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2019/6/19 12:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UseServiceTest {
	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private StringRedisTemplate redisTemplate;
	@Test
	@Async
	public void  selec1t(){
		redisTemplate.opsForValue().set("aaa","bbb" );
		System.out.println(redisTemplate.opsForValue().get("aaa"));
	}






	@Test
	public void  insertuser(){
		userService.insertuser();
	}
	@Test
	public void  select(){
//		List<String> myList = Arrays.asList("111111","211111113");
//		List<User> users = userMapper.UserById(myList);

		List<String> selectby = userMapper.selectby("123","远");
		System.out.println();
//		log.info(JSON.toJSONString(users, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));
	}
	@Test
	public void  selt(){
		JSONObject jsonObject = JSONObject.parseObject("     ");
		User user = new User("a3411", "", new Date());
		log.info(JSON.toJSONString(user, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteDateUseDateFormat));
		System.out.println(jsonObject);
	}



	@Test
@Transactional
	public void setttid() {

		User user = new User("a3411", "", new Date());
		userMapper.insertuser(user);

		Order order = Order.builder().messageId("2").name("dslf").build();
		int i = orderMapper.insertSelective(order);
	}
}
