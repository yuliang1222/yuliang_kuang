/**
 * ClassName:baseFeign
 * Author:Administrator
 * Date:2020/4/9 000914:30
 * Description:TODO
 */
package com.example.demo.wx.moban.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/4/9 0009 14:30
 */
@Component
@FeignClient(value = "tensquare-base", fallbackFactory = BaseFeignImpl.class)
public interface BaseFeign {

	@PostMapping("/wxmb/sendmsg")
	public String sendMsg(@RequestBody   HashMap<String, Object> temp ) ;


}
