package com.gzx.demo;

import com.gzx.gspringboot.annotation.SpringBootApplication;
import com.gzx.gspringboot.context.SpringApplication;
import com.gzx.gspringboot.server.WebServerAutoConfiguration;
import com.gzx.spring.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("com.gzx.demo")
// 使用Import注解导入某个配置类
//@Import(WebServerAutoConfiguration.class)
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}
