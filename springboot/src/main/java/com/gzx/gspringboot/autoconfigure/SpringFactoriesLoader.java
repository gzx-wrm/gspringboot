package com.gzx.gspringboot.autoconfigure;

import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 这个类用于实际读取外部jar包的configuration配置类名，并将配置类名做缓存
 */
public final class SpringFactoriesLoader {
    // 其实就是使用类加载器读取各个Jar包的META-INF/spring.factories配置文件，这个文件里面不仅仅包括自动配置类名
    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

    static final Map<ClassLoader, Map<String, List<String>>> cache = new ConcurrentReferenceHashMap();

    public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
        ClassLoader classLoaderToUse = classLoader;
        if (classLoaderToUse == null) {
            classLoaderToUse = org.springframework.core.io.support.SpringFactoriesLoader.class.getClassLoader();
        }

        String factoryTypeName = factoryType.getName();
        // 实际上cache/result中不仅仅保存了自动配置类的类名，还有一些其他的东西..暂时不知道是什么
        // 如果需要读取自动配置类，则从cache/result中读取键为AutoConfiguration的值即可
        return (List)loadSpringFactories(classLoaderToUse).getOrDefault(factoryTypeName, Collections.emptyList());
    }

    private static Map<String, List<String>> loadSpringFactories(ClassLoader classLoader) {
        Map<String, List<String>> result = (Map)cache.get(classLoader);
        if (result != null) {
            return result;
        } else {
            result = new HashMap();

            try {
                Enumeration<URL> urls = classLoader.getResources("META-INF/spring.factories");

                while(urls.hasMoreElements()) {
                    URL url = (URL)urls.nextElement();
                    UrlResource resource = new UrlResource(url);
                    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                    Iterator var6 = properties.entrySet().iterator();

                    while(var6.hasNext()) {
                        Map.Entry<?, ?> entry = (Map.Entry)var6.next();
                        String factoryTypeName = ((String)entry.getKey()).trim();
                        String[] factoryImplementationNames = StringUtils.commaDelimitedListToStringArray((String)entry.getValue());
                        String[] var10 = factoryImplementationNames;
                        int var11 = factoryImplementationNames.length;

                        for(int var12 = 0; var12 < var11; ++var12) {
                            String factoryImplementationName = var10[var12];
                            ((List)result.computeIfAbsent(factoryTypeName, (key) -> {
                                return new ArrayList();
                            })).add(factoryImplementationName.trim());
                        }
                    }
                }

                result.replaceAll((factoryType, implementations) -> implementations.stream().distinct().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)));
                cache.put(classLoader, result);
                return result;
            } catch (IOException var14) {
                IOException ex = var14;
                throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", ex);
            }
        }
    }
}
