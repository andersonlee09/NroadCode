package com.jedis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/8 16:49
 * @purpose null
 * @ModifiedRecords null
 */
public class TestConnection {
    private Jedis jedis;
    @BeforeEach
    void setUp() {    // 建立连接
        jedis = new Jedis("192.168.23.131", 6379);
        System.out.println(jedis.ping());
        // 设置密码
//        jedis.auth("011009lrh");
        // 选择库
        jedis.select(0);
    }

    public static void main(String[] args) {
        TestConnection testConnection = new TestConnection();
        testConnection.setUp();
        testConnection.testString();
        testConnection.tearDown();
    }
    @Test
    void testString() {
        System.out.println("start");
        String res = jedis.set("name","ander");
        System.out.println("res = " + res);
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }
    @Test
    void run() {
        System.out.println("start");
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
