/**
 * ClassName:StatusMoveController
 * Author:Administrator
 * Date:2020/5/16 001610:59
 * Description:TODO
 */
package com.examp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2020/5/16 0016 10:59
 */
@RestController("/status")
@Slf4j

public class StatusMoveController {

	@Autowired
	private CmsDetailMapper cmsDetailMapper;


	@PostMapping("/select/book")
	public void see() {
		List<CmsDetail> cmsDetails = cmsDetailMapper.selectList(null);
		cmsDetails.forEach(System.out::println);
	}

}
