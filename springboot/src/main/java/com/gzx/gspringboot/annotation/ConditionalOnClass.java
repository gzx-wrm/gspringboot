package com.gzx.gspringboot.annotation;

import com.gzx.gspringboot.server.OnClassCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于判断某个类是否能被加载从而进行生成bean，如果condition返回false，则不会注入bean
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnClassCondition.class)
public @interface ConditionalOnClass {

    String value() default "";
}
