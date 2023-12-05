package com.junmooo.springbootdemo.service.rabbitmq;

/**
 * <p>
 * RabbitMQService
 * </p>
 *
 * @author qingbguo(qingbguo @ paypal.com)
 * @version 1.0.0
 * @copyright Copyright(c) 2011-2020, Gopay All Rights Reserved.
 * @date 2023/12/5 11:34
 */
public interface RabbitMQService {
    String sendMsg(String msg);
}
