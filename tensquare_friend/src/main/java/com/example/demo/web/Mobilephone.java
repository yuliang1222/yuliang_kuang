/**
 * ClassName:Mobilephone
 * Author:机械革命
 * Date:2019/9/2712:52
 * Description:TODO
 */
package com.example.demo.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


/**
 * @Author: yuliang
 * @Date: 2019/9/27 12:52
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Mobilephone<T> extends base<T> {
	@Length(max = 2,message = "手机号超过三位")
	@NotBlank(message = "空空如也")
	private String mobileNo;


}
