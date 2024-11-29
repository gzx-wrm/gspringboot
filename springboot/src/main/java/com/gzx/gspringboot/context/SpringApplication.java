package com.gzx.gspringboot.context;

import com.gzx.gspringboot.annotation.EnableAutoConfiguration;
import com.gzx.gspringboot.autoconfigure.AutoConfigurationImportSelector;
import com.gzx.gspringboot.server.TomcatServer;
import com.gzx.gspringboot.server.WebServer;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;

public class SpringApplication {

    private static String basePackagePath;

    private static AnnotationConfigWebApplicationContext webContext;

    public static void run(Class clazz) throws Exception {
        // 需要做的事情 1. 启动tomcat 2. 注册dispatcher servlet 3. 读取自动配置
        // 获取启动类的路径，这个路径是默认的spring context和spring web context的扫描路径
        String basePackagePath = clazz.getPackage().getName();
        SpringApplication.basePackagePath = basePackagePath;

        AnnotationConfigWebApplicationContext webContext = initWebContext(clazz);
        SpringApplication.webContext = webContext;

        // 启动服务器
        WebServer webServer = getWebServer(webContext);
        webServer.start(webContext);

//        // 从clazz注解中获取AutoConfiguration注解，再获取ImportSelector实现类
//        if (clazz.isAnnotationPresent(EnableAutoConfiguration.class)) {
//            Annotation enableAutoConfiguration = clazz.getAnnotation(EnableAutoConfiguration.class);
//            if (!enableAutoConfiguration.getClass().isAnnotationPresent(Import.class)) {
//
//            }
//            Import importAnnotation = enableAutoConfiguration.getClass().getAnnotation(Import.class);
//            Class<?>[] importClazzList = importAnnotation.value();
//            if (importClazzList.length == 0) {
//                System.out.println("no available import selector!");
//            }
//            Class<?> importSelectorClazz = importClazzList[0];
//            DeferredImportSelector importSelector = (DeferredImportSelector) importSelectorClazz.newInstance();
//            importSelector.selectImports(null);
//        }
    }

    private static AnnotationConfigWebApplicationContext initWebContext(Class clazz) {
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        // 将启动类注册到webContext中，这个类被SpringBootApplication注解标注，带有ComponentScan会被Spring容器扫描
        // 默认该类下的所有包以及子包下的类都会被扫描到，所以Controller类就被注入进来了

        // 同时，这里注册clazz的时候还会扫描到EnableAutoConfiguration注解，实现自动配置的功能
        // 目前不清楚为什么自己写的EnableAutoConfiguration注解也能自动生效 todo 了解一下详细的原理
        webContext.register(clazz);
        webContext.refresh();
        return webContext;
    }

    private static void startServer(AnnotationConfigWebApplicationContext context) throws Exception {
        TomcatServer tomcatServer = new TomcatServer();
        tomcatServer.start(context);
    }

    private static WebServer getWebServer(WebApplicationContext webContext) {
        Map<String, WebServer> webServers = webContext.getBeansOfType(WebServer.class);

        // 从容器中获取webServer，默认获取到一个，如果不是一个就会报错
        // webserver只有在对应的依赖存在时才能获取到
        if (webServers.size() == 0) {
            throw new RuntimeException("no available web server");
        }

        if (webServers.size() > 1) {
            throw new RuntimeException("multiple available web server");
        }

        return webServers.values().iterator().next();
    }
}
