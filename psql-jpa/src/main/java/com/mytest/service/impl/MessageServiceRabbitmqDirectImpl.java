package com.mytest.service.impl;

import com.mytest.service.MessageService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/4/1 9:12
 * @purpose null
 * @ModifiedRecords null
 */
@Service
public class MessageServiceRabbitmqDirectImpl {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String id) {
        System.out.println("待发送短信的订单已纳入处理队列（rabbitmq direct），id：" + id);
        // 第一个参数是交换方式，，这里是直接交换
        amqpTemplate.convertAndSend("directExchange", "direct", id);
    }
}