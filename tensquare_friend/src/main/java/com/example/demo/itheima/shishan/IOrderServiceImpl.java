/**
 * ClassName:IOrderServiceImpl
 * Author:��е����
 * Date:2019/9/2213:36
 * Description:TODO
 */
package com.example.demo.itheima.shishan;

/**
 * @Author: yuliang
 * @Date: 2019/9/22 13:36
 */
public class IOrderServiceImpl implements IOrderService {
	@Override
	public String handle(OrderDTO dto) {
		String type = dto.getType();
		if ("1".equals(type)) {
			return "������";
		} else if ("2".equals(type)) {
			return "�����Ŷ���";
		} else if ("3".equals(type)) {
			return "����";
		}
		return null;
	}
}
