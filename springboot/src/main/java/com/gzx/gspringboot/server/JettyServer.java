package com.gzx.gspringboot.server;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class JettyServer implements WebServer {
    @Override
    public void start(AnnotationConfigWebApplicationContext webContext) throws Exception {
        System.out.println("JettyServer start");
    }
}
