package com.gzx.demo.config;

import com.gzx.demo.entity.TestEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAutoConfiguration {

    @Bean
    public TestEntity testEntity() {
        return new TestEntity(725430);
    }

}
