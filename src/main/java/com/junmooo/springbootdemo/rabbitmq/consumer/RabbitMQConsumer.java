package com.junmooo.springbootdemo.rabbitmq.consumer;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.junmooo.springbootdemo.config.rabbitmq.RabbitMQConfig;
import com.junmooo.springbootdemo.service.demo.HelloService;


/**
 * <p>
 * RabbitMQConsumer
 * </p>
 *
 * @author qingbguo(qingbguo @ paypal.com)
 * @version 1.0.0
 * @copyright Copyright(c) 2011-2020, Gopay All Rights Reserved.
 * @date 2023/12/5 13:17
 */
@Component @RabbitListener(queues = RabbitMQConfig.RABBITMQ_DEMO_TOPIC) public class RabbitMQConsumer{
    Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
        @RabbitHandler
        @Async("myThreadPoolTaskExecutor")
        public void process(Map<String,Object> testMessage) {
            logger.info("DirectReceiver消费者收到消息  : " + testMessage.toString());
        }
}
