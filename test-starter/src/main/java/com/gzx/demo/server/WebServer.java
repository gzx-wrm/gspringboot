package com.gzx.demo.server;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * webserver 接口，负责服务器的启动
 */
public interface WebServer {

    void start(AnnotationConfigWebApplicationContext webContext) throws Exception;
}
