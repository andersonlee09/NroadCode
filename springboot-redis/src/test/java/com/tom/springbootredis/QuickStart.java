package com.tom.springbootredis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;


/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/8 19:16
 * @purpose null
 * @ModifiedRecords null
 */

@SpringBootTest
public class QuickStart {

   /* @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name", "李四");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name= " + name);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {    // 创建Template
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();    // 设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置序列化工具
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // key和 hashKey采用 string序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // value和 hashValue采用 JSON序列化
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        return redisTemplate;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // JSON工具
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testStringTemplate() throws JsonProcessingException {
        // 准备对象
        User user = new User("虎哥", 18);
        // 手动序列化
        String json = mapper.writeValueAsString(user);
        // 写入一条数据到redis
        stringRedisTemplate.opsForValue().set("user:200", json);
        // 读取数据
        String val = stringRedisTemplate.opsForValue().get("user:200");
        // 反序列化
        User user1 = mapper.readValue(val, User.class);
        System.out.println("user1 = " + user1);
    }
*/
}
