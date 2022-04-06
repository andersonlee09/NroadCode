package com.mytest.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/4/1 9:15
 * @purpose null
 * @ModifiedRecords null
 */
@Component
public class MessageListener {
    @RabbitListener(queues = "topic_queue")
    public void receive(String id){
        System.out.println("已完成短信发送业务--------id："+id);
    }


    @RabbitListener(queues = "topic_queue2")
    public void receive2(String id){
        System.out.println("已完成短信发送业务+++++++id："+id);
    }


}