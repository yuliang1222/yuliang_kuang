/**
 * ClassName:wxMobanController
 * Author:Administrator
 * Date:2020/3/26 002613:23
 * Description:TODO
 */
package com.example.demo.wx.moban.controller;

import com.example.demo.rabbitmq.Wxsend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 13:23
 */
@RestController
@Slf4j
@RequestMapping("wxmb")
public class WxMobanController {


	@Autowired
	private Wxsend wxsend;

	@PostMapping(value = "/sendmsg" )
	public void sendMsg(@RequestBody HashMap<String,Object> sourceString) {
		wxsend.sendwxmsg(sourceString);
	}

}
