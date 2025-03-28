// @author Tjzlo
// @version 2024/11/4 12:05

package com.sky.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,ObjectMapper objectMapper){
        log.info("开始创建redis模版对象...");
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        //设置redis的连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置redis key的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 使用Jackson2JsonRedisSerializer作为值序列化器
        Jackson2JsonRedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        try {
            valueSerializer.setObjectMapper(objectMapper);
        } catch (Exception e) {
            log.error("初始化Jackson2JsonRedisSerializer失败,不使用序列化器传入", e);
        }

        redisTemplate.setValueSerializer(valueSerializer);

        return redisTemplate;
    }

}
