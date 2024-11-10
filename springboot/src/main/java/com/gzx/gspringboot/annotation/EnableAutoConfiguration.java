package com.gzx.gspringboot.annotation;

import com.gzx.gspringboot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
}
