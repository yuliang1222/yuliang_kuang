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

	@Bean("topicExchange")
	public Exchange directExchange() {
//		return ExchangeBuilder.directExchange("wx_temp_msg_exchange").durable(true).build();
		return ExchangeBuilder.topicExchange("wx_temp_msg_exchange").durable(true).build();
	}

	@Bean
	public Queue wxtempmsgQueue() {
		return new Queue("wx_temp_msg_queue",true);
	}

	@Bean
	public Binding wxtempmsgbinding(@Qualifier("wxtempmsgQueue") Queue queue, @Qualifier("topicExchange") Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("wx_temp_msg_key.#").noargs();
	}


	@Bean
	public  SimpleMessageListenerContainer wxSimpleMessageListenerContainer(@Qualifier("wxtempmsgQueue") Queue queue,@Qualifier("firstConnectionFactory") CachingConnectionFactory firstConnectionFactory) {
		SimpleMessageListenerContainer container = getContainer(firstConnectionFactory);
		container.setQueues(queue);
		container.setMessageListener(wxReciver);
		return container;
	}

	public SimpleMessageListenerContainer getContainer(@Qualifier("firstConnectionFactory") CachingConnectionFactory firstConnectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(firstConnectionFactory);
		container.setConcurrentConsumers(5);
		container.setMaxConcurrentConsumers(10);
		container.setPrefetchCount(2);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		return container;
	}

}
