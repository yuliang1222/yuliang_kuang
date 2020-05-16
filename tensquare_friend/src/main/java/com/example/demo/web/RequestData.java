/**
 * ClassName:RequestData
 * Author:机械革命
 * Date:2019/9/2713:06
 * Description:TODO
 */
package com.example.demo.web;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * @Author: yuliang
 * @Date: 2019/9/27 13:06
 */
@Data
public class RequestData<T> {

//	@NotBlank
//	@NotNull(message = "空空如也")
	@NotNull(message = "空空如也")
	private int data;

	private Integer version;


}