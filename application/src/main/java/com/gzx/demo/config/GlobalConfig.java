//package com.gzx.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//@Configuration
//public class GlobalConfig extends WebMvcConfigurationSupport {
//
//    @Override
//    protected void addCorsMappings(CorsRegistry registry) {
//        System.out.println("cors config....");
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .maxAge(3600)
//                .allowedHeaders("*")
//                .allowedMethods("*");
//    }
//}
