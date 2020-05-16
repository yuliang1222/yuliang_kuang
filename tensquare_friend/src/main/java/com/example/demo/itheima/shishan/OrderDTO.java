/**
 * ClassName:OrderDTO
 * Author:��е����
 * Date:2019/9/2213:31
 * Description:TODO
 */
package com.example.demo.itheima.shishan;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: yuliang
 * @Date: 2019/9/22 13:31
 */
@Data
public class OrderDTO {
	private String code;
	private BigDecimal price;
	private String type;
}


