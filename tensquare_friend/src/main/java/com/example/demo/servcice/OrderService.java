package com.example.demo.servcice;


import com.example.demo.constant.Constants;
import com.example.demo.entity.BrokerMessageLog;
import com.example.demo.entity.Order;
import com.example.demo.mapper.BrokerMessageLogMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.producer.RabbitOrderSender;
import com.example.demo.utils.FastJsonConvertUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.OptionalLong;
import java.util.stream.LongStream;

//@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private BrokerMessageLogMapper brokerMessageLogMapper;
	
	@Autowired
	private RabbitOrderSender rabbitOrderSender;
	
	
	public void createOrder(Order order) throws Exception {
		// order current time 
		Date orderTime = new Date();
		// order insert
		orderMapper.insert(order);
		// log insert
		BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
		brokerMessageLog.setMessageId(order.getMessageId());
		//save order message as json
		brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(order));
		brokerMessageLog.setStatus("0");
		brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
		brokerMessageLog.setCreateTime(new Date());
		brokerMessageLog.setUpdateTime(new Date());
		brokerMessageLogMapper.insert(brokerMessageLog);
		// order message sender
		rabbitOrderSender.sendOrder(order);
	}

	public static void main(String[] args) {
		Instant start = Instant.now();
		//使用StreamAPI
		OptionalLong result = LongStream.rangeClosed(0L, 50000000000L)
				.parallel()
				.reduce(Long::sum);
		System.out.println(result.getAsLong());
		Instant end = Instant.now();
		System.out.println("五百亿求和耗费的时间为: " + Duration.between(start, end).toMillis());


	}
	
}
