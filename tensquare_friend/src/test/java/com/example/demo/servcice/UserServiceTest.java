package com.example.demo.servcice;

import com.example.demo.FriendApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: yuliang
 * @Date: 2019/10/18 14:04
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
	@Autowired
	private BookService bookService;

	@Test
	public void   selectbook() {
		bookService.selectbook();
		System.out.println();
	}
}
