/**
 * ClassName:IOrderService2Impl
 * Author:��е����
 * Date:2019/9/2213:46
 * Description:TODO
 */
package com.example.demo.itheima.shishan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

/**
 * @Author: yuliang
 * @Date: 2019/9/22 13:46
 */
public class IOrderService2Impl implements IOrderService {

	@Autowired
//	private HandlerContext handlerContext;

	@Override
	public String handle(OrderDTO orderDTO) {
//		handlerContext.getInstance(orderDTO.getType());

		return null;
	}
}
