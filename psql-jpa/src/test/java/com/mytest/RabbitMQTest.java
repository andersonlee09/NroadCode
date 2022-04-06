package com.mytest;

import com.mytest.service.MessageService;
import com.mytest.service.impl.MessageServiceRabbitmqDirectImpl;
import com.mytest.service.impl.MessageServiceRabbitmqTopicImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/4/1 9:47
 * @purpose null
 * @ModifiedRecords null
 */

@SpringBootTest
public class RabbitMQTest {
    private final MessageService directSender = new MessageServiceRabbitmqDirectImpl();
    private final MessageService topicSender = new MessageServiceRabbitmqTopicImpl();
    private final ExecutorService executor = new ThreadPoolExecutor(20, 40, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100));

    @Test
    public void testDirect() throws Exception {
        for (int i = 0; i < 100; i++) {
            directSender.sendMessage("11");
//            executor.submit(() -> {
//                for (int j = 0; j < 20; j++) {
//                    directSender.sendMessage("test" + j);
//                }
//            });
        }
    }

    @Test
    public void testTopic() {
        for (int i = 0; i < 100; i++) {
//            executor.submit(() -> {
//                for (int j = 0; j < 20; j++) {
//                    topicSender.sendMessage("test" + j);
//                }
//            });
        }
    }



}
