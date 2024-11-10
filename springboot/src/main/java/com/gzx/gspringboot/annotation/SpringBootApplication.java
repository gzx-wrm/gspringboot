package com.gzx.gspringboot.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * springboot的启动类注解，包括若干核心注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@ComponentScan
@EnableAutoConfiguration
public @interface SpringBootApplication {
}
