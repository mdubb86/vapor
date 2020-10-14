package dev.jarcadia.vapor.config;

import dev.jarcadia.redao.RedaoCommando;
import dev.jarcadia.retask.Retask;
import dev.jarcadia.retask.RetaskManager;
import io.lettuce.core.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetaskConfig {

    private final Logger logger = LoggerFactory.getLogger(RetaskConfig.class);
    
    @Autowired
    RedisClient redisClient;
    
    @Autowired
    RedaoCommando redisCommando;
    
    @Bean
    public RetaskManager watchdogManager() throws ClassNotFoundException {

        RetaskManager manager = Retask.init(redisClient, redisCommando, null);




//        WatchdogManager manager = Watchdog.init(redisClient, redisCommando, "com.polydyne.watchdog");
//        manager.registerPostShutdownHook(() -> {
//            logger.info("Shutting down log4j2");
//            LogManager.shutdown();
//        });
//        return manager;

        return manager;
    }

}