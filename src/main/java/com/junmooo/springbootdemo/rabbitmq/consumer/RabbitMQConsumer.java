package com.junmooo.springbootdemo.rabbitmq.consumer;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.junmooo.springbootdemo.config.rabbitmq.RabbitMQConfig;


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
@Component @RabbitListener(queues = RabbitMQConfig.RABBITMQ_DEMO_TOPIC) public class RabbitMQConsumer
        implements Runnable {

    private final ThreadPoolExecutor executor;

    public RabbitMQConsumer(final ThreadPoolExecutor executor) {
        this.executor = executor;
    }
    @RabbitHandler public void process(Map map) {

        executor.execute(() -> {
            System.out.println("消费者RabbitDemoConsumer 从 RabbitMQ 消费" + map.toString());
            // 模拟耗时操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    @Override public void run() {

    }
}
