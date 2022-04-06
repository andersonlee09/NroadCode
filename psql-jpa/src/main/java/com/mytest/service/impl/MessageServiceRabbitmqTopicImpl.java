package com.mytest.service.impl;

import com.mytest.service.MessageService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/4/1 13:27
 * @purpose null
 * @ModifiedRecords null
 */
@Service
public class MessageServiceRabbitmqTopicImpl implements MessageService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String id) {
        System.out.println("待发送短信的订单已纳入处理队列（rabbitmq topic），id：" + id);
        amqpTemplate.convertAndSend("topicExchange", "topic.orders.id", id);
    }
}