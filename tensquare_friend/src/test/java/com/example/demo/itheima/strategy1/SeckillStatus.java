/**
 * ClassName:SeckillStatus
 * Author:机械革命
 * Date:2019/11/2016:05
 * Description:TODO
 */
package com.example.demo.itheima.strategy1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: yuliang
 * @Date: 2019/11/20 16:05
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeckillStatus implements Serializable {
	 private String username;
	 private Date createTime;
	 //秒杀状态 1:排队中，2:秒杀等待支付,3:支付超时，4:秒杀失败,5:支付完成
	private Integer status; //秒杀的商品ID
	 private Long goodsId; //应付金额
	 private Float money; //订单号
	 private String orderId; //时间段
	 private String time;

	public SeckillStatus(String username, Date createTime, Integer status, Long goodsId, String time) {
		this.username = username; this.createTime = createTime;
		this.status = status; this.goodsId = goodsId; this.time = time; }
}
