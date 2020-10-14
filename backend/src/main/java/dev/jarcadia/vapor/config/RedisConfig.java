package dev.jarcadia.vapor.config;


import dev.jarcadia.redao.RedaoCommando;
import io.lettuce.core.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
    
    private final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
    
    @Autowired
    ApplicationContext context;
    
    @Bean
    public RedisClient redisClient() {
        return RedisClient.create("redis://localhost");
    }
    
    @Bean
    public RedaoCommando redisCommando() {
        return RedaoCommando.create(redisClient());
    }
}