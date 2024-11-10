package com.gzx.gspringboot.server;

import com.gzx.gspringboot.annotation.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServerAutoConfiguration {

    @Bean
    @ConditionalOnClass("org.apache.catalina.startup.Tomcat")
    public TomcatServer tomcatServer() {
        return new TomcatServer();
    }

    @Bean
    @ConditionalOnClass("org.eclipse.jetty")
    public JettyServer jettyServer() {
        return new JettyServer();
    }
}
