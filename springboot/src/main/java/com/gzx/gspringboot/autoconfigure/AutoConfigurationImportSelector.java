package com.gzx.gspringboot.autoconfigure;

import com.gzx.gspringboot.annotation.EnableAutoConfiguration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.util.List;

/**
 * springboot自动配置依赖的读取类
 * 这个类实现了一个核心方法：selectImports，会返回所有外部jar包定义的Configuration配置类（返回类的完全限定名的数组）
 * 后续就是根据这个类的完全限定名加载类并且注入到容器中了
 */
public class AutoConfigurationImportSelector implements DeferredImportSelector {
    private static final String[] NO_IMPORTS = new String[0];

    private static final String[] EMPTY_ENTRY = new String[0];

    private static final ClassLoader beanClassLoader = AutoConfigurationImportSelector.class.getClassLoader();

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return this.getAutoConfigurationEntry(annotationMetadata);
    }

    protected String[] getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
        List<String> configurations = this.getCandidateConfigurations(annotationMetadata);
        return configurations.toArray(new String[configurations.size()]);
    }


    protected List<String> getCandidateConfigurations(AnnotationMetadata metadata) {
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
        return configurations;
    }

    protected Class<?> getSpringFactoriesLoaderFactoryClass() {
        return EnableAutoConfiguration.class;
    }

    protected ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

}
