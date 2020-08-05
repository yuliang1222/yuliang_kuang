/**
 * ClassName:StatusMoveController
 * Author:Administrator
 * Date:2020/5/16 001610:59
 * Description:TODO
 */
package com.example.demo.web.statusMoveController;

import com.example.demo.entity.Book;
import com.example.demo.rabbitmq.WxReciver;
import com.example.demo.servcice.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author: yuliang
 * @Date: 2020/5/16 0016 10:59
 */
@RestController("/status")
@Slf4j

@Api(value = "用户信息管理")
public class StatusMoveController {
	@Autowired
	private WxReciver wxReciver;

	@Autowired
	private BookService bookService;

	@ApiOperation(value = "获取用户信息", notes = "通过用户姓名获取用户信息")
	@PostMapping("/select/book")
//    @FangshaAno(key = "中",cout = "10"  )
	public Book  selectbook() {
		Book selectbook = bookService.selectbook();
		return selectbook;
	}

	@ApiOperation(value = "获取用户信息", notes = "通过用户姓名获取用户信息")
	@PostMapping("/select/book1")
//    @FangshaAno(key = "中",cout = "10"  )
	public Book  selectbook1() throws InvocationTargetException, IllegalAccessException {
		wxReciver.lock();
		return null;
	}



}
