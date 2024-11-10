package com.gzx.demo.server;

import com.gzx.gspringboot.server.WebServer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class JettyServer implements WebServer {
    @Override
    public void start(AnnotationConfigWebApplicationContext webContext) throws Exception {
        System.out.println("JettyServer start");
    }
}
