/**
 * ClassName:RabbitmqConfigure
 * Author:Administrator
 * Date:2020/3/30 003018:05
 * Description:TODO
 */
package com.example.demo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yuliang
 * @Date: 2020/3/30 0030 18:05
 */
@Configuration
public class RabbitmqConfigure {

	@Bean(name="firstConnectionFactory")
	@Primary
	public CachingConnectionFactory firstConnectionFactory(
			@Value("${spring.rabbitmq.first.host}") String host,
			@Value("${spring.rabbitmq.first.port}") int port,
			@Value("${spring.rabbitmq.first.username}") String username,
			@Value("${spring.rabbitmq.first.password}") String password,
			@Value("${spring.rabbitmq.first.virtual-host}") String virtualHost
	){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
//    #确认消息已发送到交换机(Exchange)
		connectionFactory.setPublisherConfirms(true);
//    #确认消息已发送到交换机(Exchange)
		connectionFactory.setPublisherReturns(true);
		return connectionFactory;
	}

	@Bean(name="secondConnectionFactory")
	public CachingConnectionFactory secondConnectionFactory(
			@Value("${spring.rabbitmq.second.host}") String host,
			@Value("${spring.rabbitmq.second.port}") int port,
			@Value("${spring.rabbitmq.second.username}") String username,
			@Value("${spring.rabbitmq.second.password}") String password,
			@Value("${spring.rabbitmq.second.virtual-host}") String virtualHost
	){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		return connectionFactory;
	}

	@Bean(name="firstRabbitTemplate")
	@Primary
	public RabbitTemplate firstRabbitTemplate(
			@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory
	){
		RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
		firstRabbitTemplate.setMandatory(true);
		firstRabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
				System.out.println("ConfirmCallback:     "+"确认情况："+ack);
				System.out.println("ConfirmCallback:     "+"原因："+cause);
			}
		});

		firstRabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				System.out.println("ReturnCallback:     "+"消息："+message);
				System.out.println("ReturnCallback:     "+"回应码："+replyCode);
				System.out.println("ReturnCallback:     "+"回应信息："+replyText);
				System.out.println("ReturnCallback:     "+"交换机："+exchange);
				System.out.println("ReturnCallback:     "+"路由键："+routingKey);
			}
		});

		return firstRabbitTemplate;
	}

	@Bean(name="secondRabbitTemplate")
	public RabbitTemplate secondRabbitTemplate(
			@Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory
	){
		RabbitTemplate secondRabbitTemplate = new RabbitTemplate(connectionFactory);
		return secondRabbitTemplate;
	}



//	@Resource(name="firstConnectionFactory")
//	private CachingConnectionFactory firstConnectionFactory;

	@Autowired
	private WxReciver wxReciver;
	@Autowired
	private WxReciver1 wxReciver1;
	@Bean("topicExchange")
	public FanoutExchange directExchange() {
//		return ExchangeBuilder.directExchange("wx_temp_msg_exchange").durable(true).build();
//		return ExchangeBuilder.topicExchange("wx_temp_msg_exchange").durable(true).build();
		return new FanoutExchange("wx_temp_msg_exchange");
	}

	@Bean
	public Queue wxtempmsgQueue() {
		return new Queue("wx_temp_msg_queue",true,false,false);
	}
	@Bean
	public Queue wxtempmsgQueue1() {
		return new Queue("wx_temp_msg_queue1",true,false,false);
	}
	@Bean
	public Binding wxtempmsgbinding(@Qualifier("wxtempmsgQueue") Queue queue, @Qualifier("topicExchange") Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("wx_temp_msg_key.#").noargs();
	}
//	@Bean
//	public Binding wxtempmsgbinding(@Qualifier("wxtempmsgQueue") Queue queue, @Qualifier("topicExchange") FanoutExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange);
//	}
	@Bean
	public Binding wxtempmsgbinding1(@Qualifier("wxtempmsgQueue1") Queue queue, @Qualifier("topicExchange") FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}
	//配置死信队列wx_temp_msg_queue
	@Bean
	public Queue deadLetterQueue() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-message-ttl", 10000);
		args.put("x-dead-letter-exchange", "wx_temp_msg_exchange");
		args.put("x-dead-letter-routing-key", "wx_temp_msg_key.#");
		return new Queue("deadLetterQueue", true, false, false, args);
	}

	@Bean
	public  SimpleMessageListenerContainer wxSimpleMessageListenerContainer(@Qualifier("wxtempmsgQueue") Queue queue,@Qualifier("firstConnectionFactory") CachingConnectionFactory firstConnectionFactory) {
		SimpleMessageListenerContainer container = getContainer(firstConnectionFactory);
		container.setQueues(queue);
		container.setMessageListener(wxReciver);
		return container;
	}
	@Bean
	public  SimpleMessageListenerContainer wxSimpleMessageListenerContainer2(@Qualifier("wxtempmsgQueue1") Queue queue,@Qualifier("firstConnectionFactory") CachingConnectionFactory firstConnectionFactory) {
		SimpleMessageListenerContainer container = getContainer(firstConnectionFactory);
		container.setQueues(queue);
		container.setMessageListener(wxReciver1);
		return container;
	}
	public SimpleMessageListenerContainer getContainer(@Qualifier("firstConnectionFactory") CachingConnectionFactory firstConnectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(firstConnectionFactory);
		container.setConcurrentConsumers(5);
		container.setMaxConcurrentConsumers(10);
		container.setPrefetchCount(0);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		return container;
	}

}
