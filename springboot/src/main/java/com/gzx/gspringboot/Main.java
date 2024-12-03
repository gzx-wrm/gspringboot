package com.gzx.gspringboot;

import com.gzx.gspringboot.annotation.SpringBootApplication;
import com.gzx.gspringboot.context.SpringApplication;
import com.gzx.spring.annotation.ComponentScan;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class);
    }
}